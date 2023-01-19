package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeTransferRecordEncloserProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeTransferRecordEncloserDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long recordId;

    @Valid
    private List<EmployeeTransferRecordEncloserProfile> profiles;
}
