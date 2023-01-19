package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeJobPostingRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeJobPostingRequestProfile {

    private Long id;

    private Date effectiveDate;

    private Integer postingStatus;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long fromDesignation;

    private Long toDesignation;

    public EmployeeJobPostingRequest to() {
        EmployeeJobPostingRequest posting = new EmployeeJobPostingRequest();
        posting.setEffectiveDate(effectiveDate);
        posting.setPostingStatus(postingStatus);
        posting.setApproverUserId(approverUserId);
        posting.setApproverNote(approverNote);
        posting.setApproveDate(approveDate);
        posting.setFromDesignation(fromDesignation);
        posting.setToDesignation(toDesignation);
        posting.setRecordStatus(DRAFT);
        return posting;
    }

    public EmployeeJobPostingRequest update() {
        EmployeeJobPostingRequest posting = new EmployeeJobPostingRequest();
        posting.setId(id);
        posting.setEffectiveDate(effectiveDate);
        posting.setPostingStatus(postingStatus);
        posting.setApproverUserId(approverUserId);
        posting.setApproverNote(approverNote);
        posting.setApproveDate(approveDate);
        posting.setFromDesignation(fromDesignation);
        posting.setToDesignation(toDesignation);
        posting.setRecordStatus(ACTIVE);
        return posting;
    }
}
