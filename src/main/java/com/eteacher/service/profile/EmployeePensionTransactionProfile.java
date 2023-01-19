package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.PaymentMethod;
import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.govtteacher.EmployeePensionTransaction;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeePensionTransactionProfile {

    private Long id;

    private Date paymentDate;

    @NotBlank
    private String bankAccountNumber;

    @NotNull
    private Long bankBranchId;

    private Boolean smsNotified;

    private Boolean emailNotified;

    private String remarks;

    @NotNull
    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    @NotNull
    private Long paymentMethod;

    @NotNull
    private Long paymentPeriod;

    @NotNull
    private Long salaryScale;

    public EmployeePensionTransaction to() {
        EmployeePensionTransaction transaction = new EmployeePensionTransaction();
        transaction.setPaymentDate(paymentDate);
        transaction.setBankAccountNumber(bankAccountNumber);
        transaction.setBankBranchId(bankBranchId);
        transaction.setSmsNotified(smsNotified);
        transaction.setRemarks(remarks);
        transaction.setStatus(status);
        transaction.setApproverUserId(approverUserId);
        transaction.setApproverNote(approverNote);
        transaction.setApproveDate(approveDate);
        transaction.setReasonForRejection(reasonForRejection);
        transaction.setRecordStatus(DRAFT);
        return transaction;
    }

    public EmployeePensionTransaction update() {
        EmployeePensionTransaction transaction = new EmployeePensionTransaction();
        transaction.setId(id);
        transaction.setPaymentDate(paymentDate);
        transaction.setBankAccountNumber(bankAccountNumber);
        transaction.setBankBranchId(bankBranchId);
        transaction.setSmsNotified(smsNotified);
        transaction.setRemarks(remarks);
        transaction.setStatus(status);
        transaction.setApproverUserId(approverUserId);
        transaction.setApproverNote(approverNote);
        transaction.setApproveDate(approveDate);
        transaction.setReasonForRejection(reasonForRejection);
        transaction.setRecordStatus(ACTIVE);
        return transaction;
    }
}
