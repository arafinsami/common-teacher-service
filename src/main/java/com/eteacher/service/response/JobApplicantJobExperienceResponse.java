package com.eteacher.service.response;

import com.eteacher.service.entity.job.JobApplicantJobExperience;
import com.eteacher.service.enums.JobNature;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class JobApplicantJobExperienceResponse {

    private Long id;

    private Long jobApplicantJobExperienceSl;

    private String organizationName;

    private String organizationAddress;

    private String designationName;

    private Date dateOfJoining;

    private Date dateOfRelease;

    private Boolean isPresent;

    private JobNature jobNature;

    public static JobApplicantJobExperienceResponse response(JobApplicantJobExperience experience) {
        JobApplicantJobExperienceResponse response = new JobApplicantJobExperienceResponse();
        response.setId(experience.getId());
        response.setJobApplicantJobExperienceSl(experience.getJobApplicantJobExperienceSl());
        response.setOrganizationName(experience.getOrganizationName());
        response.setOrganizationAddress(experience.getOrganizationAddress());
        response.setDesignationName(experience.getDesignationName());
        response.setDateOfJoining(experience.getDateOfRelease());
        response.setDateOfRelease(experience.getDateOfRelease());
        response.setIsPresent(experience.getIsPresent());
        response.setJobNature(experience.getJobNature());
        return response;
    }
}
