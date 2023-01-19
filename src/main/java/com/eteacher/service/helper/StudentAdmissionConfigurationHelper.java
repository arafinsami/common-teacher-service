package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.StudentAdmissionConfiguration;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentAdmissionConfigurationHelper {

    private final ActiveUserContext context;

    public void getSaveData(StudentAdmissionConfiguration studentAdmissionConfiguration) {
        studentAdmissionConfiguration.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        studentAdmissionConfiguration.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(StudentAdmissionConfiguration studentAdmissionConfiguration, RecordStatus status) {
        studentAdmissionConfiguration.setRecordVersion(studentAdmissionConfiguration.getRecordVersion() + 1);
        studentAdmissionConfiguration.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        studentAdmissionConfiguration.setRecordStatus(status);
    }
}
