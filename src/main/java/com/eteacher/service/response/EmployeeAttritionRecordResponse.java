package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeAttritionRecord;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeAttritionRecordResponse {

    private Long id;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public static EmployeeAttritionRecordResponse response(EmployeeAttritionRecord record) {
        EmployeeAttritionRecordResponse response = new EmployeeAttritionRecordResponse();
        response.setId(record.getId());
        response.setStatus(record.getStatus());
        response.setApproverUserId(record.getApproverUserId());
        response.setApproveDate(record.getApproveDate());
        response.setReasonForRejection(record.getReasonForRejection());
        return response;
    }
}
