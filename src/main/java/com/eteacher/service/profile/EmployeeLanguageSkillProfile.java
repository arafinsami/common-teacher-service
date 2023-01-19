package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.EmployeeLanguageSkill;
import com.eteacher.service.enums.ProficiencyLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeLanguageSkillProfile {

    private Long id;

    @NotNull
    private Long languageSkillSl;

    @NotNull
    private ProficiencyLevel proficiencyLevel;

    @NotNull
    private Long language;

    public EmployeeLanguageSkill to() {
        EmployeeLanguageSkill skill = new EmployeeLanguageSkill();
        skill.setId(id);
        skill.setEmployeeLanguageSkillSl(languageSkillSl);
        skill.setProficiencyLevel(proficiencyLevel);
        skill.setLanguage(language);
        skill.setRecordStatus(DRAFT);
        return skill;
    }

    public EmployeeLanguageSkill update() {
        EmployeeLanguageSkill skill = new EmployeeLanguageSkill();
        skill.setId(id);
        skill.setEmployeeLanguageSkillSl(languageSkillSl);
        skill.setProficiencyLevel(proficiencyLevel);
        skill.setLanguage(language);
        skill.setRecordStatus(ACTIVE);
        return skill;
    }
}
