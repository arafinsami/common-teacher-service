package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.govtteacher.EmployeeAcrEvaluation;
import com.eteacher.service.entity.govtteacher.EmployeePensionRequest;
import com.eteacher.service.entity.govtteacher.EmployeePensionTransaction;
import com.eteacher.service.entity.mpo.*;
import com.eteacher.service.entity.ntrca.Department;
import com.eteacher.service.entity.ntrca.EmployeeDepartmentalExamMerit;
import com.eteacher.service.entity.ntrca.EmployeeDepartmentalExamMeritScore;
import com.eteacher.service.entity.ntrca.EmployeePublication;
import com.eteacher.service.enums.EmploymentType;
import com.eteacher.service.enums.Gender;
import com.eteacher.service.enums.MaritalStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "EMPLOYEE")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Employee extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @Column(name = "EMPLOYEE_LEGACY_ID")
    private String employeeLegacyId;

    @Column(name = "INDEX_NO")
    private String indexNo;

    @Column(name = "EMPLOYEE_CODE")
    private String employeeCode;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "EMPLOYEE_NAME_BN")
    private String employeeNameBn;

    @Enumerated(EnumType.STRING)
    @Column(name = "EMPLOYMENT_TYPE", length = 10)
    private EmploymentType employmentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 10)
    private Gender gender;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "NID")
    private String nid;

    @Column(name = "BIRTH_REG_NO")
    private String birthRegNo;

    @Column(name = "PASSPORT_NO")
    private String passportNo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PASSPORT_ISSUE_DATE")
    private Date passportIssueDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PASSPORT_EXPIRY_DATE")
    private Date passportExpiryDate;

    @Column(name = "PASSPORT_ISSUE_PLACE")
    private String passportIssuePlace;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_OF_FIRST_JOINING")
    private Date dateOfFirstJoining;

    @Column(name = "NATIONALITY")
    private String nationality;

    @Enumerated(EnumType.STRING)
    @Column(name = "MARITAL_STATUS", length = 12)
    private MaritalStatus maritalStatus;

    @Column(name = "FATHERS_NAME")
    private String fathersName;

    @Column(name = "FATHERS_NAME_BN")
    private String fathersNameBn;

    @Column(name = "MOTHERS_NAME")
    private String mothersName;

    @Column(name = "MOTHERS_NAME_BN")
    private String mothersNameBn;

    @Column(name = "SPOUSE_NAME")
    private String spouseName;

    @Column(name = "SPOUSE_NAME_BN")
    private String spouseNameBn;

    @Column(name = "NO_OF_CHILDREN_MALE")
    private Integer noOfChildrenMale;

    @Column(name = "NO_OF_CHILDREN_FEMALE")
    private Integer noOfChildrenFemale;

    @Column(name = "IS_MPO")
    private Boolean isMpo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MPO_ENROLLMENT_DATE")
    private Date mpoEnrollmentDate;

    @Column(name = "MPO_CODE")
    private String mpoCode;

    @Column(name = "IS_NTRCA")
    private Boolean isNtrca;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "NTRCA_ENROLLMENT_DATE")
    private Date ntrcaEnrollmentDate;

    @Column(name = "IS_GOVT")
    private Boolean isGovt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "GOVERNMENTALIZATION_DATE")
    private Date governmentalizationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTIVE_INSTITUTE_DATE_OF_JOINING")
    private Date activeInstituteDateOfJoining;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ACTIVE_DESIGNATION_DATE_OF_START")
    private Date activeDesignationDateOfStart;

    //    new included relation
    @OneToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @Column(name = "DESIGNATION_ID")
    private Long designationId;

    @Column(name = "HOME_DISTRICT_ID")
    private Long districtId;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_IMAGE_ENCLOSER_ID")
    private EmployeeEncloser employeeEncloser;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_TYPE_ID")
    private EmployeeType employeeType;

    @Column(name = "ACTIVE_INSTITUTE_ID")
    private Long institute;

    @Column(name = "JOB_APPLICANT_ID")
    private Long jobApplicant;

    @OneToOne
    @JoinColumn(name = "ACTIVE_PAYMENT_METHOD_ID")
    private PaymentMethod paymentMethod;

    @Column(name = "RELIGION_ID")
    private Long religionId;

    @Column(name = "SUBJECT_ID")
    private Long subjectId;

    @OneToOne
    @JoinColumn(name = "ACTIVE_SALARY_SCALE_ID")
    private SalaryScale salaryScale;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeAttendance> attendances;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeMpoApplication> mpoApplications;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeMpoPaymentRequest> mpoPaymentRequests;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeMpoPaymentRefund> mpoPaymentRefunds;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeMpoPaymentDeduction> mpoPaymentDeductions;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeMpoSalaryExtraBenefit> mpoSalaryExtraBenefits;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeMpoAffiliation> mpoAffiliations;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeRetirementPayment> retirementPayments;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeReleaseRecord> releaseRecords;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeSalaryIncrement> salaryIncrements;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeSalaryTransaction> salaryTransactions;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeRetirementRequest> retirementRequests;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeJobPostingRequest> jobPostingRequests;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeePaymentArrear> paymentArrears;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeTransferRecord> transferRecords;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeAttritionRecord> attritionRecords;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeePerformanceEvaluation> performanceEvaluations;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeRoutineAssignment> routineAssignments;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeEducationalQualification> educationalQualifications;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeBankAccount> bankAccounts;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeLanguageSkill> languageSkills;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeContactDetail> contacts;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeJobExperienceDetail> jobExperiences;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeTrainingDetail> trainings;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeLeaveRecord> leaveRecords;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeAcrEvaluation> acrEvaluations;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeePensionRequest> pensionRequests;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeePensionTransaction> pensionTransactions;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeDepartmentalExamMerit> departmentalExamMerits;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeDepartmentalExamMeritScore> departmentalExamMeritScores;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeePublication> publications;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeLeaveRecordEncloser> leaveRecordEnclosers;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeePromotionRecord> promotionRecords;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employee"
    )
    private List<EmployeeJoining> employeeJoinings;

    public void addAttendances(List<EmployeeAttendance> lists) {
        if (attendances == null) {
            attendances = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        attendances.addAll(lists);
    }

    public void addMpoApplications(List<EmployeeMpoApplication> lists) {
        if (mpoApplications == null) {
            mpoApplications = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        mpoApplications.addAll(lists);
    }

    public void addMpoApplication(EmployeeMpoApplication application) {
        if (mpoApplications == null) {
            mpoApplications = new ArrayList<>();
        }
        application.setEmployee(this);
        mpoApplications.add(application);
    }

    public void addMpoPaymentRequests(List<EmployeeMpoPaymentRequest> lists) {
        if (mpoPaymentRequests == null) {
            mpoPaymentRequests = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        mpoPaymentRequests.addAll(lists);
    }

    public void addMpoPaymentRequest(EmployeeMpoPaymentRequest paymentRequest) {
        if (mpoPaymentRequests == null) {
            mpoPaymentRequests = new ArrayList<>();
        }
        paymentRequest.setEmployee(this);
        mpoPaymentRequests.add(paymentRequest);
    }

    public void addMpoPaymentRefunds(List<EmployeeMpoPaymentRefund> lists) {
        if (mpoPaymentRefunds == null) {
            mpoPaymentRefunds = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        mpoPaymentRefunds.addAll(lists);
    }

    public void addMpoPaymentDeductions(List<EmployeeMpoPaymentDeduction> lists) {
        if (mpoPaymentDeductions == null) {
            mpoPaymentDeductions = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        mpoPaymentDeductions.addAll(lists);
    }

    public void addMpoSalaryExtraBenefits(List<EmployeeMpoSalaryExtraBenefit> lists) {
        if (mpoSalaryExtraBenefits == null) {
            mpoSalaryExtraBenefits = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        mpoSalaryExtraBenefits.addAll(lists);
    }

    public void addMpoSalaryExtraBenefit(EmployeeMpoSalaryExtraBenefit benefit) {
        if (mpoSalaryExtraBenefits == null) {
            mpoSalaryExtraBenefits = new ArrayList<>();
        }
        benefit.setEmployee(this);
        mpoSalaryExtraBenefits.add(benefit);
    }

    public void addMpoAffiliations(List<EmployeeMpoAffiliation> lists) {
        if (mpoAffiliations == null) {
            mpoAffiliations = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        mpoAffiliations.addAll(lists);
    }

    public void addMpoAffiliation(EmployeeMpoAffiliation affiliation) {
        if (mpoAffiliations == null) {
            mpoAffiliations = new ArrayList<>();
        }
        affiliation.setEmployee(this);
        mpoAffiliations.add(affiliation);
    }

    public void addRetirementPayments(List<EmployeeRetirementPayment> lists) {
        if (retirementPayments == null) {
            retirementPayments = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        retirementPayments.addAll(lists);
    }

    public void addRetirementPayment(EmployeeRetirementPayment payment) {
        if (retirementPayments == null) {
            retirementPayments = new ArrayList<>();
        }
        payment.setEmployee(this);
        retirementPayments.add(payment);
    }

    public void addReleaseRecords(List<EmployeeReleaseRecord> lists) {
        if (releaseRecords == null) {
            releaseRecords = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        releaseRecords.addAll(lists);
    }

    public void addReleaseRecord(EmployeeReleaseRecord record) {
        if (releaseRecords == null) {
            releaseRecords = new ArrayList<>();
        }
        record.setEmployee(this);
        releaseRecords.add(record);
    }

    public void addSalaryIncrements(List<EmployeeSalaryIncrement> lists) {
        if (salaryIncrements == null) {
            salaryIncrements = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        salaryIncrements.addAll(lists);
    }

    public void addSalaryIncrement(EmployeeSalaryIncrement salary) {
        if (salaryIncrements == null) {
            salaryIncrements = new ArrayList<>();
        }
        salary.setEmployee(this);
        salaryIncrements.add(salary);
    }

    public void addSalaryTransactions(List<EmployeeSalaryTransaction> lists) {
        if (salaryTransactions == null) {
            salaryTransactions = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        salaryTransactions.addAll(lists);
    }

    public void addSalaryTransaction(EmployeeSalaryTransaction transaction) {
        if (salaryTransactions == null) {
            salaryTransactions = new ArrayList<>();
        }
        transaction.setEmployee(this);
        salaryTransactions.add(transaction);
    }

    public void addRetirementRequests(List<EmployeeRetirementRequest> lists) {
        if (retirementRequests == null) {
            retirementRequests = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        retirementRequests.addAll(lists);
    }

    public void addRetirementRequest(EmployeeRetirementRequest request) {
        if (retirementRequests == null) {
            retirementRequests = new ArrayList<>();
        }
        request.setEmployee(this);
        retirementRequests.add(request);
    }

    public void addJobPostingRequests(List<EmployeeJobPostingRequest> lists) {
        if (jobPostingRequests == null) {
            jobPostingRequests = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        jobPostingRequests.addAll(lists);
    }

    public void addJobPostingRequest(EmployeeJobPostingRequest postingRequest) {
        if (jobPostingRequests == null) {
            jobPostingRequests = new ArrayList<>();
        }
        postingRequest.setEmployee(this);
        jobPostingRequests.add(postingRequest);
    }

    public void addPaymentArrears(List<EmployeePaymentArrear> lists) {
        if (paymentArrears == null) {
            paymentArrears = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        paymentArrears.addAll(lists);
    }

    public void addPaymentArrear(EmployeePaymentArrear arrear) {
        if (paymentArrears == null) {
            paymentArrears = new ArrayList<>();
        }
        arrear.setEmployee(this);
        paymentArrears.add(arrear);
    }

    public void addTransferRecords(List<EmployeeTransferRecord> lists) {
        if (transferRecords == null) {
            transferRecords = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        transferRecords.addAll(lists);
    }

    public void addTransferRecord(EmployeeTransferRecord record) {
        if (transferRecords == null) {
            transferRecords = new ArrayList<>();
        }
        record.setEmployee(this);
        transferRecords.add(record);
    }

    public void addAttritionRecords(List<EmployeeAttritionRecord> lists) {
        if (attritionRecords == null) {
            attritionRecords = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        attritionRecords.addAll(lists);
    }

    public void addAttritionRecord(EmployeeAttritionRecord record) {
        if (attritionRecords == null) {
            attritionRecords = new ArrayList<>();
        }
        record.setEmployee(this);
        attritionRecords.add(record);
    }

    public void addPerformanceEvaluations(List<EmployeePerformanceEvaluation> lists) {
        if (performanceEvaluations == null) {
            performanceEvaluations = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        performanceEvaluations.addAll(lists);
    }

    public void addRoutineAssignments(List<EmployeeRoutineAssignment> lists) {
        if (routineAssignments == null) {
            routineAssignments = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        routineAssignments.addAll(lists);
    }

    public void addEducationalQualifications(List<EmployeeEducationalQualification> lists) {
        if (educationalQualifications == null) {
            educationalQualifications = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        educationalQualifications.addAll(lists);
    }

    public void addBankAccounts(List<EmployeeBankAccount> lists) {
        if (bankAccounts == null) {
            bankAccounts = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        bankAccounts.addAll(lists);
    }

    public void addLanguageSkills(List<EmployeeLanguageSkill> lists) {
        if (languageSkills == null) {
            languageSkills = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        languageSkills.addAll(lists);
    }

    public void addContacts(List<EmployeeContactDetail> lists) {
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        contacts.addAll(lists);
    }

    public void addJobExperiences(List<EmployeeJobExperienceDetail> lists) {
        if (jobExperiences == null) {
            jobExperiences = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        jobExperiences.addAll(lists);
    }

    public void addTrainings(List<EmployeeTrainingDetail> lists) {
        if (trainings == null) {
            trainings = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        trainings.addAll(lists);
    }

    public void addLeaveRecords(List<EmployeeLeaveRecord> lists) {
        if (leaveRecords == null) {
            leaveRecords = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        leaveRecords.addAll(lists);
    }

    public void addAcrEvaluations(List<EmployeeAcrEvaluation> lists) {
        if (acrEvaluations == null) {
            acrEvaluations = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        acrEvaluations.addAll(lists);
    }

    public void addAcrEvaluation(EmployeeAcrEvaluation evaluation) {
        if (acrEvaluations == null) {
            acrEvaluations = new ArrayList<>();
        }
        evaluation.setEmployee(this);
        acrEvaluations.add(evaluation);
    }

    public void addPensionRequests(List<EmployeePensionRequest> lists) {
        if (pensionRequests == null) {
            pensionRequests = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        pensionRequests.addAll(lists);
    }

    public void addPensionRequest(EmployeePensionRequest pensionRequest) {
        if (pensionRequests == null) {
            pensionRequests = new ArrayList<>();
        }
        pensionRequest.setEmployee(this);
        pensionRequests.add(pensionRequest);
    }

    public void addPensionTransactions(List<EmployeePensionTransaction> lists) {
        if (pensionTransactions == null) {
            pensionTransactions = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        pensionTransactions.addAll(lists);
    }

    public void addPensionTransaction(EmployeePensionTransaction transaction) {
        if (pensionTransactions == null) {
            pensionTransactions = new ArrayList<>();
        }
        transaction.setEmployee(this);
        pensionTransactions.add(transaction);
    }

    public void addDepartmentalExamMerits(List<EmployeeDepartmentalExamMerit> lists) {
        if (departmentalExamMerits == null) {
            departmentalExamMerits = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        departmentalExamMerits.addAll(lists);
    }

    public void addDepartmentalExamMeritScores(List<EmployeeDepartmentalExamMeritScore> lists) {
        if (departmentalExamMeritScores == null) {
            departmentalExamMeritScores = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        departmentalExamMeritScores.addAll(lists);
    }

    public void addPublications(List<EmployeePublication> lists) {
        if (publications == null) {
            publications = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        publications.addAll(lists);
    }

    public void addPromotionRecords(List<EmployeePromotionRecord> lists) {
        if (promotionRecords == null) {
            promotionRecords = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        promotionRecords.addAll(lists);
    }

    public void addJoinings(List<EmployeeJoining> lists) {
        if (employeeJoinings == null) {
            employeeJoinings = new ArrayList<>();
        }
        lists.forEach(list -> list.setEmployee(this));
        employeeJoinings.addAll(lists);
    }

    public void addJoining(EmployeeJoining joining) {
        if (employeeJoinings == null) {
            employeeJoinings = new ArrayList<>();
        }
        joining.setEmployee(this);
        employeeJoinings.add(joining);
    }

    public void addLeaveRecordEnclosers(List<EmployeeLeaveRecordEncloser> lists) {
        if (leaveRecordEnclosers == null) {
            leaveRecordEnclosers = new ArrayList<>();
        }
        lists.forEach(l -> l.setEmployee(this));
        leaveRecordEnclosers.addAll(lists);
    }
}