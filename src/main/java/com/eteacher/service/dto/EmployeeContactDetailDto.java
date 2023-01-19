package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeContactDetailProfile;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EmployeeContactDetailDto {
    @NotNull
    private Long employee;

    @NotNull
    private List<EmployeeContactDetailProfile> contactDetails;
}
