package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeMpoAffiliationReviewer;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeMpoAffiliationReviewerResponse {

    private Long id;

    private Integer reviewerSl;

    private Long reviewerUserId;

    private String reviewerNote;

    private Date reviewerDate;

    private Long reasonForRejection;

    public static EmployeeMpoAffiliationReviewerResponse response(EmployeeMpoAffiliationReviewer reviewer) {
        EmployeeMpoAffiliationReviewerResponse response = new EmployeeMpoAffiliationReviewerResponse();
        response.setId(reviewer.getId());
        response.setReviewerSl(reviewer.getReviewerSl());
        response.setReviewerUserId(reviewer.getReviewerUserId());
        response.setReviewerNote(reviewer.getReviewerNote());
        response.setReviewerDate(reviewer.getReviewerDate());
        response.setReasonForRejection(reviewer.getReasonForRejection());
        return response;
    }
}
