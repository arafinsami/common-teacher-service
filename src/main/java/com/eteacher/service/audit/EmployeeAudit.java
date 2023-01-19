package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.enums.EmploymentType;
import com.eteacher.service.enums.Gender;
import com.eteacher.service.enums.MaritalStatus;
import lombok.Data;

import java.util.Date;

import static com.eteacher.service.utils.StringUtils.nonNull;

@Data
public class EmployeeAudit {

  private Long id;

  private String employeeLegacyId;

  private String indexNo;

  private String employeeCode;

  private String employeeName;

  private String employeeNameBn;

  private EmploymentType employmentType;

  private Gender gender;

  private Date dateOfBirth;

  private String nid;

  private String birthRegNo;

  private String passportNo;

  private Date passportIssueDate;

  private Date passportExpiryDate;

  private String passportIssuePlace;

  private Date dateOfFirstJoining;

  private String nationality;

  private MaritalStatus maritalStatus;

  private String fathersName;

  private String fathersNameBn;

  private String mothersName;

  private String mothersNameBn;

  private String spouseName;

  private String spouseNameBn;

  private Integer noOfChildrenMale;

  private Integer noOfChildrenFemale;

  private Boolean isMpo;

  private Date mpoEnrollmentDate;

  private String mpoCode;

  private Boolean isNtrca;

  private Date ntrcaEnrollmentDate;

  private Boolean isGovt;

  private Date governmentalizationDate;

  private Date activeInstituteDateOfJoining;

  private Date activeDesignationDateOfStart;

  private Long department;

  private Long designation;

  private Long district;

  private Long employeeType;

  private Long institute;

  private Long jobApplicant;

  private Long paymentMethod;

  private Long religion;

  private Long salaryScale;

  public static EmployeeAudit from(Employee employee) {
    EmployeeAudit audit = new EmployeeAudit();
    audit.setId(employee.getId());
    audit.setEmployeeLegacyId(employee.getEmployeeLegacyId());
    audit.setIndexNo(employee.getIndexNo());
    audit.setEmployeeCode(employee.getEmployeeCode());
    audit.setEmployeeName(employee.getEmployeeName());
    audit.setEmployeeNameBn(employee.getEmployeeNameBn());
    audit.setEmploymentType(employee.getEmploymentType());
    audit.setGender(employee.getGender());
    audit.setDateOfBirth(employee.getDateOfBirth());
    audit.setNid(employee.getNid());
    audit.setBirthRegNo(employee.getBirthRegNo());
    audit.setPassportNo(employee.getPassportNo());
    audit.setPassportIssueDate(employee.getPassportIssueDate());
    audit.setPassportExpiryDate(employee.getPassportExpiryDate());
    audit.setPassportIssuePlace(employee.getPassportIssuePlace());
    audit.setDateOfFirstJoining(employee.getDateOfFirstJoining());
    audit.setNationality(employee.getNationality());
    audit.setMaritalStatus(employee.getMaritalStatus());
    audit.setFathersName(employee.getFathersName());
    audit.setFathersNameBn(employee.getFathersNameBn());
    audit.setMothersName(employee.getMothersName());
    audit.setMothersNameBn(employee.getMothersNameBn());
    audit.setSpouseName(employee.getSpouseName());
    audit.setSpouseNameBn(employee.getSpouseNameBn());
    audit.setNoOfChildrenMale(employee.getNoOfChildrenMale());
    audit.setNoOfChildrenFemale(employee.getNoOfChildrenFemale());
    audit.setIsMpo(employee.getIsMpo());
    audit.setMpoEnrollmentDate(employee.getMpoEnrollmentDate());
    audit.setMpoCode(employee.getMpoCode());
    audit.setIsNtrca(employee.getIsNtrca());
    audit.setNtrcaEnrollmentDate(employee.getNtrcaEnrollmentDate());
    audit.setIsGovt(employee.getIsGovt());
    audit.setGovernmentalizationDate(employee.getGovernmentalizationDate());
    audit.setActiveInstituteDateOfJoining(employee.getActiveInstituteDateOfJoining());
    audit.setActiveDesignationDateOfStart(employee.getActiveDesignationDateOfStart());
    audit.setInstitute(employee.getInstitute());
    audit.setJobApplicant(employee.getJobApplicant());
    audit.setPaymentMethod(nonNull(
            employee.getPaymentMethod()) ?
            employee.getPaymentMethod().getId()
            : 0l
    );
    audit.setReligion(employee.getReligionId());
    audit.setSalaryScale(nonNull(
            employee.getSalaryScale()) ?
            employee.getSalaryScale().getId()
            : 0l
    );
    return audit;
  }
}
