package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeMpoAffiliation;
import com.eteacher.service.enums.AffiliationStatus;
import com.eteacher.service.enums.ReviewStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeMpoAffiliationAudit {

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

    public static EmployeeMpoAffiliationAudit audit(EmployeeMpoAffiliation affiliation) {
        EmployeeMpoAffiliationAudit audit = new EmployeeMpoAffiliationAudit();
        affiliation.setId(affiliation.getId());
        affiliation.setEffectiveDate(affiliation.getEffectiveDate());
        affiliation.setAffiliationStatus(affiliation.getAffiliationStatus());
        affiliation.setReviewStatus(affiliation.getReviewStatus());
        affiliation.setIsApproved(affiliation.getIsApproved());
        affiliation.setApproverUserId(affiliation.getApproverUserId());
        affiliation.setApproverNote(affiliation.getApproverNote());
        affiliation.setApproveDate(affiliation.getApproveDate());
        affiliation.setFromDesignation(affiliation.getToDesignation());
        affiliation.setToDesignation(affiliation.getFromDesignation());
        return audit;
    }
}
