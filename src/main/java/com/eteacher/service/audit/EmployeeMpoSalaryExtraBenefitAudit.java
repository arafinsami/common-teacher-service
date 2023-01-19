package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.mpo.EmployeeMpoSalaryExtraBenefit;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeMpoSalaryExtraBenefitAudit {

    private Long id;

    private Boolean isApproved;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private PaymentPeriod paymentPeriod;

    private Long reasonForRejection;

    public static EmployeeMpoSalaryExtraBenefitAudit audit(EmployeeMpoSalaryExtraBenefit benefit) {
        EmployeeMpoSalaryExtraBenefitAudit audit = new EmployeeMpoSalaryExtraBenefitAudit();
        audit.setId(benefit.getId());
        audit.setIsApproved(benefit.getIsApproved());
        audit.setApproverUserId(benefit.getApproverUserId());
        audit.setApproverNote(benefit.getApproverNote());
        audit.setApproveDate(benefit.getApproveDate());
        audit.setPaymentPeriod(benefit.getPaymentPeriod());
        audit.setReasonForRejection(benefit.getReasonForRejection());
        return audit;
    }
}
