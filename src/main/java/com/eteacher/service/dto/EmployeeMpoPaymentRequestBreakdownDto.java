package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeMpoPaymentRequestBreakdownProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EmployeeMpoPaymentRequestBreakdownDto {

    @NotNull
    private Long paymentId;

    @NotNull
    private Long employeeId;

    @Valid
    private EmployeeMpoPaymentRequestBreakdownProfile salaryBreakdown;
}
