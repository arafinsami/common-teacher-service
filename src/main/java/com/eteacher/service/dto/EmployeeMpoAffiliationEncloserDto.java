package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeMpoAffiliationEncloserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeMpoAffiliationEncloserDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long affiliationId;

    @Valid
    private List<EmployeeMpoAffiliationEncloserProfile> profiles;
}
