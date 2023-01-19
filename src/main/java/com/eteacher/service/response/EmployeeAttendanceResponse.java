package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeeAttendance;
import com.eteacher.service.enums.AttendanceStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeAttendanceResponse {

    private Long id;

    private Date dateOfAttendance;

    private Date inAt;

    private Date outAt;

    private AttendanceStatus status;

    private Boolean isLateAbsentApproved;

    private String remarks;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long employeeId;

    private String employeeName;

    private Long reasonForRejection;

    public static EmployeeAttendanceResponse response(EmployeeAttendance attendance) {
        EmployeeAttendanceResponse response = new EmployeeAttendanceResponse();
        response.setId(attendance.getId());
        response.setDateOfAttendance(attendance.getDateOfAttendance());
        response.setInAt(attendance.getInAt());
        response.setOutAt(attendance.getOutAt());
        response.setStatus(attendance.getStatus());
        response.setIsLateAbsentApproved(attendance.getIsLateAbsentApproved());
        response.setRemarks(attendance.getRemarks());
        response.setApproverUserId(attendance.getApproverUserId());
        response.setApproverNote(attendance.getApproverNote());
        response.setApproveDate(attendance.getApproveDate());
        response.setEmployeeId(attendance.getEmployee().getId());
        response.setEmployeeName(attendance.getEmployee().getEmployeeName());
        response.setReasonForRejection(attendance.getReasonForRejection());
        return response;
    }
}
