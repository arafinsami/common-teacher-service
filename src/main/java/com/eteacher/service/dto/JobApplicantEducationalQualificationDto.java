package com.eteacher.service.dto;

import com.eteacher.service.profile.JobApplicantEducationalQualificationProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class JobApplicantEducationalQualificationDto {

    @NotNull
    private Long applicantId;

    @Valid
    private List<JobApplicantEducationalQualificationProfile> qualifications;
}
