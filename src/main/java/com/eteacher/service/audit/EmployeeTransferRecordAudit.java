package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeTransferRecord;
import com.eteacher.service.enums.TransferStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeTransferRecordAudit {

    private Long id;

    private Date effectiveDate;

    private TransferStatus transferStatus;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long fromDesignation;

    private Long toDesignation;

    private Long reasonForRejection;

    public static EmployeeTransferRecordAudit audit(EmployeeTransferRecord record) {
        EmployeeTransferRecordAudit audit = new EmployeeTransferRecordAudit();
        audit.setId(record.getId());
        audit.setEffectiveDate(record.getEffectiveDate());
        audit.setTransferStatus(record.getTransferStatus());
        audit.setApproverUserId(record.getApproverUserId());
        audit.setApproverNote(record.getApproverNote());
        audit.setApproveDate(record.getApproveDate());
        audit.setFromDesignation(record.getFromDesignation());
        audit.setToDesignation(record.getToDesignation());
        audit.setReasonForRejection(record.getReasonForRejection());
        return audit;
    }
}
