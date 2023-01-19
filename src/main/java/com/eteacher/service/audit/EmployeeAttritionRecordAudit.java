package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeAttritionRecord;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeAttritionRecordAudit {

    private Long id;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public static EmployeeAttritionRecordAudit audit(EmployeeAttritionRecord record) {
        EmployeeAttritionRecordAudit audit = new EmployeeAttritionRecordAudit();
        audit.setId(record.getId());
        audit.setStatus(record.getStatus());
        audit.setApproverUserId(record.getApproverUserId());
        audit.setApproveDate(record.getApproveDate());
        audit.setReasonForRejection(record.getReasonForRejection());
        return audit;
    }
}
