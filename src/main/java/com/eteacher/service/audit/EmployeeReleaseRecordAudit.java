package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeReleaseRecord;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeeReleaseRecordAudit {

    private Long id;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public static EmployeeReleaseRecordAudit audit(EmployeeReleaseRecord record) {
        EmployeeReleaseRecordAudit audit = new EmployeeReleaseRecordAudit();
        audit.setId(record.getId());
        audit.setStatus(record.getStatus());
        audit.setApproverUserId(record.getApproverUserId());
        audit.setApproverNote(record.getApproverNote());
        audit.setApproveDate(record.getApproveDate());
        audit.setReasonForRejection(record.getReasonForRejection());
        return audit;
    }
}
