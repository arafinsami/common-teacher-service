package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeMpoPaymentRequestProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeMpoPaymentReqDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private List<EmployeeMpoPaymentRequestProfile> profiles;
}
