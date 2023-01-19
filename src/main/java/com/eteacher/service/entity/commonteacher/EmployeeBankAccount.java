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
@Table(name = "EMPLOYEE_BANK_ACCOUNT")
@EqualsAndHashCode(callSuper = false)
public class EmployeeBankAccount extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_BANK_ACCOUNT_ID")
    private Long id;

    @Column(name = "BANK_ACCOUNT_NUMBER")
    private String bankAccountNumber;

    @Column(name = "IS_ACTIVE_FOR_SALARY_DISBURSEMENT")
    private Boolean isActiveForSalaryDisbursement;

    @Column(name = "IS_OPERATIVE")
    private Boolean isOperative;

    @Column(name = "LAST_DISBURSEMENT_DATE")
    private Date lastDisbursementDate;

    @OneToOne
    @JoinColumn(name = "BANK_ID")
    private Bank bank;

    @OneToOne
    @JoinColumn(name = "BANK_BRANCH_ID")
    private BankBranch bankBranch;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ENCLOSER_ID")
    private EmployeeEncloser employeeEncloser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
