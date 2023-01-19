package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeSalaryTransactionBreakdownProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Data
@NoArgsConstructor
public class EmployeeSalaryTransactionBreakdownDto {

    private Long transactionId;

    private Long employeeId;

    @Valid
    private EmployeeSalaryTransactionBreakdownProfile profile;
}
