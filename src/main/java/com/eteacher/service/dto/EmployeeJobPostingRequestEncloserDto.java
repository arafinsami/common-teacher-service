package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeJobPostingRequestEncloserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeJobPostingRequestEncloserDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long jobPostingRequestId;

    @Valid
    private List<EmployeeJobPostingRequestEncloserProfile> profiles;
}
