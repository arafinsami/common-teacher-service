package com.eteacher.service.entity.mpo;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.enums.AffiliationStatus;
import com.eteacher.service.enums.ReviewStatus;
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
@Table(name = "EMPLOYEE_MPO_AFFILIATION")
public class EmployeeMpoAffiliation extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_MPO_AFFILIATION_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EFFECTIVE_DATE")
    private Date effectiveDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "AFFILIATION_STATUS", length = 15)
    private AffiliationStatus affiliationStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "REVIEW_STATUS", length = 12)
    private ReviewStatus reviewStatus;

    @Column(name = "IS_APPROVED")
    private Boolean isApproved;

    @Column(name = "APPROVER_USER_ID")
    private Long approverUserId;

    @Column(name = "APPROVER_NOTE")
    private String approverNote;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APPROVE_DATE")
    private Date approveDate;

    @Column(name = "FROM_DESIGNATION_ID")
    private Long fromDesignation;

    @Column(name = "TO_DESIGNATION_ID")
    private Long toDesignation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

/*  @OneToOne
  @JoinColumn(name = "FROM_INSTITUTE_ID", nullable = false)
  private Institute institute;

  @OneToOne
  @JoinColumn(name = "TO_INSTITUTE_ID", nullable = false)
  private Institute institute;

  @OneToOne
  @JoinColumn(name = "INSTITUTE_ID", nullable = false)
  private Institute institute;*/

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "mpoAffiliation"
    )
    private List<EmployeeMpoAffiliationReviewer> reviewers;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "mpoAffiliation"
    )
    private List<EmployeeMpoAffiliationEncloser> files;

    public void addReviewer(EmployeeMpoAffiliationReviewer reviewer) {
        if (reviewers == null) {
            reviewers = new ArrayList<>();
        }
        reviewer.setMpoAffiliation(this);
        reviewers.add(reviewer);
    }

    public void addFiles(List<EmployeeMpoAffiliationEncloser> lists) {
        if (reviewers == null) {
            reviewers = new ArrayList<>();
        }
        files.forEach(f -> f.setMpoAffiliation(this));
        files.addAll(lists);
    }
}
