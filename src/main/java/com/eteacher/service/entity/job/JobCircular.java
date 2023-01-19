package com.eteacher.service.entity.job;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.enums.Status;
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
@EqualsAndHashCode(callSuper = false)
@Table(name = "JOB_CIRCULAR")
public class JobCircular extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_CIRCULAR_ID")
    private Long id;

    @Column(name = "JOB_CIRCULAR_DESCRIPTION")
    private String jobCircularDescription;

    @Column(name = "JOB_CIRCULAR_DESCRIPTION_BN")
    private String jobCircularDescriptionBn;

    @Column(name = "JOB_CIRCULAR_REF_NO")
    private String jobCircularRefNo;

    @Column(name = "JOB_CIRCULAR_ISSUE_DATE")
    private Date jobCircularIssueDate;

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

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejectionId;
}
