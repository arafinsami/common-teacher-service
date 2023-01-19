package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeMpoAffiliationReviewerProfile;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeMpoAffiliationReviewerDto {

    @NotNull
    private Long mpoAffiliationId;

    @NotNull
    private Long employeeId;

    @Valid
    private EmployeeMpoAffiliationReviewerProfile profile;
}
