package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeePensionTransactionProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeePensionTransactionDto {

    @NotNull
    private Long employeeId;

    @Valid
    private List<EmployeePensionTransactionProfile> profiles;
}
