package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeLanguageSkillAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeLanguageSkill;
import com.eteacher.service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class EmployeeLanguageSkillService extends BaseService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    public Optional<EmployeeLanguageSkill> findByLanguageSkill(Long languageId, Employee e) {
        Optional<EmployeeLanguageSkill> skill = repository.findByLanguageSkill(languageId, e);
        return skill;
    }

    public Optional<EmployeeLanguageSkill> findByLanguageSkillId(Long languageSkillId) {
        Optional<EmployeeLanguageSkill> skill = repository.findByLanguageSkillId(languageSkillId);
        return skill;
    }

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeLanguageSkillAudit> audits = e.getLanguageSkills()
                .stream()
                .map(EmployeeLanguageSkillAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_LANGUAGE_SKILL_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeLanguageSkillAudit> audits = e.getLanguageSkills()
                .stream()
                .map(EmployeeLanguageSkillAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_LANGUAGE_SKILL_UPDATE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeLanguageSkillAudit> audits = e.getLanguageSkills()
                .stream()
                .map(EmployeeLanguageSkillAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_LANGUAGE_SKILL_DELETE,
                objectToJson(audits)
        );
    }
}
