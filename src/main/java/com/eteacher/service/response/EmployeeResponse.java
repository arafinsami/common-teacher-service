package com.eteacher.service.response;

import com.eteacher.service.client.CommonRestClient;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeAttendance;
import com.eteacher.service.entity.commonteacher.EmployeeEncloser;
import com.eteacher.service.entity.commonteacher.EmployeeJoining;
import com.eteacher.service.enums.EmploymentType;
import com.eteacher.service.enums.Gender;
import com.eteacher.service.enums.MaritalStatus;
import com.eteacher.service.profile.EmployeeAttendanceProfile;
import com.eteacher.service.profile.EmployeeJoiningProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.utils.StringUtils.isNotEmpty;
import static com.eteacher.service.utils.StringUtils.nonNull;

@Data
@NoArgsConstructor
public class EmployeeResponse {


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

    private Long departmentId;

    private String departmentName;

    private Long designation;

    private String designationName;

    private Long district;

    private String employeeType;

    private Long institute;

    private Long jobApplicant;

    private String paymentMethod;

    private Long religion;

    private String salaryScale;

    private String employeeEncloser;

    private Long subjectId;

    private String subjectName;

    public static EmployeeResponse response(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setEmployeeLegacyId(employee.getEmployeeLegacyId());
        response.setIndexNo(employee.getIndexNo());
        response.setEmployeeCode(employee.getEmployeeCode());
        response.setEmployeeName(employee.getEmployeeName());
        response.setEmployeeNameBn(employee.getEmployeeNameBn());
        response.setEmploymentType(employee.getEmploymentType());
        response.setGender(employee.getGender());
        response.setDateOfBirth(employee.getDateOfBirth());
        response.setNid(employee.getNid());
        response.setBirthRegNo(employee.getBirthRegNo());
        response.setPassportNo(employee.getPassportNo());
        response.setPassportIssueDate(employee.getPassportIssueDate());
        response.setPassportExpiryDate(employee.getPassportExpiryDate());
        response.setPassportIssuePlace(employee.getPassportIssuePlace());
        response.setDateOfFirstJoining(employee.getDateOfFirstJoining());
        response.setNationality(employee.getNationality());
        response.setMaritalStatus(employee.getMaritalStatus());
        response.setFathersName(employee.getFathersName());
        response.setFathersNameBn(employee.getFathersNameBn());
        response.setMothersName(employee.getMothersName());
        response.setMothersNameBn(employee.getMothersNameBn());
        response.setSpouseName(employee.getSpouseName());
        response.setSpouseNameBn(employee.getSpouseNameBn());
        response.setNoOfChildrenMale(employee.getNoOfChildrenMale());
        response.setNoOfChildrenFemale(employee.getNoOfChildrenFemale());
        response.setIsMpo(employee.getIsMpo());
        response.setMpoEnrollmentDate(employee.getMpoEnrollmentDate());
        response.setMpoCode(employee.getMpoCode());
        response.setIsNtrca(employee.getIsNtrca());
        response.setNtrcaEnrollmentDate(employee.getNtrcaEnrollmentDate());
        response.setIsGovt(employee.getIsGovt());
        response.setGovernmentalizationDate(employee.getGovernmentalizationDate());
        response.setActiveInstituteDateOfJoining(employee.getActiveInstituteDateOfJoining());
        response.setActiveDesignationDateOfStart(employee.getActiveDesignationDateOfStart());
        response.setInstitute(employee.getInstitute());
        response.setJobApplicant(employee.getJobApplicant());
        response.setSubjectId(employee.getSubjectId());
        response.setPaymentMethod(nonNull(
                employee.getPaymentMethod()) ?
                employee.getPaymentMethod().getPaymentMethodName()
                : "No Data"
        );
        response.setReligion(employee.getReligionId());
        response.setSalaryScale(nonNull(
                employee.getSalaryScale()) ?
                employee.getSalaryScale().getSalaryScaleName()
                : "No Data"
        );
        response.setDesignation(employee.getDesignationId());
        response.setDistrict(employee.getDistrictId());
        response.setEmployeeType(nonNull(
                employee.getEmployeeType()) ?
                employee.getEmployeeType().getEmployeeTypeName()
                : "No Data"
        );
        response.setDepartmentId(nonNull(
                employee.getDepartment()) ?
                employee.getDepartment().getId()
                : null
        );
        response.setDepartmentName(nonNull(
                employee.getDepartment()) ?
                employee.getDepartment().getDepartmentName()
                : "No Data"
        );
        response.setEmployeeEncloser(nonNull(
                employee.getEmployeeEncloser()) ?
                employee.getEmployeeEncloser().getEncloserName()
                : "No Data"
        );
        return response;
    }


