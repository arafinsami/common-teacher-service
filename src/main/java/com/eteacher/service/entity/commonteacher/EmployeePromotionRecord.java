package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
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
@Table(name = "EMPLOYEE_PROMOTION_RECORD")
public class EmployeePromotionRecord extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_PROMOTION_RECORD_ID")
    private Long id;

    @Column(name = "EFFECTIVE_DATE")
    private Date effectiveDate;

    @Column(name = "PROMOTION_STATUS")
    private Integer promotionStatus;

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

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_PROMOTION_RECORD_ENCLOSER_ID")
    private EmployeePromotionRecordEncloser employeePromotionRecordEncloser;

/*  @OneToOne
  @JoinColumn(name = "FROM_INSTITUTE_ID", nullable = false)
  private Institute institute;

  @OneToOne
  @JoinColumn(name = "TO_INSTITUTE_ID", nullable = false)
  private Institute institute;*/

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @OneToOne
    @JoinColumn(name = "FROM_SALARY_SCALE_ID")
    private SalaryScale salaryScale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
