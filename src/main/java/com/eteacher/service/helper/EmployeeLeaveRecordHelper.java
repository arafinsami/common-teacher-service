package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeeLeaveRecordEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeLeaveRecord;
import com.eteacher.service.entity.commonteacher.EmployeeLeaveRecordEncloser;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeLeaveRecordEncloserProfile;
import com.eteacher.service.profile.EmployeeLeaveRecordProfile;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.utils.FileUpload;
import lombok.RequiredArgsConstructor;
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

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_LEAVE_RECORD;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeLeaveRecordHelper extends FileUpload {

    @Resource
    private ActiveUserContext context;

    @Resource
    private Environment env;

    public Function<EmployeeLeaveRecordProfile, EmployeeLeaveRecord> saveProfile = converter -> {
        EmployeeLeaveRecord record = converter.to();
        record.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return record;
    };

    public Function<EmployeeLeaveRecordEncloserProfile, EmployeeLeaveRecordEncloser> saveEncloserProfiles = r -> {
        EmployeeLeaveRecordEncloser encloser = r.to();
        return encloser;
    };

    public Function<EmployeeLeaveRecordProfile, EmployeeLeaveRecord> updateProfile = converter -> {
        EmployeeLeaveRecord leaveRecord = converter.update();
        leaveRecord.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return leaveRecord;
    };

    public List<EmployeeLeaveRecord> save(List<EmployeeLeaveRecordProfile> profiles) {
        List<EmployeeLeaveRecord> lists = profiles.stream()
                .map(saveProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeLeaveRecordEncloser> save(MultipartFile[] files, EmployeeLeaveRecordEncloserDto dto) {
        List<EmployeeLeaveRecordEncloser> lists = dto.getProfiles()
                .stream()
                .map(saveEncloserProfiles)
                .collect(Collectors.toList());

        for (int index = 0; index < lists.size(); index++) {
            for (EmployeeLeaveRecordEncloser encloser : lists) {
                if (encloser.getEncloserUrl().equals(files[index].getOriginalFilename())) {
                    encloser.setEncloserUrl(
                            upload(files[index], env.getProperty("employeeLeaveRecordEncloserUploadPath"))
                    );
                }
            }
        }
        return lists;
    }

    public List<EmployeeLeaveRecord> update(List<EmployeeLeaveRecordProfile> profiles) {
        List<EmployeeLeaveRecord> lists = profiles.stream()
                .map(updateProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeLeaveRecord> deleteOne(EmployeeLeaveRecord leaveRecord) {
        leaveRecord.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        leaveRecord.setRecordStatus(DELETED);
        return Arrays.asList(leaveRecord);
    }

    public EmployeeLeaveRecord findByRecordId(Long recordId, List<EmployeeLeaveRecord> lists) {
        for (EmployeeLeaveRecord record : lists) {
            if (record.getId().equals(recordId)) {
                return record;
            }
        }
        return null;
    }

    public EmployeeLeaveRecord findLeaveRecord(Long recordId, Employee employee) {
        return Optional.ofNullable(findByRecordId(recordId, employee.getLeaveRecords()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_LEAVE_RECORD + recordId)
                );
    }

}
