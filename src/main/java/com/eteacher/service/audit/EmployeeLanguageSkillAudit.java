package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeeLanguageSkill;
import com.eteacher.service.enums.ProficiencyLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeLanguageSkillAudit {

  private Long id;

  private Long languageSkillSl;

  private ProficiencyLevel proficiencyLevel;

  private Long language;

  public static EmployeeLanguageSkillAudit audit(EmployeeLanguageSkill skill) {
    EmployeeLanguageSkillAudit audit = new EmployeeLanguageSkillAudit();
    audit.setId(skill.getId());
    audit.setLanguageSkillSl(skill.getEmployeeLanguageSkillSl());
    audit.setProficiencyLevel(skill.getProficiencyLevel());
    audit.setLanguage(skill.getLanguage());
    return audit;
  }
}
