package com.eteacher.service.audit;

import com.eteacher.service.entity.job.JobApplicant;
import com.eteacher.service.enums.Gender;
import com.eteacher.service.enums.MaritalStatus;
import lombok.Data;

import java.util.Date;

@Data
public class JobApplicantAudit {

    private Long id;

    private Date dateOfBirth;

    private String nid;

    private String birthRegNo;

    private String passportNo;

    private Date passportIssueDate;

    private Date passportExpiryDate;

    private String passportIssuePlace;

    private String nationality;

    private MaritalStatus maritalStatus;

    private String fathersName;

    private String fathersNameBn;

    private String mothersName;

    private String mothersNameBn;

    private String spouseName;

    private String spouseNameBn;

    private String applicationTrackingNo;

    private String pin;

    private String applicantName;

    private String applicantNameBn;

    private Gender gender;

    private Long districtId;

    private Long jobCircularId;

    private Long religionId;

    private Long subjectId;

    public static JobApplicantAudit from(JobApplicant applicant) {
        JobApplicantAudit audit = new JobApplicantAudit();
        audit.setId(applicant.getId());
        audit.setDateOfBirth(applicant.getDateOfBirth());
        audit.setNid(applicant.getNid());
        audit.setBirthRegNo(applicant.getBirthRegNo());
        audit.setPassportNo(applicant.getPassportNo());
        audit.setPassportIssueDate(applicant.getPassportIssueDate());
        audit.setPassportExpiryDate(applicant.getPassportExpiryDate());
        audit.setPassportIssuePlace(applicant.getPassportIssuePlace());
        audit.setNationality(applicant.getNationality());
        audit.setMaritalStatus(applicant.getMaritalStatus());
        audit.setFathersName(applicant.getFathersName());
        audit.setFathersNameBn(applicant.getFathersNameBn());
        audit.setMothersName(applicant.getMothersName());
        audit.setMothersNameBn(applicant.getMothersNameBn());
        audit.setSpouseName(applicant.getSpouseName());
        audit.setSpouseNameBn(applicant.getSpouseNameBn());
        audit.setApplicationTrackingNo(applicant.getApplicationTrackingNo());
        audit.setPin(applicant.getPin());
        audit.setApplicantName(applicant.getApplicantName());
        audit.setApplicantNameBn(applicant.getApplicantNameBn());
        audit.setDistrictId(applicant.getDistrictId());
        audit.setReligionId(applicant.getReligionId());
        audit.setSubjectId(applicant.getSubjectId());
        return audit;
    }
}
