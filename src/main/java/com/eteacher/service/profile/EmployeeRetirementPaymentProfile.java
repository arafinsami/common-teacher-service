package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.PaymentMethod;
import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.mpo.EmployeeRetirementPayment;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;


@Data
@NoArgsConstructor
public class EmployeeRetirementPaymentProfile {

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

    public EmployeeRetirementPayment to() {
        EmployeeRetirementPayment payment = new EmployeeRetirementPayment();
        payment.setPaymentDate(paymentDate);
        payment.setBankAccountNumber(bankAccountNumber);
        payment.setBankBranchId(bankBranchId);
        payment.setRemarks(remarks);
        payment.setStatus(status);
        payment.setApproverUserId(approverUserId);
        payment.setApproverNote(approverNote);
        payment.setApproveDate(approveDate);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentPeriod(paymentPeriod);
        payment.setReasonForRejection(reasonForRejection);
        payment.setSalaryScale(salaryScale);
        payment.setRecordStatus(DRAFT);
        return payment;
    }

    public EmployeeRetirementPayment update() {
        EmployeeRetirementPayment payment = new EmployeeRetirementPayment();
        payment.setId(id);
        payment.setPaymentDate(paymentDate);
        payment.setBankAccountNumber(bankAccountNumber);
        payment.setBankBranchId(bankBranchId);
        payment.setRemarks(remarks);
        payment.setStatus(status);
        payment.setApproverUserId(approverUserId);
        payment.setApproverNote(approverNote);
        payment.setApproveDate(approveDate);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentPeriod(paymentPeriod);
        payment.setReasonForRejection(reasonForRejection);
        payment.setSalaryScale(salaryScale);
        payment.setRecordStatus(ACTIVE);
        return payment;
    }
}
