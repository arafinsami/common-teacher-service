package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.mpo.EmployeeMpoPaymentRequest;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeMpoPaymentRequestResponse {

    private Long id;

    private Boolean isApproved;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private PaymentPeriod paymentPeriod;

    private Long reasonForRejection;

    public static EmployeeMpoPaymentRequestResponse response(EmployeeMpoPaymentRequest request) {
        EmployeeMpoPaymentRequestResponse response = new EmployeeMpoPaymentRequestResponse();
        response.setId(request.getId());
        response.setIsApproved(request.getIsApproved());
        response.setApproverUserId(request.getApproverUserId());
        response.setApproverNote(request.getApproverNote());
        response.setApproveDate(request.getApproveDate());
        response.setPaymentPeriod(request.getPaymentPeriod());
        response.setReasonForRejection(request.getReasonForRejection());
        return response;
    }
}
