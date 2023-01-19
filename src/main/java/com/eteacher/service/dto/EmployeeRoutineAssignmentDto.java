package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeRoutineAssignmentProfile;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeRoutineAssignmentDto {

    @NotNull
    private Long employee;

    @Valid
    private List<EmployeeRoutineAssignmentProfile> profiles;
}
