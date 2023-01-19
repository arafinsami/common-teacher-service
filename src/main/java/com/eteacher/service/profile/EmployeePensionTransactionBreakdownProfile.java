package com.eteacher.service.profile;

import com.eteacher.service.entity.govtteacher.EmployeePensionTransactionBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeePensionTransactionBreakdownProfile {

    private Long id;

    @NotNull
    private Integer salaryBreakdownId;

    @NotNull
    private Double pensionAmount;

    public EmployeePensionTransactionBreakdown to() {
        EmployeePensionTransactionBreakdown breakdown = new EmployeePensionTransactionBreakdown();
        breakdown.setSalaryBreakdownId(salaryBreakdownId);
        breakdown.setPensionAmount(pensionAmount);
        breakdown.setRecordStatus(DRAFT);
        return breakdown;
    }

    public EmployeePensionTransactionBreakdown update() {
        EmployeePensionTransactionBreakdown breakdown = new EmployeePensionTransactionBreakdown();
        breakdown.setId(id);
        breakdown.setSalaryBreakdownId(salaryBreakdownId);
        breakdown.setPensionAmount(pensionAmount);
        breakdown.setRecordStatus(ACTIVE);
        return breakdown;
    }
}
