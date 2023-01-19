package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.EmployeeType;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeeTypeHelper {

    private final ActiveUserContext context;

    public void getSaveData(EmployeeType employeeType) {
        employeeType.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        employeeType.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(EmployeeType employeeType, RecordStatus status) {
        employeeType.setRecordVersion(employeeType.getRecordVersion() + 1);
        employeeType.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        employeeType.setRecordStatus(status);
    }
}
