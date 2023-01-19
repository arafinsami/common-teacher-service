package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeMpoApplicationReviewer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeMpoApplicationReviewerAudit {

    private Long id;

    private Integer reviewerSl;

    private Long reviewerUserId;

    private String reviewerNote;

    private Date reviewerDate;

    private Long reasonForRejection;

    public static EmployeeMpoApplicationReviewerAudit audit(EmployeeMpoApplicationReviewer reviewer) {
        EmployeeMpoApplicationReviewerAudit audit = new EmployeeMpoApplicationReviewerAudit();
        audit.setId(reviewer.getId());
        audit.setReviewerSl(reviewer.getReviewerSl());
        audit.setReviewerUserId(reviewer.getReviewerUserId());
        audit.setReviewerNote(reviewer.getReviewerNote());
        audit.setReviewerDate(reviewer.getReviewerDate());
        audit.setReasonForRejection(reviewer.getReasonForRejection());
        return audit;
    }
}
