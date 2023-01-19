package com.eteacher.service.entity.mpo;

import com.eteacher.service.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "EMPLOYEE_RETIREMENT_PAYMENT_BREAKDOWN")
public class EmployeeRetirementPaymentBreakdown extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_RETIREMENT_PAYMENT_BREAKDOWN_ID")
    private Long id;

    @Column(name = "SALARY_BREAKDOWN_ID")
    private Integer salaryBreakdownId;

    @Column(name = "AMOUNT")
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_RETIREMENT_PAYMENT_ID")
    private EmployeeRetirementPayment retirementPayment;

    /*@OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "EMPLOYEE_RETIREMENT_PAYMENT_ID")
    List<EmployeeRetirementPaymentEncloser> employeeRetirementPaymentEncloser;*/
}
