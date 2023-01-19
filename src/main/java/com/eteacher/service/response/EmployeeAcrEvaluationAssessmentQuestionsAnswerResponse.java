package com.eteacher.service.response;

import com.eteacher.service.entity.govtteacher.EmployeeAcrEvaluationAssessmentQuestionsAnswer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeAcrEvaluationAssessmentQuestionsAnswerResponse {

    private Long id;

    private String answer;

    private String answerBn;

    public static EmployeeAcrEvaluationAssessmentQuestionsAnswerResponse response(EmployeeAcrEvaluationAssessmentQuestionsAnswer answer) {
        EmployeeAcrEvaluationAssessmentQuestionsAnswerResponse response = new EmployeeAcrEvaluationAssessmentQuestionsAnswerResponse();
        response.setId(answer.getId());
        response.setAnswer(answer.getAnswer());
        response.setAnswerBn(answer.getAnswerBn());
        return response;
    }
}
