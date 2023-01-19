package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeeLeaveRecord;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeLeaveRecordAudit {

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

  public static EmployeeLeaveRecordAudit from(EmployeeLeaveRecord leaveRecord) {
    EmployeeLeaveRecordAudit audit = new EmployeeLeaveRecordAudit();
    audit.setId(leaveRecord.getId());
    audit.setLeaveStartDate(leaveRecord.getLeaveStartDate());
    audit.setLeaveEndDate(leaveRecord.getLeaveEndDate());
    audit.setReviewerUserId(leaveRecord.getReviewerUserId());
    audit.setReviewDate(leaveRecord.getReviewDate());
    audit.setReviewNote(leaveRecord.getReviewNote());
    audit.setReviewStatus(leaveRecord.getReviewStatus());
    audit.setEmployeeLeaveRecordId(leaveRecord.getEmployeeLeaveRecordId());
    audit.setReasonForRejection(leaveRecord.getReasonForRejection());
    audit.setLeaveType(leaveRecord.getLeaveType());
    return audit;
  }
}
