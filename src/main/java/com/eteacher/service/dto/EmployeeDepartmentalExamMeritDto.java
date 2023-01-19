package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeDepartmentalExamMeritProfile;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeeDepartmentalExamMeritDto {

    @NotNull
    private Long employee;

    @Valid
    private List<EmployeeDepartmentalExamMeritProfile> departmentalExamMerits;
}
