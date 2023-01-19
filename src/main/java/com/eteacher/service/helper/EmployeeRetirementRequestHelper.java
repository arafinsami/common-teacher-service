package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeeRetirementRequestEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeRetirementRequest;
import com.eteacher.service.entity.mpo.EmployeeRetirementRequestEncloser;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeRetirementRequestEncloserProfile;
import com.eteacher.service.profile.EmployeeRetirementRequestProfile;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.utils.FileUpload;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_RETIREMENT_REQUEST;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
public class EmployeeRetirementRequestHelper extends FileUpload {

    @Resource
    private ActiveUserContext context;

    @Resource
    private Environment env;

    public Function<EmployeeRetirementRequestProfile, EmployeeRetirementRequest> saveProfile = converter -> {
        EmployeeRetirementRequest record = converter.to();
        record.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return record;
    };

    public Function<EmployeeRetirementRequestEncloserProfile, EmployeeRetirementRequestEncloser> saveEncloserProfiles = r -> {
        EmployeeRetirementRequestEncloser encloser = r.to();
        return encloser;
    };

    public Function<EmployeeRetirementRequestProfile, EmployeeRetirementRequest> updateProfile = converter -> {
        EmployeeRetirementRequest leaveRecord = converter.update();
        leaveRecord.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return leaveRecord;
    };

    public List<EmployeeRetirementRequest> save(List<EmployeeRetirementRequestProfile> profiles) {
        List<EmployeeRetirementRequest> lists = profiles
                .stream()
                .map(saveProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeRetirementRequestEncloser> save(MultipartFile[] files, EmployeeRetirementRequestEncloserDto dto) {
        List<EmployeeRetirementRequestEncloser> lists = dto.getProfiles()
                .stream()
                .map(saveEncloserProfiles)
                .collect(Collectors.toList());

        for (int index = 0; index < lists.size(); index++) {
            for (EmployeeRetirementRequestEncloser encloser : lists) {
                if (encloser.getEncloserUrl().equals(files[index].getOriginalFilename())) {
                    encloser.setEncloserUrl(
                            upload(files[index], env.getProperty("employeeMpoRetirementRequestEncloserUploadPath"))
                    );
                }
            }
        }
        return lists;
    }

    public List<EmployeeRetirementRequest> update(List<EmployeeRetirementRequestProfile> profiles) {
        List<EmployeeRetirementRequest> lists = profiles
                .stream()
                .map(updateProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public EmployeeRetirementRequest deleteOne(EmployeeRetirementRequest request) {
        request.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        request.setRecordStatus(DELETED);
        return request;
    }

    public EmployeeRetirementRequest findByRequestId(Long requestId, List<EmployeeRetirementRequest> lists) {
        for (EmployeeRetirementRequest request : lists) {
            if (request.getId().equals(requestId)) {
                return request;
            }
        }
        return null;
    }

    public EmployeeRetirementRequest findRequest(Long requestId, Employee employee) {
        return Optional.ofNullable(findByRequestId(requestId, employee.getRetirementRequests()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_RETIREMENT_REQUEST + requestId)
                );
    }
}
