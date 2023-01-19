package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import com.eteacher.service.entity.mpo.EmployeeSalaryIncrementBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeSalaryIncrementBreakdownProfile {

    private Long id;

    private Double amount;

    private SalaryBreakdown salaryBreakdown;

    public EmployeeSalaryIncrementBreakdown to() {
        EmployeeSalaryIncrementBreakdown breakdown = new EmployeeSalaryIncrementBreakdown();
        breakdown.setAmount(amount);
        breakdown.setSalaryBreakdown(salaryBreakdown);
        breakdown.setRecordStatus(DRAFT);
        return breakdown;
    }

    public EmployeeSalaryIncrementBreakdown update() {
        EmployeeSalaryIncrementBreakdown breakdown = new EmployeeSalaryIncrementBreakdown();
        breakdown.setId(id);
        breakdown.setAmount(amount);
        breakdown.setSalaryBreakdown(salaryBreakdown);
        breakdown.setRecordStatus(ACTIVE);
        return breakdown;
    }
}
