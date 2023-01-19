package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.mpo.ArrearType;
import com.eteacher.service.entity.mpo.EmployeePaymentArrear;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeePaymentArrearAudit {

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

    public static EmployeePaymentArrearAudit audit(EmployeePaymentArrear arrear) {
        EmployeePaymentArrearAudit audit = new EmployeePaymentArrearAudit();
        audit.setId(arrear.getApproverUserId());
        audit.setArrearDateFrom(arrear.getArrearDateFrom());
        audit.setArrearDateTo(arrear.getArrearDateTo());
        audit.setIsApproved(arrear.getIsApproved());
        audit.setApproverUserId(arrear.getApproverUserId());
        audit.setApproverNote(arrear.getApproverNote());
        audit.setApproveDate(arrear.getApproveDate());
        audit.setArrearType(arrear.getArrearType());
        audit.setPaymentPeriod(arrear.getPaymentPeriod());
        audit.setReasonForRejection(arrear.getReasonForRejection());
        return audit;
    }
}
