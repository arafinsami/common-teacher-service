package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.EmployeeLanguageSkill;
import com.eteacher.service.profile.EmployeeLanguageSkillProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeLanguageSkillHelper {

    @Resource
    private ActiveUserContext context;

    public Function<EmployeeLanguageSkillProfile, EmployeeLanguageSkill> saveProfile = converter -> {
        EmployeeLanguageSkill skill = converter.to();
        skill.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return skill;
    };

    public Function<EmployeeLanguageSkillProfile, EmployeeLanguageSkill> updateProfile = converter -> {
        EmployeeLanguageSkill skill = converter.update();
        skill.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return skill;
    };

    public List<EmployeeLanguageSkill> save(List<EmployeeLanguageSkillProfile> profiles) {
        List<EmployeeLanguageSkill> lists = profiles.stream()
                .map(saveProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeLanguageSkill> update(List<EmployeeLanguageSkillProfile> profiles) {
        List<EmployeeLanguageSkill> lists = profiles.stream()
                .map(updateProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeLanguageSkill> deleteOne(EmployeeLanguageSkill languageSkill) {
        languageSkill.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        languageSkill.setRecordStatus(DELETED);
        return Arrays.asList(languageSkill);
    }

    public EmployeeLanguageSkill findBySkillId(Long skillId, List<EmployeeLanguageSkill> lists) {
        for (EmployeeLanguageSkill skill : lists) {
            if (skill.getId().equals(skillId)) {
                return skill;
            }
        }
        return null;
    }
}
