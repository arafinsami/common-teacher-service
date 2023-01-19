package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeeTransferRecordEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeTransferRecord;
import com.eteacher.service.entity.mpo.EmployeeTransferRecordEncloser;
import com.eteacher.service.entity.mpo.EmployeeTransferRecordPreferredInstitute;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeTransferRecordEncloserProfile;
import com.eteacher.service.profile.EmployeeTransferRecordPreferredInstituteProfile;
import com.eteacher.service.profile.EmployeeTransferRecordProfile;
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

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_TRANSFER_RECORD;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_TRANSFER_RECORD_PREFERRED_INSTITUTE;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
public class EmployeeMpoTransferRecordHelper extends FileUpload {

    @Resource
    private ActiveUserContext context;

    @Resource
    private Environment env;

    public Function<EmployeeTransferRecordProfile, EmployeeTransferRecord> saveRecord = r -> {
        EmployeeTransferRecord record = r.to();
        record.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return record;
    };

    public Function<EmployeeTransferRecordPreferredInstituteProfile, EmployeeTransferRecordPreferredInstitute> saveTransferProfile = r -> {
        EmployeeTransferRecordPreferredInstitute institute = r.to();
        institute.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return institute;
    };

    public Function<EmployeeTransferRecordEncloserProfile, EmployeeTransferRecordEncloser> saveProfiles = r -> {
        EmployeeTransferRecordEncloser encloser = r.to();
        return encloser;
    };

    public Function<EmployeeTransferRecordProfile, EmployeeTransferRecord> updateRecord = r -> {
        EmployeeTransferRecord record = r.update();
        record.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return record;
    };

    public Function<EmployeeTransferRecordPreferredInstituteProfile, EmployeeTransferRecordPreferredInstitute> updateTransferProfile = r -> {
        EmployeeTransferRecordPreferredInstitute institute = r.update();
        institute.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return institute;
    };

    public List<EmployeeTransferRecord> save(List<EmployeeTransferRecordProfile> profiles) {
        List<EmployeeTransferRecord> records = profiles.stream()
                .map(saveRecord)
                .collect(Collectors.toList());
        return records;
    }

    public EmployeeTransferRecordPreferredInstitute save(EmployeeTransferRecordPreferredInstituteProfile profile) {
        EmployeeTransferRecordPreferredInstitute transfer = saveTransferProfile.apply(profile);
        return transfer;
    }

    public List<EmployeeTransferRecordEncloser> save(MultipartFile[] files, EmployeeTransferRecordEncloserDto dto) {
        List<EmployeeTransferRecordEncloser> lists = dto.getProfiles()
                .stream()
                .map(saveProfiles)
                .collect(Collectors.toList());

        for (int index = 0; index < lists.size(); index++) {
            for (EmployeeTransferRecordEncloser encloser : lists) {
                if (encloser.getEncloserUrl().equals(files[index].getOriginalFilename())) {
                    encloser.setEncloserUrl(
                            upload(files[index], env.getProperty("transferRecordEncloserUploadPath"))
                    );
                }
            }
        }
        return lists;
    }

    public List<EmployeeTransferRecord> update(List<EmployeeTransferRecordProfile> profiles) {
        List<EmployeeTransferRecord> records = profiles.stream()
                .map(updateRecord)
                .collect(Collectors.toList());
        return records;
    }

    public EmployeeTransferRecordPreferredInstitute update(EmployeeTransferRecordPreferredInstituteProfile profile) {
        EmployeeTransferRecordPreferredInstitute transfer = updateTransferProfile.apply(profile);
        return transfer;
    }

    private EmployeeTransferRecord findByRecordId(Long id, List<EmployeeTransferRecord> lists) {
        for (EmployeeTransferRecord record : lists) {
            if (record.getId().equals(id)) {
                return record;
            }
        }
        return null;
    }

    private EmployeeTransferRecordPreferredInstitute findByInstituteId(Long id, List<EmployeeTransferRecordPreferredInstitute> lists) {
        for (EmployeeTransferRecordPreferredInstitute institute : lists) {
            if (institute.getId().equals(id)) {
                return institute;
            }
        }
        return null;
    }

    public EmployeeTransferRecord findByRecord(Long recordId, Employee employee) {
        return Optional.ofNullable(findByRecordId(recordId, employee.getTransferRecords()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_TRANSFER_RECORD + recordId)
                );
    }

    public EmployeeTransferRecordPreferredInstitute findByInstitute(Long instituteId, EmployeeTransferRecord record) {
        return Optional.ofNullable(findByInstituteId(instituteId, record.getPreferredInstitutes()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_TRANSFER_RECORD_PREFERRED_INSTITUTE + instituteId)
                );
    }

    public EmployeeTransferRecord deleteOne(EmployeeTransferRecord record) {
        record.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        record.setRecordStatus(DELETED);
        return record;
    }
}
