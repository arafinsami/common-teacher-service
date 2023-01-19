package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeMpoApplicationReviewerProfile;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeMpoApplicationReviewerDto {

    @NotNull
    private Long applicationId;

    @NotNull
    private Long employeeId;

    @Valid
    private EmployeeMpoApplicationReviewerProfile profile;
}
