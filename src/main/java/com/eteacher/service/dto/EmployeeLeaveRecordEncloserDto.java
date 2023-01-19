package com.eteacher.service.dto;


import com.eteacher.service.profile.EmployeeLeaveRecordEncloserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeLeaveRecordEncloserDto {

    @NotNull
    private Long employeeId;

    @Valid
    private List<EmployeeLeaveRecordEncloserProfile> profiles;
}
