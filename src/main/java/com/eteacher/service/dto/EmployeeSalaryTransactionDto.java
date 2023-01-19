package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeSalaryTransactionProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeSalaryTransactionDto {

    @NotNull
    private Long employeeId;

    @Valid
    private List<EmployeeSalaryTransactionProfile> profiles;
}
