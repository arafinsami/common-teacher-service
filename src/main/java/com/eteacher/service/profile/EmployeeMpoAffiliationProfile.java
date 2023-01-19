package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeMpoAffiliation;
import com.eteacher.service.enums.AffiliationStatus;
import com.eteacher.service.enums.ReviewStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeMpoAffiliationProfile {

    private Long id;

    private Date effectiveDate;

    private AffiliationStatus affiliationStatus;

    private ReviewStatus reviewStatus;

    private Boolean isApproved;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long fromDesignation;

    private Long toDesignation;

    public EmployeeMpoAffiliation to() {
        EmployeeMpoAffiliation affiliation = new EmployeeMpoAffiliation();
        affiliation.setEffectiveDate(effectiveDate);
        affiliation.setAffiliationStatus(affiliationStatus);
        affiliation.setReviewStatus(reviewStatus);
        affiliation.setIsApproved(isApproved);
        affiliation.setApproverUserId(approverUserId);
        affiliation.setApproverNote(approverNote);
        affiliation.setApproveDate(approveDate);
        affiliation.setFromDesignation(fromDesignation);
        affiliation.setToDesignation(toDesignation);
        affiliation.setRecordStatus(DRAFT);
        return affiliation;
    }

    public EmployeeMpoAffiliation update() {
        EmployeeMpoAffiliation affiliation = new EmployeeMpoAffiliation();
        affiliation.setId(id);
        affiliation.setEffectiveDate(effectiveDate);
        affiliation.setAffiliationStatus(affiliationStatus);
        affiliation.setReviewStatus(reviewStatus);
        affiliation.setIsApproved(isApproved);
        affiliation.setApproverUserId(approverUserId);
        affiliation.setApproverNote(approverNote);
        affiliation.setApproveDate(approveDate);
        affiliation.setFromDesignation(fromDesignation);
        affiliation.setToDesignation(toDesignation);
        affiliation.setRecordStatus(ACTIVE);
        return affiliation;
    }
}
