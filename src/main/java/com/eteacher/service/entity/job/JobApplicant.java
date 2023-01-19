package com.eteacher.service.entity.job;

import com.eteacher.service.entity.BaseEntity;
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
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "JOB_APPLICANT")
public class JobApplicant extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_APPLICANT_ID")
    private Long id;

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

    @Column(name = "APPLICATION_TRACKING_NO")
    private String applicationTrackingNo;

    @Column(name = "PIN")
    private String pin;

    @Column(name = "APPLICANT_NAME")
    private String applicantName;

    @Column(name = "APPLICANT_NAME_BN")
    private String applicantNameBn;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", length = 10)
    private Gender gender;

    @Column(name = "HOME_DISTRICT_ID")
    private Long districtId;

    @OneToOne
    @JoinColumn(name = "JOB_CIRCULAR_ID")
    private JobCircular jobCircular;

    @Column(name = "RELIGION_ID")
    private Long religionId;

    @Column(name = "APPLICANT_SUBJECT_ID")
    private Long subjectId;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "applicant"
    )
    private List<JobApplicantJobExperience> experiences;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "applicant"
    )
    private List<JobApplicantEducationalQualification> qualifications;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "applicant"
    )
    private List<JobApplicantContact> contacts;

    public void addExperiences(List<JobApplicantJobExperience> lists) {
        if (experiences == null) {
            experiences = new ArrayList<>();
        }
        lists.forEach(list -> {
            list.setApplicant(this);
        });
        experiences.addAll(lists);
    }

    public void addQualifications(List<JobApplicantEducationalQualification> lists) {
        if (qualifications == null) {
            qualifications = new ArrayList<>();
        }
        lists.forEach(list -> {
            list.setApplicant(this);
        });
        qualifications.addAll(lists);
    }

    public void addContacts(List<JobApplicantContact> lists) {
        if (contacts == null) {
            contacts = new ArrayList<>();
        }
        lists.forEach(list -> {
            list.setApplicant(this);
        });
        contacts.addAll(lists);
    }
}
