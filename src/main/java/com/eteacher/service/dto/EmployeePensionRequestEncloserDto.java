package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeePensionRequestEncloserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeePensionRequestEncloserDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long pensionRequestId;

    @Valid
    private List<EmployeePensionRequestEncloserProfile> profiles;
}
