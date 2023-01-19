package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.StudentType;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentTypeHelper {

    private final ActiveUserContext context;

    public void getSaveData(StudentType studentType) {
        studentType.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        studentType.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(StudentType studentType, RecordStatus status) {
        studentType.setRecordVersion(studentType.getRecordVersion() + 1);
        studentType.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        studentType.setRecordStatus(status);
    }
}
