package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.enums.AttendanceStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "EMPLOYEE_ATTENDANCE")
@EqualsAndHashCode(callSuper = false)
public class EmployeeAttendance extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ATTENDANCE_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(
            pattern = "yyyy-MM-dd",
            shape = JsonFormat.Shape.STRING
    )
    @Column(name = "DATE_OF_ATTENDANCE")
    private Date dateOfAttendance;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(
            pattern = "yyyy-MM-dd'T'HH:mm:ss",
            shape = JsonFormat.Shape.STRING
    )
    @Column(name = "IN_AT")
    private Date inAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(
            pattern = "yyyy-MM-dd'T'HH:mm:ss",
            shape = JsonFormat.Shape.STRING
    )
    @Column(name = "OUT_AT")
    private Date outAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "ATTENDANCE_STATUS", length = 10)
    private AttendanceStatus status;

    @Column(name = "IS_LATE_ABSENT_APPROVED")
    private Boolean isLateAbsentApproved;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "APPROVER_USER_ID")
    private Long approverUserId;

    @Column(name = "APPROVER_NOTE")
    private String approverNote;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(
            pattern = "yyyy-MM-dd'T'HH:mm:ss",
            shape = JsonFormat.Shape.STRING
    )
    @Column(name = "APPROVE_DATE")
    private Date approveDate;

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Override
    public boolean equals(Object obj){
        if(obj.hashCode() == this.hashCode()) return true;
        else return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.employee.getId());
    }
}
