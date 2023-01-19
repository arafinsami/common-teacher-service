package com.eteacher.service.entity.mpo;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.PaymentMethod;
import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.enums.Status;
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
@Table(name = "EMPLOYEE_RETIREMENT_PAYMENT")
public class EmployeeRetirementPayment extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_RETIREMENT_PAYMENT_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @Column(name = "BANK_ACCOUNT_NUMBER")
    private String bankAccountNumber;

    @Column(name = "BANK_BRANCH_ID")
    private Long bankBranchId;

    @Column(name = "REMARKS")
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 12)
    private Status status;

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
    @JoinColumn(name = "PAYMENT_METHOD_ID")
    private PaymentMethod paymentMethod;

    @OneToOne
    @JoinColumn(name = "PAYMENT_PERIOD_ID")
    private PaymentPeriod paymentPeriod;

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @OneToOne
    @JoinColumn(name = "SALARY_SCALE_ID")
    private SalaryScale salaryScale;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "retirementPayment"
    )
    private List<EmployeeRetirementPaymentEncloser> files;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "retirementPayment"
    )
    private List<EmployeeRetirementPaymentBreakdown> breakdowns;

    public void addBreakdown(EmployeeRetirementPaymentBreakdown breakdown) {
        if (breakdowns == null) {
            breakdowns = new ArrayList<>();
        }
        breakdowns.add(breakdown);
    }

    public void addFiles(List<EmployeeRetirementPaymentEncloser> lists) {
        if (files == null) {
            files = new ArrayList<>();
        }
        lists.forEach(l -> l.setRetirementPayment(this));
        files.addAll(lists);
    }
}
