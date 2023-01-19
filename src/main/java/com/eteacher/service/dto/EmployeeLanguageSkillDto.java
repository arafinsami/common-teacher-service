package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeLanguageSkillProfile;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EmployeeLanguageSkillDto {

    @NotNull
    private Long employee;

    @Valid
    private List<EmployeeLanguageSkillProfile> languageSkills;
}
