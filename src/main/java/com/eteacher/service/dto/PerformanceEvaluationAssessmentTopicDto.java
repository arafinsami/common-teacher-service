package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PerformanceEvaluationAssessmentTopicDto {

    private Long id;

    @NotBlank
    private String topicDescription;

    @NotBlank
    private String topicDescriptionBn;

    @NotNull
    private Integer maxScore;

    @NotNull
    private Integer qualifyingScore;

    public PerformanceEvaluationAssessmentTopic to() {
        PerformanceEvaluationAssessmentTopic topic = new PerformanceEvaluationAssessmentTopic();
        topic.setTopicDescription(topicDescription);
        topic.setTopicDescriptionBn(topicDescriptionBn);
        topic.setMaxScore(maxScore);
        topic.setQualifyingScore(qualifyingScore);
        return topic;
    }

    public void update(PerformanceEvaluationAssessmentTopic topic) {
        topic.setTopicDescription(topicDescription);
        topic.setTopicDescriptionBn(topicDescriptionBn);
        topic.setMaxScore(maxScore);
        topic.setQualifyingScore(qualifyingScore);
    }
}
