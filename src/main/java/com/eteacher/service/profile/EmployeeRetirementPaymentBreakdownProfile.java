package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeRetirementPaymentBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeRetirementPaymentBreakdownProfile {

    private Long id;

    private Integer salaryBreakdownId;

    private Double amount;

    public EmployeeRetirementPaymentBreakdown to() {
        EmployeeRetirementPaymentBreakdown breakdown = new EmployeeRetirementPaymentBreakdown();
        breakdown.setSalaryBreakdownId(salaryBreakdownId);
        breakdown.setAmount(amount);
        breakdown.setRecordStatus(DRAFT);
        return breakdown;
    }

    public EmployeeRetirementPaymentBreakdown update() {
        EmployeeRetirementPaymentBreakdown breakdown = new EmployeeRetirementPaymentBreakdown();
        breakdown.setId(id);
        breakdown.setSalaryBreakdownId(salaryBreakdownId);
        breakdown.setAmount(amount);
        breakdown.setRecordStatus(ACTIVE);
        return breakdown;
    }
}
