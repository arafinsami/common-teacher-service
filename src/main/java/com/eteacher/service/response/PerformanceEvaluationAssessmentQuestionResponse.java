package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestion;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PerformanceEvaluationAssessmentQuestionResponse {

    private Long id;

    private String assessmentQuestion;

    private String assessmentQuestionBn;

    private Long performanceEvaluationId;

    private Long assessmentTopic;

    private Long recordAreaId;

    private Integer appControlType;

    private Integer unitMark;

    private String topicName;

    public static PerformanceEvaluationAssessmentQuestionResponse from(PerformanceEvaluationAssessmentQuestion question) {
        PerformanceEvaluationAssessmentQuestionResponse response = new PerformanceEvaluationAssessmentQuestionResponse();
        response.setId(question.getId());
        response.setAssessmentQuestion(question.getAssessmentQuestion());
        response.setAssessmentQuestionBn(question.getAssessmentQuestionBn());
        response.setUnitMark(question.getUnitMark());
        response.setAppControlType(question.getAppControlType());
        //response.setPerformanceEvaluationId(question.getPerformanceEvaluation().getId());
        response.setAssessmentTopic(question.getAssesmentTopic().getId());
        response.setRecordAreaId(question.getRecordId());
        return response;
    }

}
