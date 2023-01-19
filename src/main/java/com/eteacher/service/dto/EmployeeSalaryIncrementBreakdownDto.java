package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeSalaryIncrementBreakdownProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EmployeeSalaryIncrementBreakdownDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long salaryId;

    private EmployeeSalaryIncrementBreakdownProfile profile;
}
