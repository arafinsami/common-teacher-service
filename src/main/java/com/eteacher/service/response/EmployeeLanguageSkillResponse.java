package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeeEncloser;
import com.eteacher.service.entity.commonteacher.EmployeeLanguageSkill;
import com.eteacher.service.enums.ProficiencyLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.eteacher.service.utils.StringUtils.nonNull;

@Data
@NoArgsConstructor
public class EmployeeLanguageSkillResponse {

    private Long id;

    private Long languageSkillSl;

    private ProficiencyLevel proficiencyLevel;

    private EmployeeEncloser employeeEncloser;

    private Long language;

    private String employee;

    public static EmployeeLanguageSkillResponse response(EmployeeLanguageSkill skill) {
        EmployeeLanguageSkillResponse response = new EmployeeLanguageSkillResponse();
        response.setId(skill.getId());
        response.setLanguageSkillSl(skill.getEmployeeLanguageSkillSl());
        response.setProficiencyLevel(skill.getProficiencyLevel());
        response.setLanguage(skill.getLanguage());
        response.setEmployee(skill.getEmployee().getEmployeeName());
        return response;
    }
}
