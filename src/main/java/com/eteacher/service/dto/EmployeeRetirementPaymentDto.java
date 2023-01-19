package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeRetirementPaymentProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@NoArgsConstructor
public class EmployeeRetirementPaymentDto {

    @NotNull
    private Long employeeId;

    private List<EmployeeRetirementPaymentProfile> profiles;
}
