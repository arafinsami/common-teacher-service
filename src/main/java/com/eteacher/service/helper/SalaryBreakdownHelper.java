package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SalaryBreakdownHelper {

    private final ActiveUserContext context;

    public void getSaveData(SalaryBreakdown salaryBreakdown) {
        salaryBreakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        salaryBreakdown.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(SalaryBreakdown salaryBreakdown, RecordStatus status) {
        salaryBreakdown.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        salaryBreakdown.setRecordStatus(status);
        salaryBreakdown.setRecordVersion(salaryBreakdown.getRecordVersion() + 1);
    }
}
