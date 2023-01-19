package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeMpoSalaryExtraBenefitBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeMpoSalaryExtraBenefitBreakdownProfile {

    private Long id;

    @NotNull
    private Integer extraBenefitBreakdownSl;

    @NotNull
    private String extraBenefitBreakdownDescription;

    @NotNull
    private String extraBenefitBreakdownDescriptionBn;

    @NotNull
    private Double benefitAmount;

    public EmployeeMpoSalaryExtraBenefitBreakdown to() {
        EmployeeMpoSalaryExtraBenefitBreakdown breakdown = new EmployeeMpoSalaryExtraBenefitBreakdown();
        breakdown.setExtraBenefitBreakdownSl(extraBenefitBreakdownSl);
        breakdown.setExtraBenefitBreakdownDescription(extraBenefitBreakdownDescription);
        breakdown.setExtraBenefitBreakdownDescriptionBn(extraBenefitBreakdownDescriptionBn);
        breakdown.setBenefitAmount(benefitAmount);
        breakdown.setRecordStatus(DRAFT);
        return breakdown;
    }

    public EmployeeMpoSalaryExtraBenefitBreakdown update() {
        EmployeeMpoSalaryExtraBenefitBreakdown breakdown = new EmployeeMpoSalaryExtraBenefitBreakdown();
        breakdown.setId(id);
        breakdown.setExtraBenefitBreakdownSl(extraBenefitBreakdownSl);
        breakdown.setExtraBenefitBreakdownDescription(extraBenefitBreakdownDescription);
        breakdown.setExtraBenefitBreakdownDescriptionBn(extraBenefitBreakdownDescriptionBn);
        breakdown.setBenefitAmount(benefitAmount);
        breakdown.setRecordStatus(ACTIVE);
        return breakdown;
    }
}
