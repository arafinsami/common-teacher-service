package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.mpo.EmployeeMpoSalaryExtraBenefit;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeMpoSalaryExtraBenefitResponse {

    private Long id;

    private Boolean isApproved;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private PaymentPeriod paymentPeriod;

    private Long reasonForRejection;

    public static EmployeeMpoSalaryExtraBenefitResponse response(EmployeeMpoSalaryExtraBenefit benefit) {
        EmployeeMpoSalaryExtraBenefitResponse response = new EmployeeMpoSalaryExtraBenefitResponse();
        response.setId(benefit.getId());
        response.setIsApproved(benefit.getIsApproved());
        response.setApproverUserId(benefit.getApproverUserId());
        response.setApproverNote(benefit.getApproverNote());
        response.setApproveDate(benefit.getApproveDate());
        response.setPaymentPeriod(benefit.getPaymentPeriod());
        response.setReasonForRejection(benefit.getReasonForRejection());
        return response;
    }
}
