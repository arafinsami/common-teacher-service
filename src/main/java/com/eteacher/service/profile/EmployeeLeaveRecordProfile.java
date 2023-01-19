package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.EmployeeLeaveRecord;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeLeaveRecordProfile {

    private Long id;

    @NotNull
    private Date leaveStartDate;

    @NotNull
    private Date leaveEndDate;

    private Long reviewerUserId;

    private Date reviewDate;

    private String reviewNote;

    private Boolean reviewStatus;

    @NotNull
    private Long employeeLeaveRecordId;

    @NotNull
    private Long leaveType;

    private Long reasonForRejection;

    public EmployeeLeaveRecord to() {
        EmployeeLeaveRecord record = new EmployeeLeaveRecord();
        record.setId(id);
        record.setLeaveStartDate(leaveStartDate);
        record.setLeaveEndDate(leaveEndDate);
        record.setReviewerUserId(reviewerUserId);
        record.setReviewDate(reviewDate);
        record.setReviewNote(reviewNote);
        record.setReviewStatus(reviewStatus);
        record.setEmployeeLeaveRecordId(employeeLeaveRecordId);
        record.setReasonForRejection(reasonForRejection);
        record.setLeaveType(leaveType);
        record.setRecordStatus(DRAFT);
        return record;
    }

    public EmployeeLeaveRecord update() {
        EmployeeLeaveRecord record = new EmployeeLeaveRecord();
        record.setId(id);
        record.setLeaveStartDate(leaveStartDate);
        record.setLeaveEndDate(leaveEndDate);
        record.setReviewerUserId(reviewerUserId);
        record.setReviewDate(reviewDate);
        record.setReviewNote(reviewNote);
        record.setReviewStatus(reviewStatus);
        record.setEmployeeLeaveRecordId(employeeLeaveRecordId);
        record.setReasonForRejection(reasonForRejection);
        record.setLeaveType(leaveType);
        record.setRecordStatus(ACTIVE);
        return record;
    }
}
