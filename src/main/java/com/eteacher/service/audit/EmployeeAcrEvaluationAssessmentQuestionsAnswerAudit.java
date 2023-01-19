package com.eteacher.service.audit;

import com.eteacher.service.entity.govtteacher.EmployeeAcrEvaluationAssessmentQuestionsAnswer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeAcrEvaluationAssessmentQuestionsAnswerAudit {

    private Long id;

    private String answer;

    private String answerBn;

    public static EmployeeAcrEvaluationAssessmentQuestionsAnswerAudit audit(EmployeeAcrEvaluationAssessmentQuestionsAnswer answer) {
        EmployeeAcrEvaluationAssessmentQuestionsAnswerAudit audit = new EmployeeAcrEvaluationAssessmentQuestionsAnswerAudit();
        audit.setId(answer.getId());
        audit.setAnswer(answer.getAnswer());
        audit.setAnswerBn(answer.getAnswerBn());
        return audit;
    }
}
