package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluation;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestion;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PerformanceEvaluationAssessmentQuestionDto {
    private Long id;

    @NotBlank
    private String performanceEvaluationAssessmentQuestion;

    private String performanceEvaluationAssessmentQuestionBn;

    private Integer appControlType;

    private Integer unitMark;

    @NotNull
    private Long performanceEvaluation;

    @NotNull
    private Long performanceEvaluationAssessmentTopicId;

    public PerformanceEvaluationAssessmentQuestion to() {
        PerformanceEvaluationAssessmentQuestion question = new PerformanceEvaluationAssessmentQuestion();
        question.setAssessmentQuestion(performanceEvaluationAssessmentQuestion);
        question.setAssessmentQuestionBn(performanceEvaluationAssessmentQuestionBn);
        question.setAppControlType(appControlType);
        question.setUnitMark(unitMark);

        return question;
    }

    public void update(PerformanceEvaluationAssessmentQuestion question) {
        question.setAssessmentQuestion(performanceEvaluationAssessmentQuestion);
        question.setAssessmentQuestionBn(performanceEvaluationAssessmentQuestionBn);
        question.setAppControlType(appControlType);
        question.setUnitMark(unitMark);
    }

    public void getData(
            PerformanceEvaluationAssessmentQuestion question,
            PerformanceEvaluationAssessmentTopic topic,
            PerformanceEvaluation evaluation) {
        question.setAssesmentTopic(topic);
        //question.setPerformanceEvaluation(evaluation);
    }
}
