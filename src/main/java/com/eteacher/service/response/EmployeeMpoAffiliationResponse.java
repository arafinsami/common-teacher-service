package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeMpoAffiliation;
import com.eteacher.service.enums.AffiliationStatus;
import com.eteacher.service.enums.ReviewStatus;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeMpoAffiliationResponse {

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

    public static EmployeeMpoAffiliationResponse response(EmployeeMpoAffiliation affiliation) {
        EmployeeMpoAffiliationResponse response = new EmployeeMpoAffiliationResponse();
        response.setId(affiliation.getId());
        response.setEffectiveDate(affiliation.getEffectiveDate());
        response.setAffiliationStatus(affiliation.getAffiliationStatus());
        response.setReviewStatus(affiliation.getReviewStatus());
        response.setIsApproved(affiliation.getIsApproved());
        response.setApproverUserId(affiliation.getApproverUserId());
        response.setApproverNote(affiliation.getApproverNote());
        response.setApproveDate(affiliation.getApproveDate());
        response.setFromDesignation(affiliation.getFromDesignation());
        response.setToDesignation(affiliation.getToDesignation());
        return response;
    }
}
