package com.eteacher.service.validation;

import com.eteacher.service.dto.EmployeeJoiningDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeJoining;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.EmployeeJoiningService;
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
import static com.eteacher.service.utils.StringUtils.isNotEmpty;
import static com.eteacher.service.utils.StringUtils.isNull;

@Component
@RequiredArgsConstructor
public class EmployeeJoiningValidator implements Validator {

    @Resource
    private EmployeeJoiningService service;

    @Resource
    private EmployeeService employeeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeJoiningDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        EmployeeJoiningDto request = (EmployeeJoiningDto) target;

        if (CollectionUtils.isNotEmpty(request.getJoining())) {
            IntStream.range(0, request.getJoining().size()).forEach(f -> {
                if (isNull(request.getJoining().get(f).getId())) {
                    if (isNotEmpty(request.getJoining().get(f).getFromDesignation())) {
                        Employee emp = employeeService.findByIdAndRecordStatusNot(request.getEmployee())
                                .orElseThrow(ResourceNotFoundException::new);
                        Optional<EmployeeJoining> joining = service.findByJoiningFromDesignation(request.getJoining().get(f).getFromDesignation(), emp);
                        if (joining.isPresent()) {
                            error.rejectValue("joining[" + f + "].fromDesignation", null, ALREADY_EXIST);
                        }
                    }

                    if (isNotEmpty(request.getJoining().get(f).getToDesignation())) {
                        Employee emp = employeeService.findByIdAndRecordStatusNot(request.getEmployee())
                                .orElseThrow(ResourceNotFoundException::new);
                        Optional<EmployeeJoining> joining = service
                                .findByJoiningToDesignation(request.getJoining().get(f).getToDesignation(), emp);
                        if (joining.isPresent()) {
                            error.rejectValue("joining[" + f + "].toDesignation", null, ALREADY_EXIST);
                        }
                    }

                    if (isNotEmpty(request.getJoining().get(f).getFromInstitute())) {
                        Employee emp = employeeService.findByIdAndRecordStatusNot(request.getEmployee())
                                .orElseThrow(ResourceNotFoundException::new);
                        Optional<EmployeeJoining> joining = service
                                .findByJoiningFromInstitute(request.getJoining().get(f).getFromInstitute(), emp);
                        if (joining.isPresent()) {
                            error.rejectValue("joining[" + f + "].fromInstitute", null, ALREADY_EXIST);
                        }
                    }

                    if (isNotEmpty(request.getJoining().get(f).getToInstitute())) {
                        Employee emp = employeeService.findByIdAndRecordStatusNot(request.getEmployee())
                                .orElseThrow(ResourceNotFoundException::new);
                        Optional<EmployeeJoining> joining = service
                                .findByJoiningToInstitute(request.getJoining().get(f).getToInstitute(), emp);
                        if (joining.isPresent()) {
                            error.rejectValue("joining[" + f + "].toInstitute", null, ALREADY_EXIST);
                        }
                    }
                }

//        if (nonNull(request.getLanguageSkills().get(f).getId())) {
//          if (isNotEmpty(request.getLanguageSkills().get(f).getLanguage())) {
//            EmployeeLanguageSkill skill = service.findByLanguageSkillId(request.getLanguageSkills().get(f).getId())
//                    .orElseThrow(ResourceNotFoundException::new);
//            if (!skill.getLanguage().equals(request.getLanguageSkills().get(f).getLanguage())) {
//              Optional<EmployeeLanguageSkill> l = service.findByLanguageSkill(request.getLanguageSkills().get(f).getLanguage());
//              if (l.isPresent()) {
//                error.rejectValue("languageSkills[" + f + "].language", null, ALREADY_EXIST);
//              }
//            }
//          }
//        }
            });
        }
    }
}