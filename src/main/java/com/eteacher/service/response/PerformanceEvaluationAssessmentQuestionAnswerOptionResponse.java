package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestionAnswerOption;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;


@Data
@NoArgsConstructor
public class PerformanceEvaluationAssessmentQuestionAnswerOptionResponse {
    private Long id;

    private String AssessmentQuestionAnswerOption;

    private String AssessmentQuestionAnswerOptionBn;

    private Long AssessmentQuestionId;
    private String AssessmentQuestionName;

    public static PerformanceEvaluationAssessmentQuestionAnswerOptionResponse from(PerformanceEvaluationAssessmentQuestionAnswerOption answerOption) {
        PerformanceEvaluationAssessmentQuestionAnswerOptionResponse response = new PerformanceEvaluationAssessmentQuestionAnswerOptionResponse();
        BeanUtils.copyProperties(answerOption, response);
        response.setAssessmentQuestionId(answerOption.getPerformanceEvaluationAssessmentQuestion().getId());
        response.setAssessmentQuestionName(answerOption.getPerformanceEvaluationAssessmentQuestion().getAssessmentQuestion());
        return response;
    }

    public static Map<String, Object> searchQuestionOption(
            String AssessmentQuestionAnswerOption,
            String AssessmentQuestionAnswerOptionBn) {
        Map<String, Object> map = new HashMap<>();
        map.put("AssessmentQuestionAnswerOption", AssessmentQuestionAnswerOption);
        map.put("AssessmentQuestionAnswerOptionBn", AssessmentQuestionAnswerOptionBn);
        return map;
    }
}
