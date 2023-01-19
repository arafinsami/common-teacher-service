package com.eteacher.service.entity.mpo;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
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
@Table(name = "EMPLOYEE_SALARY_INCREMENT_BREAKDOWN")
public class EmployeeSalaryIncrementBreakdown extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_SALARY_INCREMENT_BREAKDOWN_ID")
    private Long id;

    @Column(name = "AMOUNT")
    private Double amount;

    @OneToOne
    @JoinColumn(name = "SALARY_BREAKDOWN_ID", nullable = false)
    private SalaryBreakdown salaryBreakdown;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_SALARY_INCREMENT_ID")
    private EmployeeSalaryIncrement salaryIncrement;
}
