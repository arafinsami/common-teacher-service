package com.eteacher.service.validation;

import com.eteacher.service.dto.EmployeeLanguageSkillDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeLanguageSkill;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.EmployeeLanguageSkillService;
import com.eteacher.service.service.EmployeeService;
import com.eteacher.service.utils.CollectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.stream.IntStream;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.StringUtils.*;

@Component
@RequiredArgsConstructor
public class EmployeeLanguageSkillValidator implements Validator {

    @Resource
    private EmployeeLanguageSkillService service;

    @Resource
    private EmployeeService employeeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeLanguageSkillDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        EmployeeLanguageSkillDto dto = (EmployeeLanguageSkillDto) target;

        if (CollectionUtils.isNotEmpty(dto.getLanguageSkills())) {
            IntStream.range(0, dto.getLanguageSkills().size()).forEach(f -> {
                if (isNull(dto.getLanguageSkills().get(f).getId()) & dto.getLanguageSkills().get(f).getId().equals(0)) {
                    if (isNotEmpty(dto.getLanguageSkills().get(f).getLanguage())) {
                        Employee emp = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);
                        Optional<EmployeeLanguageSkill> skill = service
                                .findByLanguageSkill(dto.getLanguageSkills().get(f).getLanguage(), emp);
                        if (skill.isPresent()) {
                            error.rejectValue("languageSkills[" + f + "].language", null, ALREADY_EXIST);
                        }
                    }
                }

                if (nonNull(dto.getLanguageSkills().get(f).getId())) {
                    if (isNotEmpty(dto.getLanguageSkills().get(f).getLanguage())) {
                        EmployeeLanguageSkill skill = service.findByLanguageSkillId(dto.getLanguageSkills().get(f).getId())
                                .orElseThrow(ResourceNotFoundException::new);
                        if (!skill.getLanguage().equals(dto.getLanguageSkills().get(f).getLanguage())) {
                            Employee emp = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);
                            Optional<EmployeeLanguageSkill> l = service.findByLanguageSkill(dto.getLanguageSkills().get(f).getLanguage(), emp);
                            if (l.isPresent()) {
                                error.rejectValue("languageSkills[" + f + "].language", null, ALREADY_EXIST);
                            }
                        }
                    }
                }
            });
        }
    }
}
