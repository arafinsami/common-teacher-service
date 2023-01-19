package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluation;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class PerformanceEvaluationResponse {

    private Long id;

    private String evaluationDescription;

    private String evaluationDescriptionBn;

    private Integer qualifyingScore;

    private Integer performanceEvaluationYear;

    public static PerformanceEvaluationResponse from(PerformanceEvaluation performanceEvaluation) {
        PerformanceEvaluationResponse response = new PerformanceEvaluationResponse();
        BeanUtils.copyProperties(performanceEvaluation, response);
        return response;
    }

    public static Map<String, Object> searchPerformanceEvaluation(String performanceEvaluationDescription,
                                                                  String performanceEvaluationDescriptionBn) {
        Map<String, Object> map = new HashMap<>();
        map.put("performanceEvaluationDescription", performanceEvaluationDescription);
        map.put("performanceEvaluationDescriptionBn", performanceEvaluationDescriptionBn);
        return map;
    }
}
