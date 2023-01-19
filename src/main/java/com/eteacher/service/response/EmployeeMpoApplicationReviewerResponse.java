package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeMpoApplicationReviewer;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeMpoApplicationReviewerResponse {

    private Long id;

    private Integer reviewerSl;

    private Long reviewerUserId;

    private String reviewerNote;

    private Date reviewerDate;

    private Long reasonForRejection;

    public static EmployeeMpoApplicationReviewerResponse response(EmployeeMpoApplicationReviewer reviewer) {
        EmployeeMpoApplicationReviewerResponse response = new EmployeeMpoApplicationReviewerResponse();
        response.setId(reviewer.getId());
        response.setReviewerSl(reviewer.getReviewerSl());
        response.setReviewerUserId(reviewer.getReviewerUserId());
        response.setReviewerNote(reviewer.getReviewerNote());
        response.setReviewerDate(reviewer.getReviewerDate());
        response.setReasonForRejection(reviewer.getReasonForRejection());
        return response;
    }
}
