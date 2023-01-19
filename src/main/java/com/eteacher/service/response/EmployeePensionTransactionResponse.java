package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.PaymentMethod;
import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.govtteacher.EmployeePensionTransaction;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeePensionTransactionResponse {

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

    private String paymentMethod;

    private String paymentPeriod;

    private Long reasonForRejection;

    private String salaryScale;

    public static EmployeePensionTransactionResponse response(EmployeePensionTransaction transaction) {
        EmployeePensionTransactionResponse response = new EmployeePensionTransactionResponse();
        response.setId(transaction.getId());
        response.setPaymentDate(transaction.getPaymentDate());
        response.setBankAccountNumber(transaction.getBankAccountNumber());
        response.setBankBranchId(transaction.getBankBranchId());
        response.setSmsNotified(transaction.getSmsNotified());
        response.setRemarks(transaction.getRemarks());
        response.setStatus(transaction.getStatus());
        response.setApproverUserId(transaction.getApproverUserId());
        response.setApproverNote(transaction.getApproverNote());
        response.setApproveDate(transaction.getApproveDate());
        response.setPaymentMethod(transaction.getPaymentMethod().getPaymentMethodName());
        response.setPaymentPeriod(transaction.getPaymentPeriod().getPaymentPeriodName());
        response.setReasonForRejection(transaction.getReasonForRejection());
        response.setSalaryScale(transaction.getSalaryScale().getSalaryScaleName());
        return response;
    }
}
