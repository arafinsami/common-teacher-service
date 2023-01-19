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
@Table(name = "EMPLOYEE_MPO_SALARY_EXTRA_BENEFIT_BREAKDOWN")
public class EmployeeMpoSalaryExtraBenefitBreakdown extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_MPO_SALARY_EXTRA_BENEFIT_BREAKDOWN_ID")
    private Long id;

    @Column(name = "EXTRA_BENEFIT_BREAKDOWN_SL")
    private Integer extraBenefitBreakdownSl;

    @Column(name = "EXTRA_BENEFIT_BREAKDOWN_DESCRIPTION")
    private String extraBenefitBreakdownDescription;

    @Column(name = "EXTRA_BENEFIT_BREAKDOWN_DESCRIPTION_BN")
    private String extraBenefitBreakdownDescriptionBn;

    @Column(name = "BENEFIT_AMOUNT")
    private Double benefitAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_MPO_SALARY_EXTRA_BENEFIT_ID")
    private EmployeeMpoSalaryExtraBenefit salaryExtraBenefit;
}
