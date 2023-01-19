package com.eteacher.service.entity.govtteacher;

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
@Table(name = "EMPLOYEE_PENSION_TRANSACTION_BREAKDOWN")
public class EmployeePensionTransactionBreakdown extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_PENSION_TRANSACTION_BREAKDOWN_ID")
    private Long id;

    @Column(name = "SALARY_BREAKDOWN_ID")
    private Integer salaryBreakdownId;

    @Column(name = "PENSION_AMOUNT")
    private Double pensionAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_PENSION_TRANSACTION_ID")
    private EmployeePensionTransaction pensionTransaction;
}
