package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestion;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestionAnswerOption;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.repository.PerformanceEvaluationAssessmentQuestionRepository;
import com.eteacher.service.dto.PerformanceEvaluationAssessmentQuestionAnswerOptionDto;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PerformanceEvaluationAssessmentQuestionOptionHelper {
    private final ActiveUserContext context;
    private final PerformanceEvaluationAssessmentQuestionRepository repository;



    public void getSaveData(PerformanceEvaluationAssessmentQuestionAnswerOption answerOption) {
        answerOption.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        answerOption.setRecordStatus(RecordStatus.DRAFT);
//        answerOption.setRecordId(StringUtils.getRecordId(previousId));
    }

    public void getUpdateData(PerformanceEvaluationAssessmentQuestionAnswerOption answerOption) {
        answerOption.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        answerOption.setRecordStatus(RecordStatus.ACTIVE);
        answerOption.setRecordVersion(answerOption.getRecordVersion()+1);
    }

    public PerformanceEvaluationAssessmentQuestionAnswerOption getPerformanceQuestion( PerformanceEvaluationAssessmentQuestionAnswerOption answerOption, PerformanceEvaluationAssessmentQuestionAnswerOptionDto request) {
        PerformanceEvaluationAssessmentQuestion question = repository.findById(request.getPerformanceEvaluationAssessmentQuestion())
                .orElseThrow(ResourceNotFoundException::new);
        //answerOption.setAssessmentQuestion(question);
        return answerOption;
    }

}
