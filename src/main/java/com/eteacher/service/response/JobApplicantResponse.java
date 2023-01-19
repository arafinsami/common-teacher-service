package com.eteacher.service.response;

import com.eteacher.service.entity.job.JobApplicant;
import com.eteacher.service.entity.job.JobCircular;
import com.eteacher.service.enums.Gender;
import com.eteacher.service.enums.MaritalStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class JobApplicantResponse {

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

    private Long religionId;

    private String jobCircular;

    private Long subjectId;

    public static JobApplicantResponse response(JobApplicant applicant) {
        JobApplicantResponse response = new JobApplicantResponse();
        response.setId(applicant.getId());
        response.setDateOfBirth(applicant.getDateOfBirth());
        response.setNid(applicant.getNid());
        response.setBirthRegNo(applicant.getBirthRegNo());
        response.setPassportNo(applicant.getPassportNo());
        response.setPassportIssueDate(applicant.getPassportIssueDate());
        response.setPassportExpiryDate(applicant.getPassportExpiryDate());
        response.setPassportIssuePlace(applicant.getPassportIssuePlace());
        response.setNationality(applicant.getNationality());
        response.setMaritalStatus(applicant.getMaritalStatus());
        response.setFathersName(applicant.getFathersName());
        response.setFathersNameBn(applicant.getFathersNameBn());
        response.setMothersName(applicant.getMothersName());
        response.setMothersNameBn(applicant.getMothersNameBn());
        response.setSpouseName(applicant.getSpouseName());
        response.setSpouseNameBn(applicant.getSpouseNameBn());
        response.setApplicationTrackingNo(applicant.getApplicationTrackingNo());
        response.setPin(applicant.getPin());
        response.setApplicantName(applicant.getApplicantName());
        response.setApplicantNameBn(applicant.getApplicantNameBn());
        response.setDistrictId(applicant.getDistrictId());
        response.setReligionId(applicant.getReligionId());
        response.setJobCircular(applicant.getJobCircular().getJobCircularDescription());
        response.setSubjectId(applicant.getSubjectId());
        return response;
    }
}
