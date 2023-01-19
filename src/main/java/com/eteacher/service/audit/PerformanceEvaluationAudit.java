package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluation;
import lombok.Data;

@Data
public class PerformanceEvaluationAudit {
    private Long id;

    private String evaluationDescription;

    private String evaluationDescriptionBn;

    private Integer qualifyingScore;

    private Integer performanceEvaluationYear;

    public static PerformanceEvaluationAudit from(PerformanceEvaluation performance) {
        PerformanceEvaluationAudit audit = new PerformanceEvaluationAudit();
        audit.setId(performance.getId());
        audit.setEvaluationDescription(performance.getEvaluationDescription());
        audit.setEvaluationDescriptionBn(performance.getEvaluationDescriptionBn());
        audit.setPerformanceEvaluationYear(performance.getPerformanceEvaluationYear());
        audit.setQualifyingScore(performance.getQualifyingScore());
        return audit;
    }
}
