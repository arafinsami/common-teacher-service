package com.eteacher.service.dto;

import com.eteacher.service.entity.job.JobCircular;
import com.eteacher.service.enums.Status;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class JobCircularDto {

    private Long id;

    @NotBlank
    private String jobCircularDescription;

    @NotBlank
    private String jobCircularDescriptionBn;

    @NotBlank
    private String jobCircularRefNo;

    @NotNull
    private Date jobCircularIssueDate;

    @NotNull
    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejectionId;

    public JobCircular to() {
        JobCircular circular = new JobCircular();
        circular.setJobCircularDescription(jobCircularDescription);
        circular.setJobCircularDescriptionBn(jobCircularDescriptionBn);
        circular.setJobCircularRefNo(jobCircularRefNo);
        circular.setJobCircularIssueDate(jobCircularIssueDate);
        circular.setStatus(status);
        circular.setApproverUserId(approverUserId);
        circular.setApproverNote(approverNote);
        circular.setApproveDate(approveDate);
        circular.setReasonForRejectionId(reasonForRejectionId);
        return circular;
    }

    public void update(JobCircular circular) {
        circular.setJobCircularDescription(jobCircularDescription);
        circular.setJobCircularDescriptionBn(jobCircularDescriptionBn);
        circular.setJobCircularRefNo(jobCircularRefNo);
        circular.setJobCircularIssueDate(jobCircularIssueDate);
        circular.setStatus(status);
        circular.setApproverUserId(approverUserId);
        circular.setApproverNote(approverNote);
        circular.setApproveDate(approveDate);
        circular.setReasonForRejectionId(reasonForRejectionId);
    }
}
