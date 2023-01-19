package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeeJoiningEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeJoining;
import com.eteacher.service.entity.commonteacher.EmployeeJoiningEncloser;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeJoiningEncloserProfile;
import com.eteacher.service.profile.EmployeeJoiningProfile;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.utils.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_JOINING;
import static com.eteacher.service.enums.RecordStatus.*;

@Component
@RequiredArgsConstructor
public class EmployeeJoiningHelper extends FileUpload {

    @Resource
    private ActiveUserContext context;

    @Resource
    private Environment env;

    public Function<EmployeeJoiningProfile, EmployeeJoining> saveProfile = converter -> {
        EmployeeJoining joining = converter.to();
        joining.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        joining.setRecordStatus(DRAFT);
        return joining;
    };

    public Function<EmployeeJoiningProfile, EmployeeJoining> updateProfile = converter -> {
        EmployeeJoining joining = converter.update();
        joining.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        joining.setRecordStatus(ACTIVE);
        return joining;
    };

    public List<EmployeeJoining> save(List<EmployeeJoiningProfile> profiles) {
        List<EmployeeJoining> lists = profiles.stream()
                .map(saveProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeJoining> update(List<EmployeeJoiningProfile> profiles) {
        List<EmployeeJoining> lists = profiles.stream()
                .map(updateProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeJoining> deleteOne(EmployeeJoining joining) {
        joining.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        joining.setRecordStatus(DELETED);
        return Arrays.asList(joining);
    }

    public EmployeeJoining findByJoiningId(Long joiningId, List<EmployeeJoining> lists) {
        for (EmployeeJoining joining : lists) {
            if (joining.getId().equals(joiningId)) {
                return joining;
            }
        }
        return null;
    }

    public EmployeeJoining findJoining(Long joiningId, Employee employee) {
        return Optional.ofNullable(findByJoiningId(joiningId, employee.getEmployeeJoinings()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_JOINING + joiningId)
                );
    }

    public Function<EmployeeJoiningEncloserProfile, EmployeeJoiningEncloser> saveProfiles = r -> {
        EmployeeJoiningEncloser encloser = r.to();
        BeanUtils.copyProperties(r, encloser);
        return encloser;
    };

    public List<EmployeeJoiningEncloser> save(MultipartFile[] files, EmployeeJoiningEncloserDto dto) {
        List<EmployeeJoiningEncloser> lists = dto.getProfiles()
                .stream()
                .map(saveProfiles)
                .collect(Collectors.toList());

        for (int index = 0; index < lists.size(); index++) {
            for (EmployeeJoiningEncloser encloser : lists) {
                if (encloser.getEncloserUrl().equals(files[index].getOriginalFilename())) {
                    encloser.setEncloserUrl(
                            upload(files[index], env.getProperty("employeeJoiningEncloserUploadPath"))
                    );
                }
            }
        }
        return lists;
    }
}
