package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.EmployeeJoining;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;

@Data
@NoArgsConstructor
public class EmployeeJoiningProfile {

    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date effectiveDate;

    private Integer postingStatus;

    private Long approverUserId;

    private String approverNote;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date approveDate;

    @NotNull
    private Long fromDesignation;

    @NotNull
    private Long toDesignation;

    @NotNull
    private Long fromInstitute;

    @NotNull
    private Long toInstitute;

    private Long reasonForRejection;

    public EmployeeJoining to() {
        EmployeeJoining joining = new EmployeeJoining();
        joining.setId(id);
        joining.setEffectiveDate(effectiveDate);
        joining.setPostingStatus(postingStatus);
        joining.setApproverUserId(approverUserId);
        joining.setApproverNote(approverNote);
        joining.setApproveDate(approveDate);
        joining.setFromDesignation(fromDesignation);
        joining.setToDesignation(toDesignation);
        joining.setFromInstitute(fromInstitute);
        joining.setToInstitute(toInstitute);
        joining.setReasonForRejection(reasonForRejection);
        return joining;
    }

    public EmployeeJoining update() {
        EmployeeJoining joining = new EmployeeJoining();
        joining.setId(id);
        joining.setEffectiveDate(effectiveDate);
        joining.setPostingStatus(postingStatus);
        joining.setApproverUserId(approverUserId);
        joining.setApproverNote(approverNote);
        joining.setApproveDate(approveDate);
        joining.setFromDesignation(fromDesignation);
        joining.setToDesignation(toDesignation);
        joining.setFromInstitute(fromInstitute);
        joining.setToInstitute(toInstitute);
        joining.setReasonForRejection(reasonForRejection);
        joining.setRecordStatus(ACTIVE);
        return joining;
    }
}
