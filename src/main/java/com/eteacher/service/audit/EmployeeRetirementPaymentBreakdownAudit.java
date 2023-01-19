package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeRetirementPaymentBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeRetirementPaymentBreakdownAudit {

    private Long id;

    private Integer salaryBreakdownId;

    private Double amount;

    public static EmployeeRetirementPaymentBreakdownAudit audit(EmployeeRetirementPaymentBreakdown breakdown) {
        EmployeeRetirementPaymentBreakdownAudit audit = new EmployeeRetirementPaymentBreakdownAudit();
        audit.setId(breakdown.getId());
        audit.setSalaryBreakdownId(breakdown.getSalaryBreakdownId());
        audit.setAmount(breakdown.getAmount());
        return audit;
    }
}
