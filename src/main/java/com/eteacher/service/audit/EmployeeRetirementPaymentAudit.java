package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.PaymentMethod;
import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.mpo.EmployeeRetirementPayment;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeRetirementPaymentAudit {

    private Long id;

    private Date paymentDate;

    private String bankAccountNumber;

    private Long bankBranchId;

    private String remarks;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private PaymentMethod paymentMethod;

    private PaymentPeriod paymentPeriod;

    private Long reasonForRejection;

    private SalaryScale salaryScale;

    public static EmployeeRetirementPaymentAudit audit(EmployeeRetirementPayment payment) {
        EmployeeRetirementPaymentAudit audit = new EmployeeRetirementPaymentAudit();
        audit.setId(payment.getId());
        audit.setPaymentDate(payment.getPaymentDate());
        audit.setBankAccountNumber(payment.getBankAccountNumber());
        audit.setBankBranchId(payment.getBankBranchId());
        audit.setRemarks(payment.getRemarks());
        audit.setStatus(payment.getStatus());
        audit.setApproverUserId(payment.getApproverUserId());
        audit.setApproverNote(payment.getApproverNote());
        audit.setApproveDate(payment.getApproveDate());
        audit.setPaymentMethod(payment.getPaymentMethod());
        audit.setPaymentPeriod(payment.getPaymentPeriod());
        audit.setReasonForRejection(payment.getReasonForRejection());
        audit.setSalaryScale(payment.getSalaryScale());
        return audit;
    }
}
