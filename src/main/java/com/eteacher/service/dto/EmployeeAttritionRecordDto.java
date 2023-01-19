package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeAttritionRecordProfile;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EmployeeAttritionRecordDto {

    @NotNull
    private Long employeeId;

    @Valid
    private List<EmployeeAttritionRecordProfile> profiles;
}
