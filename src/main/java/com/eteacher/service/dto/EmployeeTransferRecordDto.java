package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeTransferRecordProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@NoArgsConstructor
public class EmployeeTransferRecordDto {

    @NotNull
    private Long employeeId;

    private List<EmployeeTransferRecordProfile> profiles;
}
