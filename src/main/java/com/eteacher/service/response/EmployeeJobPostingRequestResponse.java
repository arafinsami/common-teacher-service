package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeJobPostingRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeJobPostingRequestResponse {

    private Long id;

    private Date effectiveDate;

    private Integer postingStatus;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long fromDesignation;

    private Long toDesignation;

    public static EmployeeJobPostingRequestResponse response(EmployeeJobPostingRequest postingRequest) {
        EmployeeJobPostingRequestResponse response = new EmployeeJobPostingRequestResponse();
        response.setId(postingRequest.getId());
        response.setEffectiveDate(postingRequest.getEffectiveDate());
        response.setPostingStatus(postingRequest.getPostingStatus());
        response.setApproverUserId(postingRequest.getApproverUserId());
        response.setApproverNote(postingRequest.getApproverNote());
        response.setApproveDate(postingRequest.getApproveDate());
        response.setFromDesignation(postingRequest.getFromDesignation());
        response.setToDesignation(postingRequest.getToDesignation());
        return response;
    }
}
