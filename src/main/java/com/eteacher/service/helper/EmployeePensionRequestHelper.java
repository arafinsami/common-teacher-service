package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeePensionRequestEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.govtteacher.EmployeePensionRequest;
import com.eteacher.service.entity.govtteacher.EmployeePensionRequestEncloser;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeePensionRequestEncloserProfile;
import com.eteacher.service.profile.EmployeePensionRequestProfile;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.utils.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_PENSION_REQUEST;
import static com.eteacher.service.enums.RecordStatus.*;

@Component
@RequiredArgsConstructor
public class EmployeePensionRequestHelper extends FileUpload {

    @Resource
    private ActiveUserContext context;

    @Resource
    private Environment env;

    public Function<EmployeePensionRequestProfile, EmployeePensionRequest> saveProfile = r -> {
        EmployeePensionRequest pensionRequest = r.to();
        pensionRequest.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        pensionRequest.setRecordStatus(DRAFT);
        return pensionRequest;
    };

    public Function<EmployeePensionRequestProfile, EmployeePensionRequest> updateProfile = r -> {
        EmployeePensionRequest pensionRequest = r.to();
        pensionRequest.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        pensionRequest.setRecordStatus(ACTIVE);
        return pensionRequest;
    };

    public Function<EmployeePensionRequestEncloserProfile, EmployeePensionRequestEncloser> saveEncloserProfile = r -> {
        EmployeePensionRequestEncloser encloser = r.to();
        BeanUtils.copyProperties(r, encloser);
        encloser.setRecordStatus(DRAFT);
        return encloser;
    };

    public List<EmployeePensionRequestEncloser> saveEncloser(MultipartFile[] files, EmployeePensionRequestEncloserDto dto) {
        List<EmployeePensionRequestEncloser> lists = dto.getProfiles()
                .stream()
                .map(saveEncloserProfile)
                .collect(Collectors.toList());

        IntStream.range(0, lists.size()).forEach(index -> {
            if (matchFileName(files[index], lists)) {
                lists.get(index).setEncloserUrl(upload(files[index], env.getProperty("pensionRequestEncloserUploadPath")));
            }
        });
        return lists;
    }

    private boolean matchFileName(MultipartFile file, List<EmployeePensionRequestEncloser> lists) {
        return Objects.nonNull(lists.stream().filter(l -> file.getOriginalFilename().equals(l.getEncloserUrl())));
    }

    public List<EmployeePensionRequest> savePensions(List<EmployeePensionRequestProfile> profiles) {
        List<EmployeePensionRequest> pensionRequests = profiles.stream()
                .map(saveProfile)
                .collect(Collectors.toList());
        return pensionRequests;
    }

    public List<EmployeePensionRequest> updatePensions(List<EmployeePensionRequestProfile> profiles) {
        List<EmployeePensionRequest> pensionRequests = profiles.stream()
                .map(updateProfile)
                .collect(Collectors.toList());
        return pensionRequests;
    }

    public List<EmployeePensionRequest> delete(EmployeePensionRequest pensionRequest) {
        pensionRequest.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        pensionRequest.setRecordStatus(DELETED);
        return Arrays.asList(pensionRequest);
    }

    private EmployeePensionRequest findByPensionId(Long pensionId, List<EmployeePensionRequest> lists) {
        for (EmployeePensionRequest pensionRequest : lists) {
            if (pensionRequest.getId().equals(pensionId)) {
                return pensionRequest;
            }
        }
        return null;
    }

    public EmployeePensionRequest findPension(Long pensionId, Employee employee) {
        return Optional.ofNullable(findByPensionId(pensionId, employee.getPensionRequests()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_PENSION_REQUEST + pensionId)
                );
    }
}
