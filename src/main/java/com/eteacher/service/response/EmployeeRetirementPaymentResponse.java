package com.eteacher.service.response;

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
public class EmployeeRetirementPaymentResponse {

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

    public static EmployeeRetirementPaymentResponse response(EmployeeRetirementPayment payment) {
        EmployeeRetirementPaymentResponse response = new EmployeeRetirementPaymentResponse();
        response.setId(payment.getId());
        response.setPaymentDate(payment.getPaymentDate());
        response.setBankAccountNumber(payment.getBankAccountNumber());
        response.setBankBranchId(payment.getBankBranchId());
        response.setRemarks(payment.getRemarks());
        response.setStatus(payment.getStatus());
        response.setApproverUserId(payment.getApproverUserId());
        response.setApproverNote(payment.getApproverNote());
        response.setApproveDate(payment.getApproveDate());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setPaymentPeriod(payment.getPaymentPeriod());
        response.setReasonForRejection(payment.getReasonForRejection());
        response.setSalaryScale(payment.getSalaryScale());
        return response;
    }
}
