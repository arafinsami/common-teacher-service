package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeMpoApplicationReviewer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeMpoApplicationReviewerProfile {

    private Long id;

    private Integer reviewerSl;

    private Long reviewerUserId;

    private String reviewerNote;

    private Date reviewerDate;

    private Long reasonForRejection;

    public EmployeeMpoApplicationReviewer to() {
        EmployeeMpoApplicationReviewer reviewer = new EmployeeMpoApplicationReviewer();
        reviewer.setReviewerSl(reviewerSl);
        reviewer.setReviewerUserId(reviewerUserId);
        reviewer.setReviewerNote(reviewerNote);
        reviewer.setReviewerDate(reviewerDate);
        reviewer.setReasonForRejection(reasonForRejection);
        reviewer.setRecordStatus(DRAFT);
        return reviewer;
    }

    public EmployeeMpoApplicationReviewer update() {
        EmployeeMpoApplicationReviewer reviewer = new EmployeeMpoApplicationReviewer();
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
