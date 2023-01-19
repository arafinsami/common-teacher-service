package com.eteacher.service.dto;


import com.eteacher.service.profile.EmployeeTransferRecordPreferredInstituteProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EmployeeTransferRecordPreferredInstituteDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long recordId;

    private EmployeeTransferRecordPreferredInstituteProfile profile;
}
