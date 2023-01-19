package com.eteacher.service.dto;

import com.eteacher.service.entity.job.JobApplicant;
import com.eteacher.service.enums.Gender;
import com.eteacher.service.enums.MaritalStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class JobApplicantDto {

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

    private Long jobCircular;

    private Long religionId;

    private Long subjectId;

    public JobApplicant to() {
        JobApplicant applicant = new JobApplicant();
        applicant.setDateOfBirth(dateOfBirth);
        applicant.setNid(nid);
        applicant.setBirthRegNo(birthRegNo);
        applicant.setPassportNo(passportNo);
        applicant.setPassportIssueDate(passportIssueDate);
        applicant.setPassportExpiryDate(passportExpiryDate);
        applicant.setPassportIssuePlace(passportIssuePlace);
        applicant.setNationality(nationality);
        applicant.setMaritalStatus(maritalStatus);
        applicant.setFathersName(fathersName);
        applicant.setFathersNameBn(fathersNameBn);
        applicant.setMothersName(mothersName);
        applicant.setMothersNameBn(mothersNameBn);
        applicant.setSpouseName(spouseName);
        applicant.setSpouseNameBn(spouseNameBn);
        applicant.setApplicationTrackingNo(applicationTrackingNo);
        applicant.setPin(pin);
        applicant.setApplicantName(applicantName);
        applicant.setApplicantNameBn(applicantNameBn);
        applicant.setDistrictId(districtId);
        applicant.setReligionId(religionId);
        applicant.setSubjectId(subjectId);
        return applicant;
    }

    public void update(JobApplicant applicant) {
        applicant.setDateOfBirth(dateOfBirth);
        applicant.setNid(nid);
        applicant.setBirthRegNo(birthRegNo);
        applicant.setPassportNo(passportNo);
        applicant.setPassportIssueDate(passportIssueDate);
        applicant.setPassportExpiryDate(passportExpiryDate);
        applicant.setPassportIssuePlace(passportIssuePlace);
        applicant.setNationality(nationality);
        applicant.setMaritalStatus(maritalStatus);
        applicant.setFathersName(fathersName);
        applicant.setFathersNameBn(fathersNameBn);
        applicant.setMothersName(mothersName);
        applicant.setMothersNameBn(mothersNameBn);
        applicant.setSpouseName(spouseName);
        applicant.setSpouseNameBn(spouseNameBn);
        applicant.setApplicationTrackingNo(applicationTrackingNo);
        applicant.setPin(pin);
        applicant.setApplicantName(applicantName);
        applicant.setApplicantNameBn(applicantNameBn);
        applicant.setDistrictId(districtId);
        applicant.setReligionId(religionId);
        applicant.setSubjectId(subjectId);
    }
}
