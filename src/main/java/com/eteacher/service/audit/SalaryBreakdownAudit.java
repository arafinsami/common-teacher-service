package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import lombok.Data;

@Data
public class SalaryBreakdownAudit {

    private Long id;

    private String breakdownName;

    private String breakdownNameBn;

    private String breakdownDescription;

    private String breakdownDescriptionBn;

    private Boolean isArrear;

    private Boolean isDeduction;

    public static SalaryBreakdownAudit audit(SalaryBreakdown salaryBreakdown) {
        SalaryBreakdownAudit audit = new SalaryBreakdownAudit();
        audit.setBreakdownName(salaryBreakdown.getBreakdownName());
        audit.setBreakdownNameBn(salaryBreakdown.getBreakdownNameBn());
        audit.setBreakdownDescription(salaryBreakdown.getBreakdownDescription());
        audit.setBreakdownDescriptionBn(salaryBreakdown.getBreakdownDescriptionBn());
        audit.setIsArrear(salaryBreakdown.getIsArrear());
        audit.setIsDeduction(salaryBreakdown.getIsDeduction());
        return audit;
    }
}
