package com.eteacher.service.entity.mpo;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.SalaryScale;
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
@Table(name = "EMPLOYEE_SALARY_INCREMENT")
public class EmployeeSalaryIncrement extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_SALARY_INCREMENT_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EFFECTIVE_DATE")
    private Date effectiveDate;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "SMS_NOTIFIED")
    private Boolean smsNotified;

    @Column(name = "EMAIL_NOTIFIED")
    private Boolean emailNotified;

    @Column(name = "STATUS")
    private Integer status;

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

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @OneToOne
    @JoinColumn(name = "SALARY_SCALE_ID_FROM")
    private SalaryScale salaryScale;

    @OneToOne
    @JoinColumn(name = "SALARY_SCALE_ID")
    private SalaryScale salaryScal1;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "salaryIncrement"
    )
    private List<EmployeeSalaryIncrementBreakdown> breakdowns;

    public void addBreakdown(EmployeeSalaryIncrementBreakdown breakdown) {
        if (breakdowns == null) {
            breakdowns = new ArrayList<>();
        }
        breakdown.setSalaryIncrement(this);
        breakdowns.add(breakdown);
    }
}
