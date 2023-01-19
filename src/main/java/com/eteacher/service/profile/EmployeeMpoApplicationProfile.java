package com.eteacher.service.profile;


import com.eteacher.service.entity.mpo.EmployeeMpoApplication;
import com.eteacher.service.enums.ReviewStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeMpoApplicationProfile {

    private Long id;

    private Long employeeId;

    @NotNull
    private ReviewStatus reviewStatus;

    @NotNull
    private Boolean isApproved;

    @NotNull
    private Long approverUserId;

    @NotBlank
    private String approverNote;

    @NotNull
    private Date approveDate;

    @NotBlank
    private String applicationName;

    @NotBlank
    private String applicationNameBn;

    @NotNull
    private Long reasonForRejection;

    public EmployeeMpoApplication to() {
        EmployeeMpoApplication application = new EmployeeMpoApplication();
        application.setReviewStatus(reviewStatus);
        application.setIsApproved(isApproved);
        application.setApproverUserId(approverUserId);
        application.setApproverNote(approverNote);
        application.setApproveDate(approveDate);
        application.setApplicationName(applicationName);
        application.setApplicationNameBn(applicationNameBn);
        application.setReasonForRejection(reasonForRejection);
        application.setRecordStatus(DRAFT);
        return application;
    }

    public EmployeeMpoApplication update() {
        EmployeeMpoApplication application = new EmployeeMpoApplication();
        application.setId(id);
        application.setReviewStatus(reviewStatus);
        application.setIsApproved(isApproved);
        application.setApproverUserId(approverUserId);
        application.setApproverNote(approverNote);
        application.setApproveDate(approveDate);
        application.setApplicationName(applicationName);
        application.setApplicationNameBn(applicationNameBn);
        application.setReasonForRejection(reasonForRejection);
        application.setRecordStatus(ACTIVE);
        return application;
    }
}
