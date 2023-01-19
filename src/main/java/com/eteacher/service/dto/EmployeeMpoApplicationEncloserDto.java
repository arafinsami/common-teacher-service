package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeMpoApplicationEncloserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeMpoApplicationEncloserDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long applicationId;

    @Valid
    private List<EmployeeMpoApplicationEncloserProfile> profiles;
}
