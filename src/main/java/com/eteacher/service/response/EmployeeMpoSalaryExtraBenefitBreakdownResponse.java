package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeMpoSalaryExtraBenefitBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeMpoSalaryExtraBenefitBreakdownResponse {

    private Long id;

    private Integer extraBenefitBreakdownSl;

    private String extraBenefitBreakdownDescription;

    private String extraBenefitBreakdownDescriptionBn;

    private Double benefitAmount;

    public static EmployeeMpoSalaryExtraBenefitBreakdownResponse response(EmployeeMpoSalaryExtraBenefitBreakdown breakdown) {
        EmployeeMpoSalaryExtraBenefitBreakdownResponse response = new EmployeeMpoSalaryExtraBenefitBreakdownResponse();
        response.setId(breakdown.getId());
        response.setExtraBenefitBreakdownSl(breakdown.getExtraBenefitBreakdownSl());
        response.setExtraBenefitBreakdownDescription(breakdown.getExtraBenefitBreakdownDescription());
        response.setExtraBenefitBreakdownDescriptionBn(breakdown.getExtraBenefitBreakdownDescriptionBn());
        response.setBenefitAmount(breakdown.getBenefitAmount());
        return response;
    }
}
