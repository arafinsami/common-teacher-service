package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.SalaryScaleBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class SalaryScaleBreakdownResponse {

//  private Long id;

    private Double amount;

    private Double pensionAmount;

    private Long salaryScaleId;

    private Long salaryBreakdownId;

    public static SalaryScaleBreakdownResponse from(SalaryScaleBreakdown salaryScaleBreakdown) {
        SalaryScaleBreakdownResponse response = new SalaryScaleBreakdownResponse();
        response.setAmount(salaryScaleBreakdown.getAmount());
        response.setPensionAmount(salaryScaleBreakdown.getPensionAmount());
        response.setSalaryScaleId(salaryScaleBreakdown.getSalaryScaleBreakdownPK().getSalaryScaleId());
        response.setSalaryBreakdownId(salaryScaleBreakdown.getSalaryScaleBreakdownPK().getSalaryBreakdownId());
        return response;
    }

    public static Map<String, Object> searchSalaryScaleBreakdown(
            Double amount,
            Double pensionAmount,
            Long salaryBreakdownId,
            Long salaryScaleId) {
        Map<String, Object> map = new HashMap<>();
        map.put("amount", amount);
        map.put("pensionAmount", pensionAmount);
        map.put("salaryBreakdown", salaryBreakdownId);
        map.put("salaryScale", salaryScaleId);
        return map;
    }
}
