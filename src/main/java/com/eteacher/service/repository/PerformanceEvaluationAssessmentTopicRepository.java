package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformanceEvaluationAssessmentTopicRepository extends JpaRepository<PerformanceEvaluationAssessmentTopic, Long> {

  Optional<PerformanceEvaluationAssessmentTopic> findByTopicDescription(String topicDescription);

  Optional<PerformanceEvaluationAssessmentTopic> findByTopicDescriptionBn(String topicDescriptionBn);

  Optional<PerformanceEvaluationAssessmentTopic> findTopByOrderByIdDesc();

  Optional<PerformanceEvaluationAssessmentTopic> findByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
