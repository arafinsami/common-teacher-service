package com.eteacher.service.validation;

import com.eteacher.service.dto.RecruitmentTypeDto;
import com.eteacher.service.entity.commonteacher.RecruitmentType;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.RecruitmentTypeService;
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
public class RecruitmentTypeValidator implements Validator {

    private final RecruitmentTypeService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return RecruitmentTypeDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        RecruitmentTypeDto request = (RecruitmentTypeDto) target;

        if (isNull(request.getId())) {
            Optional<RecruitmentType> recruitmentTypeName = service.findByRecruitmentTypeName(
                    request.getRecruitmentTypeName());
            if (recruitmentTypeName.isPresent()) {
                error.rejectValue("recruitmentTypeName", null, ALREADY_EXIST);
            }
        }

        if (nonNull(request.getId())) {
            RecruitmentType recruitmentType = service.findById(request.getId())
                    .orElseThrow(ResourceNotFoundException::new);
            if (!recruitmentType.getRecruitmentTypeName().equals(request.getRecruitmentTypeName())) {
                Optional<RecruitmentType> recruitmentTypeName = service.findByRecruitmentTypeName(
                        request.getRecruitmentTypeName());
                if (recruitmentTypeName.isPresent()) {
                    error.rejectValue("recruitmentTypeName", null, ALREADY_EXIST);
                }
            }
        }
    }
}
