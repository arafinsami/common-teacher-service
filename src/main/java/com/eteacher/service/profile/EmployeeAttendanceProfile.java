package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.EmployeeAttendance;
import com.eteacher.service.enums.AttendanceStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeAttendanceProfile {

    private Long id;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date dateOfAttendance;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date inAt;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date outAt;

    private AttendanceStatus status;

    private Boolean isLateAbsentApproved;

    private String remarks;

    private Long approverUserId;

    private String approverNote;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date approveDate;

    private Long reasonForRejection;

    public EmployeeAttendance to() {
        EmployeeAttendance attendance = new EmployeeAttendance();
        attendance.setId(id);
        attendance.setDateOfAttendance(dateOfAttendance);
        attendance.setInAt(inAt);
        attendance.setOutAt(outAt);
        attendance.setStatus(status);
        attendance.setIsLateAbsentApproved(isLateAbsentApproved);
        attendance.setRemarks(remarks);
        attendance.setApproverUserId(approverUserId);
        attendance.setApproverNote(approverNote);
        attendance.setApproveDate(approveDate);
        attendance.setReasonForRejection(reasonForRejection);
        attendance.setRecordStatus(DRAFT);
        return attendance;
    }

    public EmployeeAttendance update() {
        EmployeeAttendance attendance = new EmployeeAttendance();
        attendance.setId(id);
        attendance.setDateOfAttendance(dateOfAttendance);
        attendance.setInAt(inAt);
        attendance.setOutAt(outAt);
        attendance.setStatus(status);
        attendance.setIsLateAbsentApproved(isLateAbsentApproved);
        attendance.setRemarks(remarks);
        attendance.setApproverUserId(approverUserId);
        attendance.setApproverNote(approverNote);
        attendance.setApproveDate(approveDate);
        attendance.setReasonForRejection(reasonForRejection);
        attendance.setRecordStatus(ACTIVE);
        return attendance;
    }
}
