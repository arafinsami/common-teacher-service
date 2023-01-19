package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestionAnswerOption;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class PerformanceEvaluationAssessmentQuestionAnswerOptionDto {

    private Long id;

    @NotBlank
    private String performanceEvaluationAssessmentQuestionAnswerOption;

    @NotBlank
    private String performanceEvaluationAssessmentQuestionAnswerOptionBn;

    @NotNull
    private Long performanceEvaluationAssessmentQuestion;

    public PerformanceEvaluationAssessmentQuestionAnswerOption to() {
        PerformanceEvaluationAssessmentQuestionAnswerOption answerOption = new PerformanceEvaluationAssessmentQuestionAnswerOption();
        answerOption.setAssessmentQuestionAnswerOption(performanceEvaluationAssessmentQuestionAnswerOption);
        answerOption.setAssessmentQuestionAnswerOptionBn(performanceEvaluationAssessmentQuestionAnswerOptionBn);
        return answerOption;
    }

    public void update(PerformanceEvaluationAssessmentQuestionAnswerOptionDto request, PerformanceEvaluationAssessmentQuestionAnswerOption answerOption) {
        BeanUtils.copyProperties(request, answerOption);
    }

}
