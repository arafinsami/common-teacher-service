package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeRetirementRequest;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeeRetirementRequestAudit {

    private Long id;

    private Date effectiveDate;

    private String remarks;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public static EmployeeRetirementRequestAudit audit(EmployeeRetirementRequest request) {
        EmployeeRetirementRequestAudit audit = new EmployeeRetirementRequestAudit();
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
