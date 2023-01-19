package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeMpoPaymentRequestBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EmployeeMpoPaymentRequestBreakdownProfile {

    private Long id;

    @NotNull
    private Integer salaryBreakdownId;

    @NotNull
    private Double mpoAmount;

    public EmployeeMpoPaymentRequestBreakdown to() {
        EmployeeMpoPaymentRequestBreakdown breakdown = new EmployeeMpoPaymentRequestBreakdown();
        breakdown.setId(id);
        breakdown.setSalaryBreakdownId(salaryBreakdownId);
        breakdown.setMpoAmount(mpoAmount);
        return breakdown;
    }
}
