package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeRetirementRequest;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeeRetirementRequestResponse {

    private Long id;

    private Date effectiveDate;

    private String remarks;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public static EmployeeRetirementRequestResponse response(EmployeeRetirementRequest request) {
        EmployeeRetirementRequestResponse response = new EmployeeRetirementRequestResponse();
        response.setId(request.getId());
        response.setEffectiveDate(request.getEffectiveDate());
        response.setRemarks(request.getRemarks());
        response.setStatus(request.getStatus());
        response.setApproverUserId(request.getApproverUserId());
        response.setApproverNote(request.getApproverNote());
        response.setApproveDate(request.getApproveDate());
        response.setReasonForRejection(request.getReasonForRejection());
        return response;
    }
}
