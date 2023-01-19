package com.eteacher.service.validation;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestion;
import com.eteacher.service.dto.PerformanceEvaluationAssessmentQuestionDto;
import com.eteacher.service.service.PerformanceEvaluationAssessmentQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.StringUtils.isNotEmpty;

@Component
@RequiredArgsConstructor
public class PerformanceEvaluationAssessmentQuestionValidator implements Validator {

    private final PerformanceEvaluationAssessmentQuestionService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return PerformanceEvaluationAssessmentQuestionDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        PerformanceEvaluationAssessmentQuestionDto request = (PerformanceEvaluationAssessmentQuestionDto) target;

        if (isNotEmpty(request.getPerformanceEvaluation())) {
            Optional<PerformanceEvaluationAssessmentQuestion> assessmentQuestion = service.findByAssessmentQuestion(request.getPerformanceEvaluationAssessmentQuestion());
            if (assessmentQuestion.isPresent()) {
                error.rejectValue("assessmentQuestion", null, ALREADY_EXIST);
            }
        }

        if (isNotEmpty(request.getPerformanceEvaluationAssessmentQuestionBn())) {
            Optional<PerformanceEvaluationAssessmentQuestion> assessmentQuestion = service.findByAssessmentQuestion(request.getPerformanceEvaluationAssessmentQuestionBn());
            if (assessmentQuestion.isPresent()) {
                error.rejectValue("assessmentQuestionBn", null, ALREADY_EXIST);
            }
        }

    }
}
