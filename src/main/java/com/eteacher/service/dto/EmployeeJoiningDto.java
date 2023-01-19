package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeJoiningProfile;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
public class EmployeeJoiningDto {

    @NotNull
    private Long employee;

    @NotNull
    private List<EmployeeJoiningProfile> joining;
}
