package com.eteacher.service.service;

import com.eteacher.service.audit.JobApplicantAudit;
import com.eteacher.service.audit.JobApplicantContactAudit;
import com.eteacher.service.audit.JobApplicantEducationalQualificationAudit;
import com.eteacher.service.audit.JobApplicantExperienceAudit;
import com.eteacher.service.entity.job.JobApplicant;
import com.eteacher.service.helper.GetListHelper;
import com.eteacher.service.helper.JobApplicantHelper;
import com.eteacher.service.repository.JobApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DELETED;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class JobApplicantService {

    private final JobApplicantRepository repository;

    private final ActionLogService actionLogService;

    private final JobApplicantHelper helper;

    private final EntityManager em;

    @Transactional
    public JobApplicant save(JobApplicant applicant) {
        helper.getSaveData(applicant);
        JobApplicant j = repository.save(applicant);
        JobApplicantAudit audit = JobApplicantAudit.from(j);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                JOB_APPLICANT_SAVE,
                objectToJson(audit)
        );
        return j;
    }

    @Transactional
    public JobApplicant saveExperiences(JobApplicant applicant) {
        JobApplicant j = repository.save(applicant);
        List<JobApplicantExperienceAudit> audit = j.getExperiences().stream()
                .map(JobApplicantExperienceAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                j.getId(),
                JOB_APPLICANT_EXPERIENCE_SAVE,
                objectToJson(audit)
        );
        return j;
    }

    @Transactional
    public JobApplicant saveQualifications(JobApplicant applicant) {
        JobApplicant j = repository.save(applicant);
        List<JobApplicantEducationalQualificationAudit> audit = j.getQualifications().stream()
                .map(JobApplicantEducationalQualificationAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                j.getId(),
                JOB_APPLICANT_EDUCATIONAL_QUALIFICATION_SAVE,
                objectToJson(audit)
        );
        return j;
    }

    @Transactional
    public JobApplicant saveContacts(JobApplicant applicant) {
        JobApplicant j = repository.save(applicant);
        List<JobApplicantContactAudit> audit = j.getContacts().stream()
                .map(JobApplicantContactAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                j.getId(),
                JOB_APPLICANT_CONTACT_SAVE,
                objectToJson(audit)
        );
        return j;
    }

    @Transactional
    public JobApplicant update(JobApplicant applicant) {
        helper.getUpdateData(applicant, ACTIVE);
        JobApplicant j = repository.save(applicant);
        JobApplicantAudit audit = JobApplicantAudit.from(j);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                JOB_APPLICANT_UPDATE,
                objectToJson(audit)
        );
        return j;
    }

    @Transactional
    public JobApplicant updateExperiences(JobApplicant applicant) {
        JobApplicant j = repository.save(applicant);
        List<JobApplicantExperienceAudit> audit = j.getExperiences().stream()
                .map(JobApplicantExperienceAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                j.getId(),
                JOB_APPLICANT_EXPERIENCE_UPDATE,
                objectToJson(audit)
        );
        return j;
    }

    @Transactional
    public JobApplicant updateQualifications(JobApplicant applicant) {
        JobApplicant j = repository.save(applicant);
        List<JobApplicantEducationalQualificationAudit> audit = j.getQualifications().stream()
                .map(JobApplicantEducationalQualificationAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                j.getId(),
                JOB_APPLICANT_EDUCATIONAL_QUALIFICATION_UPDATE,
                objectToJson(audit)
        );
        return j;
    }

    @Transactional
    public JobApplicant updateContacts(JobApplicant applicant) {
        JobApplicant j = repository.save(applicant);
        List<JobApplicantContactAudit> audit = j.getContacts().stream()
                .map(JobApplicantContactAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                j.getId(),
                JOB_APPLICANT_CONTACT_UPDATE,
                objectToJson(audit)
        );
        return j;
    }

    public Optional<JobApplicant> findById(Long id) {
        Optional<JobApplicant> applicant = repository.findById(id);
        return applicant;
    }

    public List<JobApplicant> findAll(String[] sortable, String sortBy) {
        return new GetListHelper<JobApplicant>(em, JobApplicant.class).findAll(sortable, sortBy);
    }

    public Optional<JobApplicant> findByPassportNo(String passportNo) {
        Optional<JobApplicant> applicant = repository.findByPassportNo(passportNo);
        return applicant;
    }

    @Transactional
    public void delete(JobApplicant applicant) {
        helper.getUpdateData(applicant, DELETED);
        JobApplicant j = repository.save(applicant);
        JobApplicantAudit audit = JobApplicantAudit.from(j);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                JOB_APPLICANT_DELETE,
                objectToJson(audit)
        );
    }

    @Transactional
    public void deleteExperience(JobApplicant applicant) {
        JobApplicant j = repository.save(applicant);
        List<JobApplicantExperienceAudit> audits = j.getExperiences()
                .stream()
                .map(JobApplicantExperienceAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                j.getId(),
                JOB_APPLICANT_EXPERIENCE_DELETE,
                objectToJson(audits)
        );
    }

    @Transactional
    public void deleteQualification(JobApplicant applicant) {
        JobApplicant j = repository.save(applicant);
        List<JobApplicantEducationalQualificationAudit> audits = j.getQualifications()
                .stream()
                .map(JobApplicantEducationalQualificationAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                j.getId(),
                JOB_APPLICANT_EDUCATIONAL_QUALIFICATION_DELETE,
                objectToJson(audits)
        );
    }

    @Transactional
    public void deleteContact(JobApplicant applicant) {
        JobApplicant j = repository.save(applicant);
        List<JobApplicantContactAudit> audits = j.getContacts()
                .stream()
                .map(JobApplicantContactAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                j.getId(),
                JOB_APPLICANT_CONTACT_DELETE,
                objectToJson(audits)
        );
    }
}
