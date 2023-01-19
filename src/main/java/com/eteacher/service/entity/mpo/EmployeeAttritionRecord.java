package com.eteacher.service.entity.mpo;

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
@Table(name = "EMPLOYEE_ATTRITION_RECORD")
public class EmployeeAttritionRecord extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ATTRITION_RECORD_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
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

/*  @OneToOne
  @JoinColumn(name = "PRESENT_INSTITUTE_ID", nullable = false)
  private Institute institute;*/

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employeeAttritionRecord"
    )
    private List<EmployeeAttritionRecordEncloser> files;

    public void addFiles(List<EmployeeAttritionRecordEncloser> lists) {
        if (files == null) {
            files = new ArrayList<>();
        }
        lists.forEach(l -> l.setEmployeeAttritionRecord(this));
        files.addAll(lists);
    }
}
