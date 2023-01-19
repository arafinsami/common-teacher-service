package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestionAnswerOption;
import lombok.Data;

@Data
public class PerformanceEvaluationAssessmentQuestionAnswerOptionAudit {

    private Long id;

    private String performanceEvaluationAssessmentQuestionAnswerOption;

    private String performanceEvaluationAssessmentQuestionAnswerOptionBn;

    private Long performanceEvaluationAssessmentQuestion;

    public static PerformanceEvaluationAssessmentQuestionAnswerOptionAudit from(PerformanceEvaluationAssessmentQuestionAnswerOption answerOption) {
        PerformanceEvaluationAssessmentQuestionAnswerOptionAudit audit = new PerformanceEvaluationAssessmentQuestionAnswerOptionAudit();
        audit.setId(answerOption.getId());
        audit.setPerformanceEvaluationAssessmentQuestionAnswerOption(answerOption.getAssessmentQuestionAnswerOption());
        audit.setPerformanceEvaluationAssessmentQuestionAnswerOptionBn(answerOption.getAssessmentQuestionAnswerOptionBn());

        /*audit.setPerformanceEvaluationAssessmentQuestion(nonNull(
                answerOption.getAssessmentQuestion()) ?
                answerOption.getAssessmentQuestion().getId()
                : 0L
        );*/
        return audit;
    }
}
