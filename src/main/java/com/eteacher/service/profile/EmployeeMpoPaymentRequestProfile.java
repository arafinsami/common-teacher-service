package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.mpo.EmployeeMpoPaymentRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeMpoPaymentRequestProfile {

    private Long id;

    private Boolean isApproved;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private PaymentPeriod paymentPeriod;

    private Long reasonForRejection;

    public EmployeeMpoPaymentRequest to() {
        EmployeeMpoPaymentRequest profile = new EmployeeMpoPaymentRequest();
        profile.setId(id);
        profile.setIsApproved(isApproved);
        profile.setApproverUserId(approverUserId);
        profile.setApproverNote(approverNote);
        profile.setApproveDate(approveDate);
        profile.setPaymentPeriod(paymentPeriod);
        profile.setReasonForRejection(reasonForRejection);
        return profile;
    }
}
