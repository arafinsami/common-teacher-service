package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeDepartmentExamMeritScoreProfile;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EmployeeDepartmentalExamScoreDto {

    @NotNull
    private Long employeeId;

    @Valid
    private List<EmployeeDepartmentExamMeritScoreProfile> meritScores;
}
