package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class PaymentPeriodDto {

    private Long id;

    @NotBlank
    private String paymentPeriodName;

    @NotBlank
    private String paymentPeriodNameBn;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    @NotNull
    private Integer month;

    @NotNull
    private Integer year;

    public PaymentPeriod to() {
        PaymentPeriod paymentPeriod = new PaymentPeriod();
        paymentPeriod.setPaymentPeriodName(paymentPeriodName);
        paymentPeriod.setPaymentPeriodNameBn(paymentPeriodNameBn);
        paymentPeriod.setStartDate(startDate);
        paymentPeriod.setEndDate(endDate);
        paymentPeriod.setMonth(month);
        paymentPeriod.setYear(year);
        return paymentPeriod;
    }

    public void update(PaymentPeriod paymentPeriod) {
        paymentPeriod.setPaymentPeriodName(paymentPeriodName);
        paymentPeriod.setPaymentPeriodNameBn(paymentPeriodNameBn);
        paymentPeriod.setStartDate(startDate);
        paymentPeriod.setEndDate(endDate);
        paymentPeriod.setMonth(month);
        paymentPeriod.setYear(year);
    }
}
