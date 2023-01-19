package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeSalaryTransactionBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeSalaryTransactionBreakdownAudit {

    private Long id;

    private Integer salaryBreakdownId;

    private Double amount;

    public static EmployeeSalaryTransactionBreakdownAudit audit(EmployeeSalaryTransactionBreakdown breakdown) {
        EmployeeSalaryTransactionBreakdownAudit audit = new EmployeeSalaryTransactionBreakdownAudit();
        audit.setId(breakdown.getId());
        audit.setAmount(breakdown.getAmount());
        return audit;
    }
}
