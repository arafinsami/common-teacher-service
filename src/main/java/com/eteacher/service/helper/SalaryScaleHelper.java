package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalaryScaleHelper {

    private final ActiveUserContext context;

    public void getSaveData(SalaryScale salaryScale) {
//    salaryScale.setCreatedBy(context.getLoggedInUser());
        salaryScale.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(SalaryScale salaryScale, RecordStatus status) {
        salaryScale.setRecordVersion(salaryScale.getRecordVersion() + 1);
//    salaryScale.setUpdatedBy(context.getLoggedInUser());
        salaryScale.setRecordStatus(status);
    }
}
