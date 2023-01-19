package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeMpoAffiliationProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@NoArgsConstructor
public class EmployeeMpoAffiliationDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private List<EmployeeMpoAffiliationProfile> profiles;
}
