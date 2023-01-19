package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeAttritionRecordEncloserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeAttritionRecordEncloserDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long recordId;

    @Valid
    private List<EmployeeAttritionRecordEncloserProfile> profiles;
}