    public static EmployeeResponse response(Employee employee, String subjectName, String designationName) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setEmployeeLegacyId(employee.getEmployeeLegacyId());
        response.setIndexNo(employee.getIndexNo());
        response.setEmployeeCode(employee.getEmployeeCode());
        response.setEmployeeName(employee.getEmployeeName());
        response.setEmployeeNameBn(employee.getEmployeeNameBn());
        response.setEmploymentType(employee.getEmploymentType());
        response.setGender(employee.getGender());
        response.setDateOfBirth(employee.getDateOfBirth());
        response.setNid(employee.getNid());
        response.setBirthRegNo(employee.getBirthRegNo());
        response.setPassportNo(employee.getPassportNo());
        response.setPassportIssueDate(employee.getPassportIssueDate());
        response.setPassportExpiryDate(employee.getPassportExpiryDate());
        response.setPassportIssuePlace(employee.getPassportIssuePlace());
        response.setDateOfFirstJoining(employee.getDateOfFirstJoining());
        response.setNationality(employee.getNationality());
        response.setMaritalStatus(employee.getMaritalStatus());
        response.setFathersName(employee.getFathersName());
        response.setFathersNameBn(employee.getFathersNameBn());
        response.setMothersName(employee.getMothersName());
        response.setMothersNameBn(employee.getMothersNameBn());
        response.setSpouseName(employee.getSpouseName());
        response.setSpouseNameBn(employee.getSpouseNameBn());
        response.setNoOfChildrenMale(employee.getNoOfChildrenMale());
        response.setNoOfChildrenFemale(employee.getNoOfChildrenFemale());
        response.setIsMpo(employee.getIsMpo());
        response.setMpoEnrollmentDate(employee.getMpoEnrollmentDate());
        response.setMpoCode(employee.getMpoCode());
        response.setIsNtrca(employee.getIsNtrca());
        response.setNtrcaEnrollmentDate(employee.getNtrcaEnrollmentDate());
        response.setIsGovt(employee.getIsGovt());
        response.setGovernmentalizationDate(employee.getGovernmentalizationDate());
        response.setActiveInstituteDateOfJoining(employee.getActiveInstituteDateOfJoining());
        response.setActiveDesignationDateOfStart(employee.getActiveDesignationDateOfStart());
        response.setInstitute(employee.getInstitute());
        response.setJobApplicant(employee.getJobApplicant());
        response.setSubjectId(employee.getSubjectId());
        response.setSubjectName(subjectName);
        response.setPaymentMethod(nonNull(
                employee.getPaymentMethod()) ?
                employee.getPaymentMethod().getPaymentMethodName()
                : "No Data"
        );
        response.setReligion(employee.getReligionId());
        response.setSalaryScale(nonNull(
                employee.getSalaryScale()) ?
                employee.getSalaryScale().getSalaryScaleName()
                : "No Data"
        );
        response.setDesignation(employee.getDesignationId());
        response.setDesignationName(designationName);
        response.setDistrict(employee.getDistrictId());
        response.setEmployeeType(nonNull(
                employee.getEmployeeType()) ?
                employee.getEmployeeType().getEmployeeTypeName()
                : "No Data"
        );
        response.setDepartmentId(nonNull(
                employee.getDepartment()) ?
                employee.getDepartment().getId()
                : null
        );
        response.setDepartmentName(nonNull(
                employee.getDepartment()) ?
                employee.getDepartment().getDepartmentName()
                : "No Data"
        );
        response.setEmployeeEncloser(nonNull(
                employee.getEmployeeEncloser()) ?
                employee.getEmployeeEncloser().getEncloserName()
                : "No Data"
        );
        return response;
    }

    public static List<EmployeeAttendanceProfile> getAttendanceDetail(
            List<EmployeeAttendance> profiles) {
        List<EmployeeAttendanceProfile> attendances = new ArrayList<>();
        if (isNotEmpty(profiles)) {
            attendances = profiles.stream().map(profile).collect(Collectors.toList());
        }
        return attendances;
    }

    public static List<EmployeeJoiningProfile> getJoiningDetail(
            List<EmployeeJoining> profiles) {
        List<EmployeeJoiningProfile> joining = new ArrayList<>();
        if (isNotEmpty(profiles)) {
            joining = profiles.stream().map(join).collect(Collectors.toList());
        }
        return joining;
    }

    public static Map<String, Object> searchEmployee(
            String employeeLegacyId,
            String indexNo,
            String employeeCode,
            String employeeName,
            String nid) {
        Map<String, Object> map = new HashMap<>();
        map.put("employeeLegacyId", employeeLegacyId);
        map.put("indexNo", indexNo);
        map.put("employeeCode", employeeCode);
        map.put("employeeName", employeeName);
        map.put("nid", nid);
        return map;
    }

    public static Function<EmployeeAttendance, EmployeeAttendanceProfile> profile = p -> {
        EmployeeAttendanceProfile attendance = new EmployeeAttendanceProfile();
        attendance.setId(p.getId());
        attendance.setDateOfAttendance(p.getDateOfAttendance());
        attendance.setInAt(p.getInAt());
        attendance.setOutAt(p.getOutAt());
        attendance.setStatus(p.getStatus());
        attendance.setRemarks(p.getRemarks());
        attendance.setApproverUserId(p.getApproverUserId());
        attendance.setApproveDate(p.getApproveDate());
        attendance.setApproverNote(p.getApproverNote());
        attendance.setReasonForRejection(p.getReasonForRejection());
        return attendance;
    };

    public static Function<EmployeeJoining, EmployeeJoiningProfile> join = p -> {
        EmployeeJoiningProfile joining = new EmployeeJoiningProfile();
        joining.setId(p.getId());
        joining.setEffectiveDate(p.getEffectiveDate());
        joining.setPostingStatus(p.getPostingStatus());
        joining.setApproverUserId(p.getApproverUserId());
        joining.setApproverNote(p.getApproverNote());
        joining.setApproveDate(p.getApproveDate());
        joining.setFromDesignation(p.getFromDesignation());
        joining.setToDesignation(p.getToDesignation());
        joining.setFromInstitute(p.getFromInstitute());
        joining.setToInstitute(p.getToInstitute());
        joining.setReasonForRejection(p.getReasonForRejection());
        return joining;
    };
}

