package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestion;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformanceEvaluationAssessmentQuestionRepository extends JpaRepository<PerformanceEvaluationAssessmentQuestion, Long> {

    Optional<PerformanceEvaluationAssessmentQuestion> findByAssessmentQuestion(String assessmentQuestion);

    Optional<PerformanceEvaluationAssessmentQuestion> findByAssessmentQuestionBn(String assessmentQuestionBn);

    Optional<PerformanceEvaluationAssessmentQuestion> findTopByOrderByIdDesc();
    Optional<PerformanceEvaluationAssessmentQuestion> findByIdAndRecordStatusNot(Long id, RecordStatus status);


}

