package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluation;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestion;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.repository.PerformanceEvaluationAssessmentTopicRepository;
import com.eteacher.service.repository.PerformanceEvaluationRepository;
import com.eteacher.service.dto.PerformanceEvaluationAssessmentQuestionDto;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PerformanceEvaluationAssessmentQuestionHelper {

    private final ActiveUserContext context;

    private final PerformanceEvaluationAssessmentTopicRepository repository;

    private final PerformanceEvaluationRepository performanceEvaluationRepository;


    public void getSaveData(PerformanceEvaluationAssessmentQuestion question) {
        question.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        question.setRecordStatus(RecordStatus.DRAFT);
    }


    public void getUpdateData(PerformanceEvaluationAssessmentQuestion question, RecordStatus status) {
        question.setRecordVersion(question.getRecordVersion() + 1);
        question.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        question.setRecordStatus(status);
    }

    public PerformanceEvaluationAssessmentQuestion getParentData(PerformanceEvaluationAssessmentQuestion question, PerformanceEvaluationAssessmentQuestionDto request) {
        PerformanceEvaluationAssessmentTopic topic = repository.findById(request.getPerformanceEvaluationAssessmentTopicId())
                .orElseThrow(ResourceNotFoundException::new);
        PerformanceEvaluation evaluation = performanceEvaluationRepository.findById(request.getPerformanceEvaluation())
                .orElseThrow(ResourceNotFoundException::new);
        question.setAssesmentTopic(topic);
        //question.setPerformanceEvaluation(evaluation);
        request.getData(question, topic, evaluation);
        return question;
    }
}
