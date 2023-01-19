package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeMpoApplication;
import com.eteacher.service.enums.ReviewStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeeMpoApplicationAudit {

    private Long id;

    private ReviewStatus reviewStatus;

    private Boolean isApproved;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private String applicationName;

    private String applicationNameBn;

    private Long reasonForRejection;

    public static EmployeeMpoApplicationAudit audit(EmployeeMpoApplication mpoApplication) {
        EmployeeMpoApplicationAudit audit = new EmployeeMpoApplicationAudit();
        audit.setReviewStatus(mpoApplication.getReviewStatus());
        audit.setIsApproved(mpoApplication.getIsApproved());
        audit.setApproverUserId(mpoApplication.getApproverUserId());
        audit.setApproverNote(mpoApplication.getApproverNote());
        audit.setApproveDate(mpoApplication.getApproveDate());
        audit.setApplicationName(mpoApplication.getApplicationName());
        audit.setApplicationNameBn(mpoApplication.getApplicationNameBn());
        audit.setReasonForRejection(mpoApplication.getReasonForRejection());
        return audit;
    }
}
