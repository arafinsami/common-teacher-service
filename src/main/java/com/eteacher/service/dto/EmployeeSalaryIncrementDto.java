package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeSalaryIncrementProfile;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EmployeeSalaryIncrementDto {

    @NotNull
    private Long employee;

    @NotNull
    private List<EmployeeSalaryIncrementProfile> salaryIncrements;
}
