package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PerformanceEvaluationAssessmentTopicHelper {

    private final ActiveUserContext context;

    public void getSaveData(PerformanceEvaluationAssessmentTopic performanceEvaluationAssessmentTopic) {
        performanceEvaluationAssessmentTopic.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        performanceEvaluationAssessmentTopic.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(
            PerformanceEvaluationAssessmentTopic performanceEvaluationAssessmentTopic, RecordStatus status) {
        performanceEvaluationAssessmentTopic.setRecordVersion(
                performanceEvaluationAssessmentTopic.getRecordVersion() + 1);
        performanceEvaluationAssessmentTopic.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        performanceEvaluationAssessmentTopic.setRecordStatus(status);
    }
}
