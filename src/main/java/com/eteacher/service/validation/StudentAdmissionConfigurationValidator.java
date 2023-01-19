package com.eteacher.service.validation;

import com.eteacher.service.dto.StudentAdmissionConfigurationDto;
import com.eteacher.service.entity.commonteacher.StudentAdmissionConfiguration;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.StudentAdmissionConfigurationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.StringUtils.isNull;
import static com.eteacher.service.utils.StringUtils.nonNull;

@Component
@RequiredArgsConstructor
public class StudentAdmissionConfigurationValidator implements Validator {

    private final StudentAdmissionConfigurationService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return StudentAdmissionConfigurationDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        StudentAdmissionConfigurationDto dto = (StudentAdmissionConfigurationDto) target;

        if (isNull(dto.getId())) {
            Optional<StudentAdmissionConfiguration> configurationName =
                    service.findByStudentAdmissionConfigurationName(dto.getConfigurationName());
            if (configurationName.isPresent()) {
                error.rejectValue("configurationName", null, ALREADY_EXIST);
            }
        }

        if (nonNull(dto.getId())) {
            StudentAdmissionConfiguration configuration = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);
            if (!configuration.getConfigurationName().equals(dto.getConfigurationName())) {
                Optional<StudentAdmissionConfiguration> configurationName =
                        service.findByStudentAdmissionConfigurationName(dto.getConfigurationName());
                if (configurationName.isPresent()) {
                    error.rejectValue("configurationName", null, ALREADY_EXIST);
                }
            }
        }
    }
}
