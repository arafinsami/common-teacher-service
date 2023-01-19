package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeJoiningEncloserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeJoiningEncloserDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long joiningId;

    @Valid
    private List<EmployeeJoiningEncloserProfile> profiles;

}
