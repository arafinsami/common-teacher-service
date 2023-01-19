package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeMpoApplication;
import com.eteacher.service.enums.ReviewStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeeMpoApplicationResponse {

    private Long id;

    private ReviewStatus reviewStatus;

    private Boolean isApproved;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private String applicationName;

    private String applicationNameBn;

    private Long reasonForRejection;

    public static EmployeeMpoApplicationResponse response(EmployeeMpoApplication application) {
        EmployeeMpoApplicationResponse response = new EmployeeMpoApplicationResponse();
        response.setId(application.getId());
        response.setReviewStatus(application.getReviewStatus());
        response.setIsApproved(application.getIsApproved());
        response.setApproverUserId(application.getApproverUserId());
        response.setApproverNote(application.getApproverNote());
        response.setApproveDate(application.getApproveDate());
        response.setApplicationName(application.getApplicationName());
        response.setApplicationNameBn(application.getApplicationNameBn());
        response.setReasonForRejection(application.getReasonForRejection());
        return response;
    }
}
