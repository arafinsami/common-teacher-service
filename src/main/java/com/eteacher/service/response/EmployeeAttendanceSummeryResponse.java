package com.eteacher.service.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EmployeeAttendanceSummeryResponse {

    private Long employeeId;

    private String employeeName;

    private Integer totalDay = 0;

    private Integer totalPresent = 0;

    private Integer totalAbsent = 0;

    private Integer totalLate = 0;

    private Integer totalLateAbsentApproved = 0;
}
