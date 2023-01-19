package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
import lombok.Data;

@Data
public class PerformanceEvaluationAssessmentTopicAudit {

  private Long id;

  private String performanceEvaluationAssessmentTopicDescription;

  private String performanceEvaluationAssessmentTopicDescriptionBn;

  private Integer maxScore;

  private Integer qualifyingScore;

  public static PerformanceEvaluationAssessmentTopicAudit from(
          PerformanceEvaluationAssessmentTopic performanceEvaluationAssessmentTopic) {
    PerformanceEvaluationAssessmentTopicAudit audit = new PerformanceEvaluationAssessmentTopicAudit();
    audit.setId(performanceEvaluationAssessmentTopic.getId());
    audit.setPerformanceEvaluationAssessmentTopicDescription(performanceEvaluationAssessmentTopic.getTopicDescription());
    audit.setPerformanceEvaluationAssessmentTopicDescriptionBn(performanceEvaluationAssessmentTopic.getTopicDescriptionBn());
    audit.setMaxScore(performanceEvaluationAssessmentTopic.getMaxScore());
    audit.setQualifyingScore(performanceEvaluationAssessmentTopic.getQualifyingScore());
    return audit;
  }
}
