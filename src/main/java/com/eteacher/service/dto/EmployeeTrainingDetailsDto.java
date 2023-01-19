package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeTrainingDetailsProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeTrainingDetailsDto {

    @NotNull
    private Long employee;

    @Valid
    private List<EmployeeTrainingDetailsProfile> profiles;
}
