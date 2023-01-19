package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeeReleaseRecordEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeReleaseRecord;
import com.eteacher.service.entity.mpo.EmployeeReleaseRecordEncloser;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeReleaseRecordEncloserProfile;
import com.eteacher.service.profile.EmployeeReleaseRecordProfile;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.utils.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_RELEASE_RECORD;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeReleaseRecordHelper extends FileUpload {

    @Resource
    private ActiveUserContext context;

    @Resource
    private Environment env;

    public Function<EmployeeReleaseRecordProfile, EmployeeReleaseRecord> saveRecord = r -> {
        EmployeeReleaseRecord record = r.to();
        record.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return record;
    };

    public Function<EmployeeReleaseRecordEncloserProfile, EmployeeReleaseRecordEncloser> saveEncloserProfiles = r -> {
        EmployeeReleaseRecordEncloser encloser = r.to();
        return encloser;
    };

    public Function<EmployeeReleaseRecordProfile, EmployeeReleaseRecord> updateRecord = r -> {
        EmployeeReleaseRecord record = r.update();
        record.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return record;
    };

    public List<EmployeeReleaseRecord> save(List<EmployeeReleaseRecordProfile> profiles) {
        List<EmployeeReleaseRecord> records = profiles
                .stream()
                .map(saveRecord)
                .collect(Collectors.toList());
        return records;
    }

    public List<EmployeeReleaseRecordEncloser> save(MultipartFile[] files, EmployeeReleaseRecordEncloserDto dto) {
        List<EmployeeReleaseRecordEncloser> lists = dto.getProfiles()
                .stream()
                .map(saveEncloserProfiles)
                .collect(Collectors.toList());

        for (int index = 0; index < lists.size(); index++) {
            for (EmployeeReleaseRecordEncloser encloser : lists) {
                if (encloser.getEncloserUrl().equals(files[index].getOriginalFilename())) {
                    encloser.setEncloserUrl(
                            upload(files[index], env.getProperty("employeeMpoReleaseRecordEncloserUploadPath"))
                    );
                }
            }
        }
        return lists;
    }

    public List<EmployeeReleaseRecord> update(List<EmployeeReleaseRecordProfile> profiles) {
        List<EmployeeReleaseRecord> records = profiles
                .stream()
                .map(updateRecord)
                .collect(Collectors.toList());
        return records;
    }

    public EmployeeReleaseRecord delete(EmployeeReleaseRecord record) {
        record.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        record.setRecordStatus(DELETED);
        return record;
    }

    private EmployeeReleaseRecord findByRecordId(Long recordId, List<EmployeeReleaseRecord> lists) {
        for (EmployeeReleaseRecord record : lists) {
            if (record.getId().equals(recordId)) {
                return record;
            }
        }
        return null;
    }

    public EmployeeReleaseRecord findRecord(Long recordId, Employee employee) {
        return Optional.ofNullable(findByRecordId(recordId, employee.getReleaseRecords()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_RELEASE_RECORD + recordId)
                );
    }
}
