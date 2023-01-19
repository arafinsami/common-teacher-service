package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class SalaryBreakdownDto {

    private Long id;

    @NotBlank
    private String breakdownName;

    @NotBlank
    private String breakdownNameBn;

    private Boolean isArrear;

    private Boolean isDeduction;

    private String breakdownDescription;

    private String breakdownDescriptionBn;

    public SalaryBreakdown to() {
        SalaryBreakdown salaryBreakdown = new SalaryBreakdown();
        salaryBreakdown.setBreakdownName(breakdownName);
        salaryBreakdown.setBreakdownNameBn(breakdownNameBn);
        salaryBreakdown.setIsArrear(isArrear);
        salaryBreakdown.setIsDeduction(isDeduction);
        salaryBreakdown.setBreakdownDescription(breakdownDescription);
        salaryBreakdown.setBreakdownDescriptionBn(breakdownDescriptionBn);
        return salaryBreakdown;
    }

    public void update(SalaryBreakdown salaryBreakdown) {
        salaryBreakdown.setBreakdownName(breakdownName);
        salaryBreakdown.setBreakdownNameBn(breakdownNameBn);
        salaryBreakdown.setIsArrear(isArrear);
        salaryBreakdown.setIsDeduction(isDeduction);
        salaryBreakdown.setBreakdownDescription(breakdownDescription);
        salaryBreakdown.setBreakdownDescriptionBn(breakdownDescriptionBn);
    }
}
