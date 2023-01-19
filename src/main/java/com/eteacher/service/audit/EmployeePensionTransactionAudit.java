package com.eteacher.service.audit;

import com.eteacher.service.entity.govtteacher.EmployeePensionTransaction;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeePensionTransactionAudit {

    private Long id;

    private Date paymentDate;

    private String bankAccountNumber;

    private Long bankBranchId;

    private Boolean smsNotified;

    private Boolean emailNotified;

    private String remarks;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    private Long paymentMethod;

    private Long paymentPeriod;

    private Long salaryScale;

    public static EmployeePensionTransactionAudit audit(EmployeePensionTransaction transaction) {
        EmployeePensionTransactionAudit audit = new EmployeePensionTransactionAudit();
        audit.setId(transaction.getId());
        audit.setPaymentDate(transaction.getPaymentDate());
        audit.setBankAccountNumber(transaction.getBankAccountNumber());
        audit.setBankBranchId(transaction.getBankBranchId());
        audit.setSmsNotified(transaction.getSmsNotified());
        audit.setRemarks(transaction.getRemarks());
        audit.setStatus(transaction.getStatus());
        audit.setApproverUserId(transaction.getApproverUserId());
        audit.setApproverNote(transaction.getApproverNote());
        audit.setApproveDate(transaction.getApproveDate());
        audit.setReasonForRejection(transaction.getReasonForRejection());
        audit.setPaymentMethod(transaction.getPaymentMethod().getId());
        audit.setPaymentPeriod(transaction.getPaymentPeriod().getId());
        audit.setSalaryScale(transaction.getSalaryScale().getId());
        return audit;
    }
}
