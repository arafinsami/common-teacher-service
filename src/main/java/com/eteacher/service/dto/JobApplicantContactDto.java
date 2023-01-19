package com.eteacher.service.dto;

import com.eteacher.service.profile.JobApplicantContactProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class JobApplicantContactDto {

    @NotNull
    private Long applicantId;

    @Valid
    private List<JobApplicantContactProfile> contacts;
}
