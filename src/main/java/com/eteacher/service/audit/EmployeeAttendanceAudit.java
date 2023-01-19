package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeeAttendance;
import com.eteacher.service.enums.AttendanceStatus;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeAttendanceAudit {

  private Long id;

  private Date dateOfAttendance;

  private Date inAt;

  private Date outAt;

  private AttendanceStatus status;

  private String remarks;

  private Long approverUserId;

  private String approverNote;

  private Date approveDate;

  private Long reasonForRejection;

  public static EmployeeAttendanceAudit from(EmployeeAttendance employeeAttendance) {
    EmployeeAttendanceAudit audit = new EmployeeAttendanceAudit();
        audit.setId(employeeAttendance.getId());
        audit.setDateOfAttendance(employeeAttendance.getDateOfAttendance());
        audit.setInAt(employeeAttendance.getInAt());
        audit.setOutAt(employeeAttendance.getOutAt());
        audit.setStatus(employeeAttendance.getStatus());
        audit.setRemarks(employeeAttendance.getRemarks());
        audit.setApproverUserId(employeeAttendance.getApproverUserId());
        audit.setApproverNote(employeeAttendance.getApproverNote());
        audit.setApproveDate(employeeAttendance.getApproveDate());
        audit.setReasonForRejection(employeeAttendance.getReasonForRejection());
        return audit;
  }
}
