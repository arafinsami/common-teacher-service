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
@Table(name = "EMPLOYEE_SALARY_TRANSACTION_BREAKDOWN")
public class EmployeeSalaryTransactionBreakdown extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_SALARY_TRANSACTION_BREAKDOWN_ID")
    private Long id;

    @Column(name = "SALARY_BREAKDOWN_ID")
    private Integer salaryBreakdownId;

    @Column(name = "AMOUNT")
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_SALARY_TRANSACTION_ID")
    private EmployeeSalaryTransaction salaryTransaction;
}
