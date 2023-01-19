package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformanceEvaluationRepository extends JpaRepository<PerformanceEvaluation, Long> {

    Optional<PerformanceEvaluation> findByEvaluationDescription(String performanceEvaluationDescription);

    Optional<PerformanceEvaluation> findByEvaluationDescriptionBn(String performanceEvaluationDescriptionBn);

    Optional<PerformanceEvaluation> findTopByOrderByIdDesc();

}
