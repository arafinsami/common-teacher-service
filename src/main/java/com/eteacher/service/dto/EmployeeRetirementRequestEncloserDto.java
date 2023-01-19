package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeRetirementRequestEncloserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeRetirementRequestEncloserDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long requestId;

    private List<EmployeeRetirementRequestEncloserProfile> profiles;
}
