package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeRetirementPaymentBreakdownProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class EmployeeRetirementPaymentBreakdownDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long paymentId;

    @Valid
    private EmployeeRetirementPaymentBreakdownProfile profile;
}
