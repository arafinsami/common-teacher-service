package com.eteacher.service.response;

import com.eteacher.service.entity.job.JobCircular;
import com.eteacher.service.enums.Status;
import lombok.Data;

import java.util.Date;

@Data
public class JobCircularResponse {

    private Long id;

    private String jobCircularDescription;

    private String jobCircularDescriptionBn;

    private String jobCircularRefNo;

    private Date jobCircularIssueDate;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejectionId;

    public static JobCircularResponse response(JobCircular circular) {
        JobCircularResponse response = new JobCircularResponse();
        response.setId(circular.getId());
        response.setJobCircularDescription(circular.getJobCircularDescription());
        response.setJobCircularDescriptionBn(circular.getJobCircularDescriptionBn());
        response.setJobCircularRefNo(circular.getJobCircularRefNo());
        response.setJobCircularIssueDate(circular.getJobCircularIssueDate());
        response.setStatus(circular.getStatus());
        response.setApproverUserId(circular.getApproverUserId());
        response.setApproverNote(circular.getApproverNote());
        response.setApproveDate(circular.getApproveDate());
        response.setReasonForRejectionId(circular.getReasonForRejectionId());
        return response;
    }
}
