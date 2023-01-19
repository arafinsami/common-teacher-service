package com.eteacher.service.helper;

import com.eteacher.service.entity.job.JobApplicant;
import com.eteacher.service.entity.job.JobApplicantContact;
import com.eteacher.service.entity.job.JobApplicantEducationalQualification;
import com.eteacher.service.entity.job.JobApplicantJobExperience;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.JobApplicantContactProfile;
import com.eteacher.service.profile.JobApplicantEducationalQualificationProfile;
import com.eteacher.service.profile.JobApplicantExperienceProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.*;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class JobApplicantHelper {

    @Resource
    private ActiveUserContext context;

    public void getSaveData(JobApplicant applicant) {
        applicant.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        applicant.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(JobApplicant applicant, RecordStatus status) {
        applicant.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        applicant.setRecordStatus(status);
    }

    public Function<JobApplicantExperienceProfile, JobApplicantJobExperience> saveExperienceProfile = e -> {
        JobApplicantJobExperience experience = e.to();
        experience.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return experience;
    };

    public Function<JobApplicantExperienceProfile, JobApplicantJobExperience> updateExperienceProfile = e -> {
        JobApplicantJobExperience experience = e.update();
        experience.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return experience;
    };

    public Function<JobApplicantEducationalQualificationProfile, JobApplicantEducationalQualification> saveQualificationProfile = e -> {
        JobApplicantEducationalQualification qualification = e.to();
        qualification.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return qualification;
    };

    public Function<JobApplicantEducationalQualificationProfile, JobApplicantEducationalQualification> updateQualificationProfile = e -> {
        JobApplicantEducationalQualification qualification = e.update();
        qualification.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return qualification;
    };

    public Function<JobApplicantContactProfile, JobApplicantContact> saveContactProfile = c -> {
        JobApplicantContact contact = c.to();
        contact.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return contact;
    };

    public Function<JobApplicantContactProfile, JobApplicantContact> updateContactProfile = c -> {
        JobApplicantContact contact = c.update();
        contact.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return contact;
    };

    public List<JobApplicantJobExperience> saveExperiences(List<JobApplicantExperienceProfile> lists) {
        List<JobApplicantJobExperience> experiences = lists
                .stream()
                .map(saveExperienceProfile)
                .collect(Collectors.toList());
        return experiences;
    }

    public List<JobApplicantJobExperience> updateExperiences(List<JobApplicantExperienceProfile> lists) {
        List<JobApplicantJobExperience> experiences = lists
                .stream()
                .map(updateExperienceProfile)
                .collect(Collectors.toList());
        return experiences;
    }

    public List<JobApplicantEducationalQualification> saveQualifications(List<JobApplicantEducationalQualificationProfile> lists) {
        List<JobApplicantEducationalQualification> qualifications = lists
                .stream()
                .map(saveQualificationProfile)
                .collect(Collectors.toList());
        return qualifications;
    }

    public List<JobApplicantEducationalQualification> updateQualifications(List<JobApplicantEducationalQualificationProfile> lists) {
        List<JobApplicantEducationalQualification> qualifications = lists
                .stream()
                .map(updateQualificationProfile)
                .collect(Collectors.toList());
        return qualifications;
    }

    public List<JobApplicantContact> saveContacts(List<JobApplicantContactProfile> lists) {
        List<JobApplicantContact> contacts = lists
                .stream()
                .map(saveContactProfile)
                .collect(Collectors.toList());
        return contacts;
    }

    public List<JobApplicantContact> updateContacts(List<JobApplicantContactProfile> lists) {
        List<JobApplicantContact> contacts = lists
                .stream()
                .map(updateContactProfile)
                .collect(Collectors.toList());
        return contacts;
    }

    public List<JobApplicantJobExperience> deleteExperience(JobApplicantJobExperience experience) {
        experience.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        experience.setRecordStatus(DELETED);
        return Arrays.asList(experience);
    }

    public List<JobApplicantEducationalQualification> deleteQualification(JobApplicantEducationalQualification application) {
        application.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        application.setRecordStatus(DELETED);
        return Arrays.asList(application);
    }

    public List<JobApplicantContact> deleteContact(JobApplicantContact contact) {
        contact.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        contact.setRecordStatus(DELETED);
        return Arrays.asList(contact);
    }

    private JobApplicantJobExperience findByExperienceId(Long experienceId, List<JobApplicantJobExperience> lists) {
        for (JobApplicantJobExperience experience : lists) {
            if (experience.getId().equals(experienceId)) {
                return experience;
            }
        }
        return null;
    }

    private JobApplicantEducationalQualification findByQualificationId(Long qualificationId, List<JobApplicantEducationalQualification> lists) {
        for (JobApplicantEducationalQualification qualification : lists) {
            if (qualification.getId().equals(qualificationId)) {
                return qualification;
            }
        }
        return null;
    }

    private JobApplicantContact findByContactId(Long contactId, List<JobApplicantContact> lists) {
        for (JobApplicantContact contact : lists) {
            if (contact.getId().equals(contactId)) {
                return contact;
            }
        }
        return null;
    }

    public JobApplicantJobExperience findExperience(Long experienceId, JobApplicant applicant) {
        return Optional.ofNullable(findByExperienceId(experienceId, applicant.getExperiences()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(JOB_APPLICANT_JOB_EXPERIENCE + experienceId)
                );
    }

    public JobApplicantEducationalQualification findQualification(Long qualificationId, JobApplicant applicant) {
        return Optional.ofNullable(findByQualificationId(qualificationId, applicant.getQualifications()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(JOB_APPLICANT_QUALIFICATION + qualificationId)
                );
    }

    public JobApplicantContact findContact(Long contactId, JobApplicant applicant) {
        return Optional.ofNullable(findByContactId(contactId, applicant.getContacts()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(JOB_APPLICANT_CONTACT + contactId)
                );
    }
}
