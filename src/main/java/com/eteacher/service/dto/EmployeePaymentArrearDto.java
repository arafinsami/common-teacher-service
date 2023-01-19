package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeePaymentArrearprofile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeePaymentArrearDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private List<EmployeePaymentArrearprofile> profiles;
}
