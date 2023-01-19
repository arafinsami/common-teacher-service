package com.eteacher.service.audit;

import com.eteacher.service.entity.govtteacher.EmployeePensionRequest;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeePensionRequestAudit {

    private Long id;

    private Date effectiveDate;

    private String remarks;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public static EmployeePensionRequestAudit audit(EmployeePensionRequest request) {
        EmployeePensionRequestAudit audit = new EmployeePensionRequestAudit();
        audit.setId(request.getId());
        audit.setEffectiveDate(request.getEffectiveDate());
        audit.setRemarks(request.getRemarks());
        audit.setStatus(request.getStatus());
        audit.setApproverUserId(request.getApproverUserId());
        audit.setApproverNote(request.getApproverNote());
        audit.setApproveDate(request.getApproveDate());
        audit.setReasonForRejection(request.getReasonForRejection());
        return audit;
    }
}
