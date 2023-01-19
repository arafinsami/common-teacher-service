package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeePensionRequestProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeePensionRequestDto {

    @NotNull
    private Long employeeId;

    @Valid
    private List<EmployeePensionRequestProfile> profiles;
}
