package com.eteacher.service.audit;

import com.eteacher.service.entity.job.JobApplicantJobExperience;
import com.eteacher.service.enums.JobNature;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class JobApplicantExperienceAudit {

    private Long id;

    private Long jobApplicantJobExperienceSl;

    private String organizationName;

    private String organizationAddress;

    private String designationName;

    private Date dateOfJoining;

    private Date dateOfRelease;

    private Boolean isPresent;

    private JobNature jobNature;

    public static JobApplicantExperienceAudit audit(JobApplicantJobExperience experience) {
        JobApplicantExperienceAudit audit = new JobApplicantExperienceAudit();
        audit.setId(experience.getId());
        experience.setJobApplicantJobExperienceSl(experience.getJobApplicantJobExperienceSl());
        experience.setOrganizationName(experience.getOrganizationName());
        experience.setOrganizationAddress(experience.getOrganizationAddress());
        experience.setDesignationName(experience.getDesignationName());
        experience.setDateOfJoining(experience.getDateOfRelease());
        experience.setDateOfRelease(experience.getDateOfRelease());
        experience.setIsPresent(experience.getIsPresent());
        experience.setJobNature(experience.getJobNature());
        return audit;
    }
}
