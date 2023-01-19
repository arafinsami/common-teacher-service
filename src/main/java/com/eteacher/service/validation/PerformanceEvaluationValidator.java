package com.eteacher.service.validation;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluation;
import com.eteacher.service.dto.PerformanceEvaluationDto;
import com.eteacher.service.service.PerformanceEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.StringUtils.isNotEmpty;


@Component
@RequiredArgsConstructor
public class PerformanceEvaluationValidator implements Validator {

    private final PerformanceEvaluationService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return PerformanceEvaluationDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        PerformanceEvaluationDto request = (PerformanceEvaluationDto) target;

        if (isNotEmpty(request.getEvaluationDescription())) {
            Optional<PerformanceEvaluation> perform = service.findByEvaluationDescription(request.getEvaluationDescription());
            if (perform.isPresent()) {
                error.rejectValue("evaluationDescription", null, ALREADY_EXIST);
            }
        }

        if (isNotEmpty(request.getEvaluationDescriptionBn())) {
            Optional<PerformanceEvaluation> perform = service.findByEvaluationDescriptionBn(request.getEvaluationDescriptionBn());
            if (perform.isPresent()) {
                error.rejectValue("evaluationDescriptionBn", null, ALREADY_EXIST);
            }
        }
    }
}
