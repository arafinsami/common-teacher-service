package com.eteacher.service.dto;

import com.eteacher.service.profile.DepartmentalExaminationProfile;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DepartmentalExaminationDto {

    @NotNull
    private Long departmentId;

    @Valid
    private List<DepartmentalExaminationProfile> profiles;
}
