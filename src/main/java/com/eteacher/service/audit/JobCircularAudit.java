package com.eteacher.service.audit;

import com.eteacher.service.entity.job.JobCircular;
import lombok.Data;

import java.util.Date;

@Data
public class JobCircularAudit {

    private Long id;

    private String jobCircularDescription;

    private String jobCircularDescriptionBn;

    private String jobCircularRefNo;

    private Date jobCircularIssueDate;

    private Integer status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public static JobCircularAudit audit(JobCircular circular) {
        JobCircularAudit audit = new JobCircularAudit();
        audit.setId(circular.getId());
        audit.setJobCircularDescription(circular.getJobCircularDescription());
        audit.setJobCircularDescriptionBn(circular.getJobCircularDescriptionBn());
        audit.setJobCircularRefNo(circular.getJobCircularRefNo());
        audit.setJobCircularIssueDate(circular.getJobCircularIssueDate());
        audit.setApproverUserId(circular.getApproverUserId());
        audit.setApproverNote(circular.getApproverNote());
        audit.setApproveDate(circular.getApproveDate());
        audit.setReasonForRejection(circular.getReasonForRejectionId());
        return audit;
    }
}
