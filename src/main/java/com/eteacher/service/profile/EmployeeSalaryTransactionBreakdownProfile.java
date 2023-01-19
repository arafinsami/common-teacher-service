package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeSalaryTransactionBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeSalaryTransactionBreakdownProfile {

    private Long id;

    private Integer salaryBreakdownId;

    private Double amount;

    public EmployeeSalaryTransactionBreakdown to() {
        EmployeeSalaryTransactionBreakdown breakdown = new EmployeeSalaryTransactionBreakdown();
        breakdown.setAmount(amount);
        breakdown.setRecordStatus(DRAFT);
        return breakdown;
    }

    public EmployeeSalaryTransactionBreakdown update() {
        EmployeeSalaryTransactionBreakdown breakdown = new EmployeeSalaryTransactionBreakdown();
        breakdown.setId(id);
        breakdown.setAmount(amount);
        breakdown.setRecordStatus(ACTIVE);
        return breakdown;
    }
}
