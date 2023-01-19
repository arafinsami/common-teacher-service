package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class PaymentPeriodResponse {

    private Long id;

    private String paymentPeriodName;

    private String paymentPeriodNameBn;

    private Date startDate;

    private Date endDate;

    private Integer month;

    private Integer year;

    public static PaymentPeriodResponse response(PaymentPeriod paymentPeriod) {
        PaymentPeriodResponse response = new PaymentPeriodResponse();
        response.setId(paymentPeriod.getId());
        response.setPaymentPeriodName(paymentPeriod.getPaymentPeriodName());
        response.setPaymentPeriodNameBn(paymentPeriod.getPaymentPeriodNameBn());
        response.setStartDate(paymentPeriod.getStartDate());
        response.setEndDate(paymentPeriod.getEndDate());
        response.setMonth(paymentPeriod.getMonth());
        response.setYear(paymentPeriod.getYear());
        return response;
    }

    public static Map<String, Object> searchPaymentPeriod(
            String paymentPeriodName,
            String paymentPeriodNameBn,
            String startDate,
            String endDate,
            Integer month,
            Integer year) {
        Map<String, Object> map = new HashMap<>();
        map.put("paymentPeriodName", paymentPeriodName);
        map.put("paymentPeriodNameBn", paymentPeriodNameBn);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("month", month);
        map.put("year", year);
        return map;
    }
}
