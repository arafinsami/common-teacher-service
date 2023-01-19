package com.eteacher.service.validation;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestionAnswerOption;
import com.eteacher.service.dto.PerformanceEvaluationAssessmentQuestionAnswerOptionDto;
import com.eteacher.service.service.PerformanceEvaluationAssessmentQuestionOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.StringUtils.isNotEmpty;


@Component
@RequiredArgsConstructor
public class PerformanceEvaluationAssessmentQuestionAnswerOptionValidator implements Validator {
    private final PerformanceEvaluationAssessmentQuestionOptionService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return PerformanceEvaluationAssessmentQuestionAnswerOptionDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        PerformanceEvaluationAssessmentQuestionAnswerOptionDto request = (PerformanceEvaluationAssessmentQuestionAnswerOptionDto) target;
        if (isNotEmpty(request.getPerformanceEvaluationAssessmentQuestionAnswerOption())) {
            Optional<PerformanceEvaluationAssessmentQuestionAnswerOption> bank = service.findByPerformanceEvaluationAssessmentQuestionAnswerOption(
                    request.getPerformanceEvaluationAssessmentQuestionAnswerOption());
            if (bank.isPresent()) {
                error.rejectValue("assessmentQuestionAnswerOption", null, ALREADY_EXIST);
            }
        }

        if (isNotEmpty(request.getPerformanceEvaluationAssessmentQuestionAnswerOptionBn())) {
            Optional<PerformanceEvaluationAssessmentQuestionAnswerOption> bank = service.findByPerformanceEvaluationAssessmentQuestionAnswerOptionBn(
                    request.getPerformanceEvaluationAssessmentQuestionAnswerOptionBn());
            if (bank.isPresent()) {
                error.rejectValue("assessmentQuestionAnswerOptionBn", null, ALREADY_EXIST);
            }
        }

    }
}
