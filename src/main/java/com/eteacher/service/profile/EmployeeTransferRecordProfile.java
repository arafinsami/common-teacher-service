package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeTransferRecord;
import com.eteacher.service.enums.TransferStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;


@Data
@NoArgsConstructor
public class EmployeeTransferRecordProfile {

    private Long id;

    private Date effectiveDate;

    private TransferStatus transferStatus;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long fromDesignation;

    private Long toDesignation;

    private Long reasonForRejection;

    public EmployeeTransferRecord to() {
        EmployeeTransferRecord record = new EmployeeTransferRecord();
        record.setEffectiveDate(effectiveDate);
        record.setTransferStatus(transferStatus);
        record.setApproverUserId(approverUserId);
        record.setApproverNote(approverNote);
        record.setApproveDate(approveDate);
        record.setFromDesignation(fromDesignation);
        record.setToDesignation(toDesignation);
        record.setReasonForRejection(reasonForRejection);
        record.setRecordStatus(DRAFT);
        return record;
    }

    public EmployeeTransferRecord update() {
        EmployeeTransferRecord record = new EmployeeTransferRecord();
        record.setId(id);
        record.setEffectiveDate(effectiveDate);
        record.setTransferStatus(transferStatus);
        record.setApproverUserId(approverUserId);
        record.setApproverNote(approverNote);
        record.setApproveDate(approveDate);
        record.setFromDesignation(fromDesignation);
        record.setToDesignation(toDesignation);
        record.setReasonForRejection(reasonForRejection);
        record.setRecordStatus(ACTIVE);
        return record;
    }
}
