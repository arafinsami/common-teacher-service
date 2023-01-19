package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestionAnswerOption;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformanceEvaluationAssessmentQuestionAnswerOptionRepository extends JpaRepository<PerformanceEvaluationAssessmentQuestionAnswerOption, Long> {

    Optional<PerformanceEvaluationAssessmentQuestionAnswerOption> findByAssessmentQuestionAnswerOption(String assessmentQuestionAnswerOption);

    Optional<PerformanceEvaluationAssessmentQuestionAnswerOption> findByAssessmentQuestionAnswerOptionBn(String assessmentQuestionAnswerOptionBn);

//    Optional<PerformanceEvaluationAssessmentQuestionAnswerOption> findTopByOrderByIdDesc();
    Optional<PerformanceEvaluationAssessmentQuestionAnswerOption>  findByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);

}
