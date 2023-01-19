package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentPeriodHelper {

    private final ActiveUserContext context;

    public void getSaveData(PaymentPeriod paymentPeriod) {
        paymentPeriod.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        paymentPeriod.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(PaymentPeriod paymentPeriod, RecordStatus status) {
        paymentPeriod.setRecordVersion(paymentPeriod.getRecordVersion() + 1);
        paymentPeriod.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        paymentPeriod.setRecordStatus(status);
    }
}
