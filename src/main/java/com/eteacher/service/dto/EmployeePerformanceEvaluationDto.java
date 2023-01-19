package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeePerformanceEvaluationProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeePerformanceEvaluationDto {

    @NotNull
    private Long employeeId;

    @Valid
    private List<EmployeePerformanceEvaluationProfile> profiles;
}
