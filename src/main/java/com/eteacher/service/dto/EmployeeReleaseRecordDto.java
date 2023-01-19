package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeReleaseRecordProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeReleaseRecordDto {

    private Long employeeId;

    private List<EmployeeReleaseRecordProfile> profiles;
}
