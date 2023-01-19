package com.eteacher.service.response;

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
public class EmployeeSalaryTransactionResponse {

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

    public static EmployeeSalaryTransactionResponse response(EmployeeSalaryTransaction transaction) {
        EmployeeSalaryTransactionResponse response = new EmployeeSalaryTransactionResponse();
        response.setId(transaction.getId());
        response.setPaymentDate(transaction.getPaymentDate());
        response.setBankBranchId(transaction.getBankBranchId());
        response.setSmsNotified(transaction.getSmsNotified());
        response.setEmailNotified(transaction.getEmailNotified());
        response.setRemarks(transaction.getRemarks());
        response.setStatus(transaction.getStatus());
        response.setApproverUserId(transaction.getApproverUserId());
        response.setApproverNote(transaction.getApproverNote());
        response.setApproveDate(transaction.getApproveDate());
        response.setPaymentMethod(transaction.getPaymentMethod());
        response.setPaymentPeriod(transaction.getPaymentPeriod());
        response.setReasonForRejection(transaction.getReasonForRejection());
        response.setSalaryScale(transaction.getSalaryScale());
        return response;
    }
}
