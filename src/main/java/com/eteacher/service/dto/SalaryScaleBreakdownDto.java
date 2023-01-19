package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.SalaryScaleBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SalaryScaleBreakdownDto {

    private Long id;

    @NotNull
    private Double amount;

    @NotNull
    private Double pensionAmount;

    @NotNull
    private Long salaryScaleId;

    @NotNull
    private Long salaryBreakDownId;

    public SalaryScaleBreakdown to() {
        SalaryScaleBreakdown salaryScaleBreakdown = new SalaryScaleBreakdown();
        //salaryScaleBreakdown.setSalaryScale(request.getSalaryScaleId().);
        // salaryScaleBreakdown.setSalaryScaleBreakdownPK(request.);
        salaryScaleBreakdown.setAmount(amount);
        salaryScaleBreakdown.setPensionAmount(pensionAmount);
        return salaryScaleBreakdown;
    }

    public void update(SalaryScaleBreakdown salaryScaleBreakdown) {
        salaryScaleBreakdown.setAmount(amount);
        salaryScaleBreakdown.setPensionAmount(pensionAmount);
    }
}
