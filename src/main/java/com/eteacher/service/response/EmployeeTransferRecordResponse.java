package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeTransferRecord;
import com.eteacher.service.enums.TransferStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeTransferRecordResponse {

    private Long id;

    private Date effectiveDate;

    private TransferStatus transferStatus;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long fromDesignation;

    private Long toDesignation;

    private Long reasonForRejection;

    public static EmployeeTransferRecordResponse response(EmployeeTransferRecord record) {
        EmployeeTransferRecordResponse response = new EmployeeTransferRecordResponse();
        response.setId(record.getId());
        response.setEffectiveDate(record.getEffectiveDate());
        response.setTransferStatus(record.getTransferStatus());
        response.setApproverUserId(record.getApproverUserId());
        response.setApproverNote(record.getApproverNote());
        response.setApproveDate(record.getApproveDate());
        response.setFromDesignation(record.getFromDesignation());
        response.setToDesignation(record.getToDesignation());
        response.setReasonForRejection(record.getReasonForRejection());
        return response;
    }
}
