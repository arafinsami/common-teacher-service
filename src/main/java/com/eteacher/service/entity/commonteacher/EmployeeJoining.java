package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "EMPLOYEE_JOINING")
@EqualsAndHashCode(callSuper = false)
public class EmployeeJoining extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_JOINING_ID")
    private Long id;

    @Temporal(TemporalType.DATE)
    @JsonFormat(
            pattern = "yyyy-MM-dd'T'HH:mm:ss",
            shape = JsonFormat.Shape.STRING
    )
    @Column(name = "EFFECTIVE_DATE")
    private Date effectiveDate;

    @Column(name = "POSTING_STATUS")
    private Integer postingStatus;

    @Column(name = "APPROVER_USER_ID")
    private Long approverUserId;

    @Column(name = "APPROVER_NOTE")
    private String approverNote;

    @Temporal(TemporalType.DATE)
    @JsonFormat(
            pattern = "yyyy-MM-dd'T'HH:mm:ss",
            shape = JsonFormat.Shape.STRING
    )
    @Column(name = "APPROVE_DATE")
    private Date approveDate;

    @Column(name = "FROM_DESIGNATION_ID")
    private Long fromDesignation;

    @Column(name = "TO_DESIGNATION_ID")
    private Long toDesignation;

    @Column(name = "FROM_INSTITUTE_ID")
    private Long fromInstitute;

    @Column(name = "TO_INSTITUTE_ID")
    private Long toInstitute;

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "employeeJoining"
    )
    private List<EmployeeJoiningEncloser> files;

    public void addFiles(List<EmployeeJoiningEncloser> lists) {
        if (files == null) {
            files = new ArrayList<>();
        }
        lists.forEach(l -> l.setEmployeeJoining(this));
        files.addAll(lists);
    }

}
