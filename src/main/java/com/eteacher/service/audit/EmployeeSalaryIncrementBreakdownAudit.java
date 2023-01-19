package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import com.eteacher.service.entity.mpo.EmployeeSalaryIncrementBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EmployeeSalaryIncrementBreakdownAudit {

    private Long id;

    private Double amount;

    private SalaryBreakdown salaryBreakdown;

    public static EmployeeSalaryIncrementBreakdownAudit audit(EmployeeSalaryIncrementBreakdown breakdown) {
        EmployeeSalaryIncrementBreakdownAudit audit = new EmployeeSalaryIncrementBreakdownAudit();
        audit.setId(breakdown.getId());
        audit.setAmount(breakdown.getAmount());
        audit.setSalaryBreakdown(breakdown.getSalaryBreakdown());
        return audit;
    }
}
