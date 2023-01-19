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
@Table(name = "EMPLOYEE_LEAVE_RECORD")
@EqualsAndHashCode(callSuper = false)
public class EmployeeLeaveRecord extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_LEAVE_RECORD_ID")
    private Long id;

    @Column(name = "LEAVE_START_DATE")
    private Date leaveStartDate;

    @Column(name = "LEAVE_END_DATE")
    private Date leaveEndDate;

    @Column(name = "REVIEWER_USER_ID")
    private Long reviewerUserId;

    @Column(name = "REVIEW_DATE")
    private Date reviewDate;

    @Column(name = "REVIEW_NOTE")
    private String reviewNote;

    @Column(name = "REVIEW_STATUS")
    private Boolean reviewStatus;

    @Column(name = "EMPLOYEE_LEAVE_RECORD_ID_LEGACY")
    private Long employeeLeaveRecordId;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_LEAVE_RECORD_ENCLOSER_ID")
    private EmployeeLeaveRecordEncloser employeeLeaveRecordEncloser;

    @Column(name = "LEAVE_TYPE_ID")
    private Long leaveType;

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
