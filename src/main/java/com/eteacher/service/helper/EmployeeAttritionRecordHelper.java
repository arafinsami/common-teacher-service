package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeeAttritionRecordEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeAttritionRecord;
import com.eteacher.service.entity.mpo.EmployeeAttritionRecordEncloser;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeAttritionRecordEncloserProfile;
import com.eteacher.service.profile.EmployeeAttritionRecordProfile;
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

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_ATTRITION_RECORD;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeAttritionRecordHelper extends FileUpload {

    @Resource
    private ActiveUserContext context;

    @Resource
    private Environment env;

    public Function<EmployeeAttritionRecordProfile, EmployeeAttritionRecord> saveRecordProfile = r -> {
        EmployeeAttritionRecord record = r.to();
        record.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return record;
    };

    public Function<EmployeeAttritionRecordEncloserProfile, EmployeeAttritionRecordEncloser> saveEncloserProfiles = r -> {
        EmployeeAttritionRecordEncloser encloser = r.to();
        return encloser;
    };

    public Function<EmployeeAttritionRecordProfile, EmployeeAttritionRecord> updateRecordProfile = r -> {
        EmployeeAttritionRecord record = r.update();
        record.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return record;
    };

    public List<EmployeeAttritionRecord> save(List<EmployeeAttritionRecordProfile> profiles) {
        List<EmployeeAttritionRecord> records = profiles
                .stream()
                .map(saveRecordProfile)
                .collect(Collectors.toList());
        return records;
    }

    public List<EmployeeAttritionRecordEncloser> save(MultipartFile[] files, EmployeeAttritionRecordEncloserDto dto) {
        List<EmployeeAttritionRecordEncloser> lists = dto.getProfiles()
                .stream()
                .map(saveEncloserProfiles)
                .collect(Collectors.toList());

        for (int index = 0; index < lists.size(); index++) {
            for (EmployeeAttritionRecordEncloser encloser : lists) {
                if (encloser.getEncloserUrl().equals(files[index].getOriginalFilename())) {
                    encloser.setEncloserUrl(
                            upload(files[index], env.getProperty("attritionRecordUploadPath"))
                    );
                }
            }
        }
        return lists;
    }

    public List<EmployeeAttritionRecord> update(List<EmployeeAttritionRecordProfile> profiles) {
        List<EmployeeAttritionRecord> records = profiles
                .stream()
                .map(updateRecordProfile)
                .collect(Collectors.toList());
        return records;
    }

    private EmployeeAttritionRecord findByRecordId(Long recordId, List<EmployeeAttritionRecord> lists) {
        for (EmployeeAttritionRecord joining : lists) {
            if (joining.getId().equals(recordId)) {
                return joining;
            }
        }
        return null;
    }

    public EmployeeAttritionRecord findRecord(Long recordId, Employee employee) {
        return Optional.ofNullable(findByRecordId(recordId, employee.getAttritionRecords()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_ATTRITION_RECORD + recordId)
                );
    }

    public List<EmployeeAttritionRecord> deleteOne(EmployeeAttritionRecord record) {
        record.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        record.setRecordStatus(DELETED);
        return Arrays.asList(record);
    }
}
