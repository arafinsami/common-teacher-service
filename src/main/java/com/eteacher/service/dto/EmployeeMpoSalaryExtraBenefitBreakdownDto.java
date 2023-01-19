package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeMpoSalaryExtraBenefitBreakdownProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EmployeeMpoSalaryExtraBenefitBreakdownDto {

    @NotNull
    private Long salaryId;

    @NotNull
    private Long employeeId;

    @Valid
    private EmployeeMpoSalaryExtraBenefitBreakdownProfile profile;
}
