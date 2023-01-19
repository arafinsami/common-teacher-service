package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeJobPostingRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeJobPostingRequestAudit {

    private Long id;

    private Date effectiveDate;

    private Integer postingStatus;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long fromDesignation;

    private Long toDesignation;

    public static EmployeeJobPostingRequestAudit audit(EmployeeJobPostingRequest postingRequest) {
        EmployeeJobPostingRequestAudit audit = new EmployeeJobPostingRequestAudit();
        audit.setId(postingRequest.getId());
        audit.setEffectiveDate(postingRequest.getEffectiveDate());
        audit.setPostingStatus(postingRequest.getPostingStatus());
        audit.setApproverUserId(postingRequest.getApproverUserId());
        audit.setApproverNote(postingRequest.getApproverNote());
        audit.setApproveDate(postingRequest.getApproveDate());
        audit.setFromDesignation(postingRequest.getFromDesignation());
        audit.setToDesignation(postingRequest.getToDesignation());
        return audit;
    }
}
