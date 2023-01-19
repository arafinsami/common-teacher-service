package com.eteacher.service.dto;

import com.eteacher.service.profile.JobApplicantExperienceProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class JobApplicantJobExperienceDto {

    @NotNull
    private Long applicantId;

    @Valid
    private List<JobApplicantExperienceProfile> experiences;
}
