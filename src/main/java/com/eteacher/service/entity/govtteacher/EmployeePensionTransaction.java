package com.eteacher.service.entity.govtteacher;

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
@Table(name = "EMPLOYEE_PENSION_TRANSACTION")
public class EmployeePensionTransaction extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_PENSION_TRANSACTION_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;

    @Column(name = "BANK_ACCOUNT_NUMBER")
    private String bankAccountNumber;

    @Column(name = "BANK_BRANCH_ID")
    private Long bankBranchId;

    @Column(name = "SMS_NOTIFIED")
    private Boolean smsNotified;

    @Column(name = "EMAIL_NOTIFIED")
    private Boolean emailNotified;

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

    @OneToOne
    @JoinColumn(name = "PAYMENT_METHOD_ID")
    private PaymentMethod paymentMethod;

    @OneToOne
    @JoinColumn(name = "PAYMENT_PERIOD_ID")
    private PaymentPeriod paymentPeriod;

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @OneToOne
    @JoinColumn(name = "PENSION_SCALE_ID")
    private SalaryScale salaryScale;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "pensionTransaction"
    )
    private List<EmployeePensionTransactionBreakdown> transactionBreakdowns;

    public void addBreakdown(EmployeePensionTransactionBreakdown breakdown) {
        if (transactionBreakdowns == null) {
            transactionBreakdowns = new ArrayList<>();
        }
        breakdown.setPensionTransaction(this);
        transactionBreakdowns.add(breakdown);
    }
}
