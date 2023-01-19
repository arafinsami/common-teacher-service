package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeEducationalQualificationProfile;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EmployeeEducationalQualificationDto {

    @NotNull
    private Long employee;

    @NotNull
    private List<EmployeeEducationalQualificationProfile> qualifications;
}
