package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.enums.EmploymentType;
import com.eteacher.service.enums.Gender;
import com.eteacher.service.enums.MaritalStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeDto {

    private Long id;

    @NotBlank
    private String employeeLegacyId;

    @NotBlank
    private String indexNo;

    @NotBlank
    private String employeeCode;

    @NotBlank
    private String employeeName;

    private String employeeNameBn;

    @NotNull
    private EmploymentType employmentType;

    //  @NotBlank/
    private Gender gender;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotBlank
    private String nid;

    @NotBlank
    private String birthRegNo;

//    @NotBlank
    private String passportNo;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date passportIssueDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date passportExpiryDate;

    //  @NotBlank
    private String passportIssuePlace;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfFirstJoining;

    @NotBlank
    private String nationality;

    private MaritalStatus maritalStatus;

    @NotBlank
    private String fathersName;

    private String fathersNameBn;

    @NotBlank
    private String mothersName;

    private String mothersNameBn;

    @NotBlank
    private String spouseName;

    private String spouseNameBn;

    private Integer noOfChildrenMale;

    private Integer noOfChildrenFemale;

    private Boolean isMpo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date mpoEnrollmentDate;

    private String mpoCode;

    private Boolean isNtrca;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ntrcaEnrollmentDate;

    private Boolean isGovt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date governmentalizationDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date activeInstituteDateOfJoining;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date activeDesignationDateOfStart;

    @NotNull
    private Long department;

    @NotNull
    private Long designation;

    @NotNull
    private Long district;

    private Long employeeEncloser;

    @NotNull
    private Long employeeType;

    @NotNull
    private Long institute;

    @NotNull
    private Long jobApplicant;

    @NotNull
    private Long paymentMethod;

    @NotNull
    private Long religion;

    @NotNull
    private Long salaryScale;

    @NotNull
    private Long subjectId;

    public Employee to() {
        Employee employee = new Employee();
        employee.setEmployeeLegacyId(employeeLegacyId);
        employee.setIndexNo(indexNo);
        employee.setEmployeeCode(employeeCode);
        employee.setEmployeeName(employeeName);
        employee.setEmployeeNameBn(employeeNameBn);
        employee.setEmploymentType(employmentType);
        employee.setGender(gender);
        employee.setDateOfBirth(dateOfBirth);
        employee.setNid(nid);
        employee.setBirthRegNo(birthRegNo);
        employee.setPassportNo(passportNo);
        employee.setPassportIssueDate(passportIssueDate);
        employee.setPassportExpiryDate(passportExpiryDate);
        employee.setPassportIssuePlace(passportIssuePlace);
        employee.setDateOfFirstJoining(dateOfFirstJoining);
        employee.setNationality(nationality);
        employee.setMaritalStatus(maritalStatus);
        employee.setFathersName(fathersName);
        employee.setFathersNameBn(fathersNameBn);
        employee.setMothersName(mothersName);
        employee.setMothersNameBn(mothersNameBn);
        employee.setSpouseName(spouseName);
        employee.setSpouseNameBn(spouseNameBn);
        employee.setNoOfChildrenMale(noOfChildrenMale);
        employee.setNoOfChildrenFemale(noOfChildrenFemale);
        employee.setIsMpo(isMpo);
        employee.setMpoEnrollmentDate(mpoEnrollmentDate);
        employee.setMpoCode(mpoCode);
        employee.setIsNtrca(isNtrca);
        employee.setNtrcaEnrollmentDate(ntrcaEnrollmentDate);
        employee.setIsGovt(isGovt);
        employee.setGovernmentalizationDate(governmentalizationDate);
        employee.setActiveInstituteDateOfJoining(activeInstituteDateOfJoining);
        employee.setActiveDesignationDateOfStart(activeDesignationDateOfStart);
        employee.setDesignationId(designation);
        employee.setDistrictId(district);
//    employee.setEmployeeEncloser(null);
        employee.setInstitute(institute);
        employee.setJobApplicant(jobApplicant);
        employee.setReligionId(religion);
        employee.setSubjectId(subjectId);
        return employee;
    }

    public void update(Employee employee) {
        employee.setEmployeeLegacyId(employeeLegacyId);
        employee.setIndexNo(indexNo);
        employee.setEmployeeCode(employeeCode);
        employee.setEmployeeName(employeeName);
        employee.setEmployeeNameBn(employeeNameBn);
        employee.setEmploymentType(employmentType);
        employee.setGender(gender);
        employee.setDateOfBirth(dateOfBirth);
        employee.setNid(nid);
        employee.setBirthRegNo(birthRegNo);
        employee.setPassportNo(passportNo);
        employee.setPassportIssueDate(passportIssueDate);
        employee.setPassportExpiryDate(passportExpiryDate);
        employee.setPassportIssuePlace(passportIssuePlace);
        employee.setDateOfFirstJoining(dateOfFirstJoining);
        employee.setNationality(nationality);
        employee.setMaritalStatus(maritalStatus);
        employee.setFathersName(fathersName);
        employee.setFathersNameBn(fathersNameBn);
        employee.setMothersName(mothersName);
        employee.setMothersNameBn(mothersNameBn);
        employee.setSpouseName(spouseName);
        employee.setSpouseNameBn(spouseNameBn);
        employee.setNoOfChildrenMale(noOfChildrenMale);
        employee.setNoOfChildrenFemale(noOfChildrenFemale);
        employee.setIsMpo(isMpo);
        employee.setMpoEnrollmentDate(mpoEnrollmentDate);
        employee.setMpoCode(mpoCode);
        employee.setIsNtrca(isNtrca);
        employee.setNtrcaEnrollmentDate(ntrcaEnrollmentDate);
        employee.setIsGovt(isGovt);
        employee.setGovernmentalizationDate(governmentalizationDate);
        employee.setActiveInstituteDateOfJoining(activeInstituteDateOfJoining);
        employee.setActiveDesignationDateOfStart(activeDesignationDateOfStart);
        employee.setInstitute(institute);
        employee.setJobApplicant(jobApplicant);
        employee.setReligionId(religion);
        employee.setDesignationId(designation);
        employee.setDistrictId(district);
        employee.setSubjectId(subjectId);
    }
}
