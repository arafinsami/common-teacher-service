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
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "EMPLOYEE_PAYMENT_ARREAR")
public class EmployeePaymentArrear extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_PAYMENT_ARREAR_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ARREAR_DATE_FROM")
    private Date arrearDateFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ARREAR_DATE_TO")
    private Date arrearDateTo;

    @Column(name = "IS_APPROVED")
    private Boolean isApproved;

    @Column(name = "APPROVER_USER_ID")
    private Long approverUserId;

    @Column(name = "APPROVER_NOTE")
    private String approverNote;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APPROVE_DATE")
    private Date approveDate;

    @OneToOne
    @JoinColumn(name = "ARREAR_TYPE_ID")
    private ArrearType arrearType;

    @OneToOne
    @JoinColumn(name = "PAYMENT_PERIOD_ID")
    private PaymentPeriod paymentPeriod;

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
