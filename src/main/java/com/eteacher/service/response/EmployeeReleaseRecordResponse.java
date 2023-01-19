package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeReleaseRecord;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeeReleaseRecordResponse {

    private Long id;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public static EmployeeReleaseRecordResponse response(EmployeeReleaseRecord record) {
        EmployeeReleaseRecordResponse response = new EmployeeReleaseRecordResponse();
        response.setId(record.getId());
        response.setStatus(record.getStatus());
        response.setApproverUserId(record.getApproverUserId());
        response.setApproverNote(record.getApproverNote());
        response.setApproveDate(record.getApproveDate());
        response.setReasonForRejection(record.getReasonForRejection());
        return response;
    }
}
