package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeRetirementPaymentEncloserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeRetirementPaymentEncloserDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long paymentId;

    @Valid
    private List<EmployeeRetirementPaymentEncloserProfile> profiles;
}
