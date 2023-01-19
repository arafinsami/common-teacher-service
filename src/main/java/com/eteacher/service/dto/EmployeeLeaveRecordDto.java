package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeLeaveRecordProfile;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EmployeeLeaveRecordDto {

    @NotNull
    private Long employee;

    @NotNull
    private List<EmployeeLeaveRecordProfile> leaveRecords;
}
