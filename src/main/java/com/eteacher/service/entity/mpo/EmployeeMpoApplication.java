package com.eteacher.service.entity.mpo;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.commonteacher.Employee;
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
@Table(name = "EMPLOYEE_MPO_APPLICATION")
public class EmployeeMpoApplication extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_MPO_APPLICATION_ID")
    private Long id;

    @Column(name = "REVIEW_STATUS")
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

    @Column(name = "EMPLOYEE_APPLICATION_NAME")
    private String applicationName;

    @Column(name = "EMPLOYEE_APPLICATION_NAME_BN")
    private String applicationNameBn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

/*  @OneToOne
  @JoinColumn(name = "INSTITUTE_ID", nullable = false)
  private Institute institute;*/

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "mpoApplication"
    )
    private List<EmployeeMpoApplicationEncloser> files;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "mpoApplication"
    )
    private List<EmployeeMpoApplicationReviewer> reviewers;

    public void addReviewer(EmployeeMpoApplicationReviewer reviewer) {
        if (reviewers == null) {
            reviewers = new ArrayList<>();
        }
        reviewer.setMpoApplication(this);
        reviewers.add(reviewer);
    }

    public void addFiles(List<EmployeeMpoApplicationEncloser> lists) {
        if (files == null) {
            files = new ArrayList<>();
        }
        files.forEach(f -> f.setMpoApplication(this));
        files.addAll(lists);
    }
}
