package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeeLeaveRecord;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.eteacher.service.utils.StringUtils.nonNull;

@Data
@NoArgsConstructor
public class EmployeeLeaveRecordResponse {

    private Long id;

    private Date leaveStartDate;

    private Date leaveEndDate;

    private Long reviewerUserId;

    private Date reviewDate;

    private String reviewNote;

    private Boolean reviewStatus;

    private Long employeeLeaveRecordId;

    private Long leaveType;

    private Long reasonForRejection;

    private Long employee;

    public static EmployeeLeaveRecordResponse from(EmployeeLeaveRecord record) {
        EmployeeLeaveRecordResponse response = new EmployeeLeaveRecordResponse();
        response.setId(record.getId());
        response.setLeaveStartDate(record.getLeaveStartDate());
        response.setLeaveEndDate(record.getLeaveEndDate());
        response.setReviewerUserId(record.getReviewerUserId());
        response.setReviewDate(record.getReviewDate());
        response.setReviewNote(record.getReviewNote());
        response.setReviewStatus(record.getReviewStatus());
        response.setEmployeeLeaveRecordId(record.getEmployeeLeaveRecordId());
        response.setLeaveType(record.getLeaveType());
        response.setReasonForRejection(record.getReasonForRejection());
        response.setEmployee(nonNull(
                record.getEmployee()) ?
                record.getEmployee().getId()
                : 0l
        );
        return response;
    }
}
