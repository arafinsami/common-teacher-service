package com.eteacher.service.entity.job;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.enums.JobNature;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "JOB_APPLICANT_JOB_EXPERIENCE_DETAIL")
public class JobApplicantJobExperience extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_APPLICANT_JOB_EXPERIENCE_DETAIL_ID")
    private Long id;

    @Column(name = "JOB_APPLICANT_JOB_EXPERIENCE_SL")
    private Long jobApplicantJobExperienceSl;

    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;

    @Column(name = "ORGANIZATION_ADDRESS")
    private String organizationAddress;

    @Column(name = "DESIGNATION_NAME")
    private String designationName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_OF_JOINING")
    private Date dateOfJoining;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_OF_RELEASE")
    private Date dateOfRelease;

    @Column(name = "IS_PRESENT")
    private Boolean isPresent;

    @Enumerated(EnumType.STRING)
    @Column(name = "JOB_NATURE", length = 12)
    private JobNature jobNature;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_APPLICANT_ID")
    private JobApplicant applicant;
}
