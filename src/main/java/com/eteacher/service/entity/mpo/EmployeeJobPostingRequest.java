package com.eteacher.service.entity.mpo;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.commonteacher.Employee;
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
@Table(name = "EMPLOYEE_JOB_POSTING_REQUEST")
public class EmployeeJobPostingRequest extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_JOB_POSTING_REQUEST_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EFFECTIVE_DATE")
    private Date effectiveDate;

    @Column(name = "POSTING_STATUS")
    private Integer postingStatus;

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
  private Institute fromInstitute;

  @OneToOne
  @JoinColumn(name = "TO_INSTITUTE_ID", nullable = false)
  private Institute institute;*/

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "jobPostingRequest"
    )
    private List<EmployeeJobPostingRequestEncloser> files;

    public void addFiles(List<EmployeeJobPostingRequestEncloser> lists) {
        if (files == null) {
            files = new ArrayList<>();
        }
        lists.forEach(l -> l.setJobPostingRequest(this));
        files.addAll(lists);
    }
}
