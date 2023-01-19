package com.eteacher.service.entity.mpo;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.PaymentPeriod;
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
@Table(name = "EMPLOYEE_MPO_SALARY_EXTRA_BENEFIT")
public class EmployeeMpoSalaryExtraBenefit extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_MPO_SALARY_EXTRA_BENEFIT_ID")
    private Long id;

    @Column(name = "IS_APPROVED")
    private Boolean isApproved;

    @Column(name = "APPROVER_USER_ID")
    private Long approverUserId;

    @Column(name = "APPROVER_NOTE")
    private String approverNote;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APPROVE_DATE")
    private Date approveDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

/*  @OneToOne
  @JoinColumn(name = "INSTITUTE_ID", nullable = false)
  private Institute institute;*/

    @OneToOne
    @JoinColumn(name = "PAYMENT_PERIOD_ID")
    private PaymentPeriod paymentPeriod;

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "salaryExtraBenefit"
    )
    private List<EmployeeMpoSalaryExtraBenefitBreakdown> breakdowns;

    public void addBreakdown(EmployeeMpoSalaryExtraBenefitBreakdown breakdown) {
        if (breakdowns == null) {
            breakdowns = new ArrayList<>();
        }
        breakdowns.add(breakdown);
    }
}
