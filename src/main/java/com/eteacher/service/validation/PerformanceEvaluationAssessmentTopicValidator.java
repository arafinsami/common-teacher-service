package com.eteacher.service.validation;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
import com.eteacher.service.dto.PerformanceEvaluationAssessmentTopicDto;
import com.eteacher.service.service.PerformanceEvaluationAssessmentTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.StringUtils.isNotEmpty;

@Component
@RequiredArgsConstructor
public class PerformanceEvaluationAssessmentTopicValidator implements Validator {

    private final PerformanceEvaluationAssessmentTopicService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return PerformanceEvaluationAssessmentTopicDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        PerformanceEvaluationAssessmentTopicDto request = (PerformanceEvaluationAssessmentTopicDto) target;

        if (isNotEmpty(request.getTopicDescription())) {
            Optional<PerformanceEvaluationAssessmentTopic> topic = service.findByTopicDescription(request.getTopicDescription());
            if (topic.isPresent()) {
                error.rejectValue("topicDescription", null, ALREADY_EXIST);
            }
        }

        if (isNotEmpty(request.getTopicDescriptionBn())) {
            Optional<PerformanceEvaluationAssessmentTopic> topic = service.findByTopicDescriptionBn(request.getTopicDescriptionBn());
            if (topic.isPresent()) {
                error.rejectValue("topicDescriptionBn", null, ALREADY_EXIST);
            }
        }

    }
}
