package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.PaymentMethod;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentMethodHelper {

    private final ActiveUserContext context;

    public void getSaveData(PaymentMethod paymentMethod) {
        paymentMethod.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        paymentMethod.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(PaymentMethod paymentMethod, RecordStatus status) {
        paymentMethod.setRecordVersion(paymentMethod.getRecordVersion() + 1);
        paymentMethod.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        paymentMethod.setRecordStatus(status);
    }

}
