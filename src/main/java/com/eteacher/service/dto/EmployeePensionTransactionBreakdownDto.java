package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeePensionTransactionBreakdownProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EmployeePensionTransactionBreakdownDto {

    @NotNull
    private Long transactionId;

    @NotNull
    private Long employeeId;

    @Valid
    private EmployeePensionTransactionBreakdownProfile profile;
}
