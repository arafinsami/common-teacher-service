package com.eteacher.service.validation;

import com.eteacher.service.dto.InstExamMarkPolicyDto;
import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicy;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.InstExamMarkPolicyService;
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
public class InstExamMarkPolicyValidator implements Validator {

    private final InstExamMarkPolicyService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return InstExamMarkPolicyDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        InstExamMarkPolicyDto dto = (InstExamMarkPolicyDto) target;

        if (isNull(dto.getId())) {
            Optional<InstituteExamMarkingPolicy> institute = service.findByInstituteId(dto.getInstituteId());
            if (institute.isPresent()) {
                error.rejectValue("instituteId", null, ALREADY_EXIST);
            }
        }

        if (nonNull(dto.getId())) {
            InstituteExamMarkingPolicy institute = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);
            if (!institute.getInstituteId().equals(dto.getInstituteId())) {
                Optional<InstituteExamMarkingPolicy> institutePolicy = service.findByInstituteId(dto.getInstituteId());
                if (institutePolicy.isPresent()) {
                    error.rejectValue("instituteId", null, ALREADY_EXIST);
                }
            }
        }
    }
}
