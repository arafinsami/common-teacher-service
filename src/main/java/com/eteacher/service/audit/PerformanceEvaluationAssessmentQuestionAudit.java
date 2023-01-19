package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestion;
import lombok.Data;

@Data
public class PerformanceEvaluationAssessmentQuestionAudit {

  private Long id;

  private String performanceEvaluationAssessmentQuestion;

  private String performanceEvaluationAssessmentQuestionBn;

  private Integer appControlType;

  private Integer unitMark;


  public static PerformanceEvaluationAssessmentQuestionAudit from(PerformanceEvaluationAssessmentQuestion performanceEvaluationAssessmentQuestion) {
    PerformanceEvaluationAssessmentQuestionAudit audit = new PerformanceEvaluationAssessmentQuestionAudit();
    audit.setId(performanceEvaluationAssessmentQuestion.getId());
    audit.setPerformanceEvaluationAssessmentQuestion(performanceEvaluationAssessmentQuestion.getAssessmentQuestion());
    audit.setPerformanceEvaluationAssessmentQuestionBn(performanceEvaluationAssessmentQuestion.getAssessmentQuestionBn());
    audit.setAppControlType(performanceEvaluationAssessmentQuestion.getAppControlType());
    audit.setUnitMark(performanceEvaluationAssessmentQuestion.getUnitMark());
    return audit;
  }
}
