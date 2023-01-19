package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeMpoAffiliationReviewer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeMpoAffiliationReviewerAudit {

    private Long id;

    private Integer reviewerSl;

    private Long reviewerUserId;

    private String reviewerNote;

    private Date reviewerDate;

    private Long reasonForRejection;

    public static EmployeeMpoAffiliationReviewerAudit audit(EmployeeMpoAffiliationReviewer reviewer) {
        EmployeeMpoAffiliationReviewerAudit audit = new EmployeeMpoAffiliationReviewerAudit();
        audit.setId(reviewer.getId());
        audit.setReviewerSl(reviewer.getReviewerSl());
        audit.setReviewerUserId(reviewer.getReviewerUserId());
        audit.setReviewerNote(reviewer.getReviewerNote());
        audit.setReviewerDate(reviewer.getReviewerDate());
        audit.setReasonForRejection(reviewer.getReasonForRejection());
        return audit;
    }
}
