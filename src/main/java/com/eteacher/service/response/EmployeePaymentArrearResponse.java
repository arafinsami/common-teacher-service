package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.mpo.ArrearType;
import com.eteacher.service.entity.mpo.EmployeePaymentArrear;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeePaymentArrearResponse {

    private Long id;

    private Date arrearDateFrom;

    private Date arrearDateTo;

    private Boolean isApproved;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private ArrearType arrearType;

    private PaymentPeriod paymentPeriod;

    private Long reasonForRejection;

    public static EmployeePaymentArrearResponse response(EmployeePaymentArrear arrear) {
        EmployeePaymentArrearResponse response = new EmployeePaymentArrearResponse();
        response.setId(arrear.getApproverUserId());
        response.setArrearDateFrom(arrear.getArrearDateFrom());
        response.setArrearDateTo(arrear.getArrearDateTo());
        response.setIsApproved(arrear.getIsApproved());
        response.setApproverUserId(arrear.getApproverUserId());
        response.setApproverNote(arrear.getApproverNote());
        response.setApproveDate(arrear.getApproveDate());
        response.setArrearType(arrear.getArrearType());
        response.setPaymentPeriod(arrear.getPaymentPeriod());
        response.setReasonForRejection(arrear.getReasonForRejection());
        return response;
    }
}
