package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.mpo.EmployeeMpoPaymentRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeMpoPaymentRequestAudit {

    private Long id;

    private Boolean isApproved;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private PaymentPeriod paymentPeriod;

    private Long reasonForRejection;

    public static EmployeeMpoPaymentRequestAudit audit(EmployeeMpoPaymentRequest profile) {
        EmployeeMpoPaymentRequestAudit audit = new EmployeeMpoPaymentRequestAudit();
        audit.setId(profile.getId());
        audit.setIsApproved(profile.getIsApproved());
        audit.setApproverUserId(profile.getApproverUserId());
        audit.setApproverNote(profile.getApproverNote());
        audit.setApproveDate(profile.getApproveDate());
        audit.setPaymentPeriod(profile.getPaymentPeriod());
        audit.setReasonForRejection(profile.getReasonForRejection());
        return audit;
    }
}
