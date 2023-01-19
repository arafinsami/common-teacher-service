package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeMpoAffiliationReviewer;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
public class EmployeeMpoAffiliationReviewerProfile {

    private Long id;

    @NotNull
    private Integer reviewerSl;

    @NotNull
    private Long reviewerUserId;

    @NotBlank
    private String reviewerNote;

    @NotNull
    private Date reviewerDate;

    @NotNull
    private Long reasonForRejection;

    public EmployeeMpoAffiliationReviewer to() {
        EmployeeMpoAffiliationReviewer reviewer = new EmployeeMpoAffiliationReviewer();
        reviewer.setReviewerSl(reviewerSl);
        reviewer.setReviewerUserId(reviewerUserId);
        reviewer.setReviewerNote(reviewerNote);
        reviewer.setReviewerDate(reviewerDate);
        reviewer.setReasonForRejection(reasonForRejection);
        reviewer.setRecordStatus(DRAFT);
        return reviewer;
    }

    public EmployeeMpoAffiliationReviewer update() {
        EmployeeMpoAffiliationReviewer reviewer = new EmployeeMpoAffiliationReviewer();
        reviewer.setId(id);
        reviewer.setReviewerSl(reviewerSl);
        reviewer.setReviewerUserId(reviewerUserId);
        reviewer.setReviewerNote(reviewerNote);
        reviewer.setReviewerDate(reviewerDate);
        reviewer.setReasonForRejection(reasonForRejection);
        reviewer.setRecordStatus(ACTIVE);
        return reviewer;
    }
}
