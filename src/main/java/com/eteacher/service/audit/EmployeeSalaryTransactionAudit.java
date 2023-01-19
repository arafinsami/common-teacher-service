package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.PaymentMethod;
import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.mpo.EmployeeSalaryTransaction;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeSalaryTransactionAudit {

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

    private PaymentMethod paymentMethod;

    private PaymentPeriod paymentPeriod;

    private Long reasonForRejection;

    private SalaryScale salaryScale;

    public static EmployeeSalaryTransactionAudit audit(EmployeeSalaryTransaction transaction) {
        EmployeeSalaryTransactionAudit audit = new EmployeeSalaryTransactionAudit();
        audit.setId(transaction.getId());
        audit.setPaymentDate(transaction.getPaymentDate());
        audit.setBankBranchId(transaction.getBankBranchId());
        audit.setSmsNotified(transaction.getSmsNotified());
        audit.setEmailNotified(transaction.getEmailNotified());
        audit.setRemarks(transaction.getRemarks());
        audit.setStatus(transaction.getStatus());
        audit.setApproverUserId(transaction.getApproverUserId());
        audit.setApproverNote(transaction.getApproverNote());
        audit.setApproveDate(transaction.getApproveDate());
        audit.setPaymentMethod(transaction.getPaymentMethod());
        audit.setPaymentPeriod(transaction.getPaymentPeriod());
        audit.setReasonForRejection(transaction.getReasonForRejection());
        audit.setSalaryScale(transaction.getSalaryScale());
        return audit;
    }
}
