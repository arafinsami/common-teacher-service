package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeJobExperienceDetailProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeJobExperienceDetailDto {

    @NotNull
    private Long employee;

    @NotNull
    private List<EmployeeJobExperienceDetailProfile> jobExperienceDetails;
}
