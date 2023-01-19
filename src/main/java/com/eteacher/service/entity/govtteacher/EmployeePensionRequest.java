package com.eteacher.service.entity.govtteacher;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.commonteacher.Employee;
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
@Table(name = "EMPLOYEE_PENSION_REQUEST")
public class EmployeePensionRequest extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_PENSION_REQUEST_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EFFECTIVE_DATE")
    private Date effectiveDate;

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

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "pensionRequest"
    )
    private List<EmployeePensionRequestEncloser> files;

    public void addFiles(List<EmployeePensionRequestEncloser> encloserList) {
        if (files == null) {
            files = new ArrayList<>();
        }
        files.addAll(encloserList);
    }
}
