package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import lombok.Data;

import java.util.Date;

@Data
public class PaymentPeriodAudit {

    private Long id;

    private String paymentPeriodName;

    private String paymentPeriodNameBn;

    private Date startDate;

    private Date endDate;

    private Integer month;

    private Integer year;

    public static PaymentPeriodAudit audit(PaymentPeriod paymentPeriod) {
        PaymentPeriodAudit audit = new PaymentPeriodAudit();
        audit.setId(paymentPeriod.getId());
        audit.setPaymentPeriodName(paymentPeriod.getPaymentPeriodName());
        audit.setPaymentPeriodNameBn(paymentPeriod.getPaymentPeriodNameBn());
        audit.setStartDate(paymentPeriod.getStartDate());
        audit.setEndDate(paymentPeriod.getEndDate());
        audit.setMonth(paymentPeriod.getMonth());
        audit.setYear(paymentPeriod.getYear());
        return audit;
    }
}
