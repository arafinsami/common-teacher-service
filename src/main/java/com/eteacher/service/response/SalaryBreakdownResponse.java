package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class SalaryBreakdownResponse {

    private Long id;

    private String breakdownName;

    private String breakdownNameBn;

    private Boolean isArrear;

    private Boolean isDeduction;

    private String breakdownDescription;

    private String breakdownDescriptionBn;

    public static SalaryBreakdownResponse response(SalaryBreakdown salaryBreakdown) {
        SalaryBreakdownResponse response = new SalaryBreakdownResponse();
        response.setId(salaryBreakdown.getId());
        response.setBreakdownName(salaryBreakdown.getBreakdownName());
        response.setBreakdownNameBn(salaryBreakdown.getBreakdownNameBn());
        response.setIsArrear(salaryBreakdown.getIsArrear());
        response.setIsDeduction(salaryBreakdown.getIsDeduction());
        response.setBreakdownDescription(salaryBreakdown.getBreakdownDescription());
        response.setBreakdownDescriptionBn(salaryBreakdown.getBreakdownDescriptionBn());
        return response;
    }

    public static Map<String, Object> searchSalaryBreakdown(
            String salaryBreakdownName,
            String salaryBreakdownNameBn,
            Boolean isArrear,
            Boolean isDeduction) {
        Map<String, Object> map = new HashMap<>();
        map.put("salaryBreakdownName", salaryBreakdownName);
        map.put("salaryBreakdownNameBn", salaryBreakdownNameBn);
        map.put("isArrear", isArrear);
        map.put("isDeduction", isDeduction);
        return map;
    }
}
