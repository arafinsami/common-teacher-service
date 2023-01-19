package com.eteacher.service.audit;

import com.eteacher.service.entity.govtteacher.EmployeePensionTransactionBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeePensionTransactionBreakdownAudit {

    private Long id;

    private Integer salaryBreakdownId;

    private Double pensionAmount;

    public static EmployeePensionTransactionBreakdownAudit audit(EmployeePensionTransactionBreakdown breakdown) {
        EmployeePensionTransactionBreakdownAudit audit = new EmployeePensionTransactionBreakdownAudit();
        audit.setId(breakdown.getId());
        audit.setSalaryBreakdownId(breakdown.getSalaryBreakdownId());
        audit.setPensionAmount(breakdown.getPensionAmount());
        return audit;
    }
}
