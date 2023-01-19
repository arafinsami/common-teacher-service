package com.eteacher.service.profile;

import com.eteacher.service.entity.job.JobApplicantJobExperience;
import com.eteacher.service.enums.JobNature;
import lombok.Data;

import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
public class JobApplicantExperienceProfile {

    private Long id;

    private Long experienceSl;

    private String organizationName;

    private String organizationAddress;

    private String designationName;

    private Date dateOfJoining;

    private Date dateOfRelease;

    private Boolean isPresent;

    private JobNature jobNature;

    public JobApplicantJobExperience to() {
        JobApplicantJobExperience experience = new JobApplicantJobExperience();
        experience.setJobApplicantJobExperienceSl(experienceSl);
        experience.setOrganizationName(organizationName);
        experience.setOrganizationAddress(organizationAddress);
        experience.setDesignationName(designationName);
        experience.setDateOfJoining(dateOfJoining);
        experience.setDateOfRelease(dateOfRelease);
        experience.setIsPresent(isPresent);
        experience.setJobNature(jobNature);
        experience.setRecordStatus(DRAFT);
        return experience;
    }

    public JobApplicantJobExperience update() {
        JobApplicantJobExperience experience = new JobApplicantJobExperience();
        experience.setId(id);
        experience.setJobApplicantJobExperienceSl(experienceSl);
        experience.setOrganizationName(organizationName);
        experience.setOrganizationAddress(organizationAddress);
        experience.setDesignationName(designationName);
        experience.setDateOfJoining(dateOfJoining);
        experience.setDateOfRelease(dateOfRelease);
        experience.setIsPresent(isPresent);
        experience.setJobNature(jobNature);
        experience.setRecordStatus(ACTIVE);
        return experience;
    }
}
