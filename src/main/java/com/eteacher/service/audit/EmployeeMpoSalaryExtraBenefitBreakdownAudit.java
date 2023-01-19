package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeMpoSalaryExtraBenefitBreakdown;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EmployeeMpoSalaryExtraBenefitBreakdownAudit {

    private Long id;

    private Integer extraBenefitBreakdownSl;

    private String extraBenefitBreakdownDescription;

    private String extraBenefitBreakdownDescriptionBn;

    private Double benefitAmount;

    public static EmployeeMpoSalaryExtraBenefitBreakdownAudit audit(EmployeeMpoSalaryExtraBenefitBreakdown breakdown) {
        EmployeeMpoSalaryExtraBenefitBreakdownAudit audit = new EmployeeMpoSalaryExtraBenefitBreakdownAudit();
        audit.setId(breakdown.getId());
        audit.setExtraBenefitBreakdownSl(breakdown.getExtraBenefitBreakdownSl());
        audit.setExtraBenefitBreakdownDescription(breakdown.getExtraBenefitBreakdownDescription());
        audit.setExtraBenefitBreakdownDescriptionBn(breakdown.getExtraBenefitBreakdownDescriptionBn());
        audit.setBenefitAmount(breakdown.getBenefitAmount());
        return audit;
    }
}
