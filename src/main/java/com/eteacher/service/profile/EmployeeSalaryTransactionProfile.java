package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.PaymentMethod;
import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.mpo.EmployeeSalaryTransaction;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeSalaryTransactionProfile {

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

    public EmployeeSalaryTransaction to() {
        EmployeeSalaryTransaction transaction = new EmployeeSalaryTransaction();
        transaction.setPaymentDate(paymentDate);
        transaction.setBankBranchId(bankBranchId);
        transaction.setSmsNotified(smsNotified);
        transaction.setEmailNotified(emailNotified);
        transaction.setRemarks(remarks);
        transaction.setStatus(status);
        transaction.setApproverUserId(approverUserId);
        transaction.setApproverNote(approverNote);
        transaction.setApproveDate(approveDate);
        transaction.setPaymentMethod(paymentMethod);
        transaction.setPaymentPeriod(paymentPeriod);
        transaction.setReasonForRejection(reasonForRejection);
        transaction.setSalaryScale(salaryScale);
        transaction.setRecordStatus(DRAFT);
        return transaction;
    }

    public EmployeeSalaryTransaction update() {
        EmployeeSalaryTransaction transaction = new EmployeeSalaryTransaction();
        transaction.setId(id);
        transaction.setPaymentDate(paymentDate);
        transaction.setBankBranchId(bankBranchId);
        transaction.setSmsNotified(smsNotified);
        transaction.setEmailNotified(emailNotified);
        transaction.setRemarks(remarks);
        transaction.setStatus(status);
        transaction.setApproverUserId(approverUserId);
        transaction.setApproverNote(approverNote);
        transaction.setApproveDate(approveDate);
        transaction.setPaymentMethod(paymentMethod);
        transaction.setPaymentPeriod(paymentPeriod);
        transaction.setReasonForRejection(reasonForRejection);
        transaction.setSalaryScale(salaryScale);
        transaction.setRecordStatus(ACTIVE);
        return transaction;
    }
}
