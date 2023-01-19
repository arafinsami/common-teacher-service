package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluation;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PerformanceEvaluationDto {

    private Long id;

    @NotBlank
    private String evaluationDescription;

    @NotBlank
    private String evaluationDescriptionBn;

    private Integer qualifyingScore;

    private Integer performanceEvaluationYear;

    public PerformanceEvaluation to(PerformanceEvaluationDto request) {
        PerformanceEvaluation performanceEvaluation = new PerformanceEvaluation();
        BeanUtils.copyProperties(request, performanceEvaluation);
        return performanceEvaluation;
    }

    public void update(PerformanceEvaluationDto request, PerformanceEvaluation performanceEvaluation) {
        BeanUtils.copyProperties(request, performanceEvaluation);
    }

}
