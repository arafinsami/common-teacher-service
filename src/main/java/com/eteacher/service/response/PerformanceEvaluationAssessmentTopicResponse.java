package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class PerformanceEvaluationAssessmentTopicResponse {

  private Long id;

  private String performanceEvaluationAssessmentTopicDescription;

  private String performanceEvaluationAssessmentTopicDescriptionBn;

  private Integer maxScore;

  private Integer qualifyingScore;

  public static PerformanceEvaluationAssessmentTopicResponse from(PerformanceEvaluationAssessmentTopic topic) {
    PerformanceEvaluationAssessmentTopicResponse response = new PerformanceEvaluationAssessmentTopicResponse();
    response.setId(topic.getId());
    response.setPerformanceEvaluationAssessmentTopicDescription(topic.getTopicDescription());
    response.setPerformanceEvaluationAssessmentTopicDescriptionBn(topic.getTopicDescriptionBn());
    response.setMaxScore(topic.getMaxScore());
    response.setQualifyingScore(topic.getQualifyingScore());
    return response;
  }

  public static Map<String, Object> searchPerformanceEvaluationAssessmentTopic(
          String performanceEvaluationAssessmentTopicDescription,
          String performanceEvaluationAssessmentTopicDescriptionBn,
          Integer maxScore,
          Integer qualifyingScore) {
    Map<String, Object> map = new HashMap<>();
    map.put("performanceEvaluationAssessmentTopicDescription", performanceEvaluationAssessmentTopicDescription);
    map.put("performanceEvaluationAssessmentTopicDescriptionBn", performanceEvaluationAssessmentTopicDescriptionBn);
    map.put("maxScore", maxScore);
    map.put("qualifyingScore", qualifyingScore);
    return map;
  }
}
