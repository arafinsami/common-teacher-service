package com.eteacher.service.profile;

import com.eteacher.service.entity.govtteacher.EmployeeAcrEvaluationAssessmentQuestionsAnswer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeAcrEvaluationAssessmentQuestionsAnswerProfile {

    private Long id;

    @NotBlank
    private String answer;

    @NotBlank
    private String answerBn;

    public EmployeeAcrEvaluationAssessmentQuestionsAnswer to() {
        EmployeeAcrEvaluationAssessmentQuestionsAnswer questionsAnswer = new EmployeeAcrEvaluationAssessmentQuestionsAnswer();
        questionsAnswer.setAnswer(answer);
        questionsAnswer.setAnswerBn(answerBn);
        questionsAnswer.setRecordStatus(DRAFT);
        return questionsAnswer;
    }

    public EmployeeAcrEvaluationAssessmentQuestionsAnswer update() {
        EmployeeAcrEvaluationAssessmentQuestionsAnswer questionsAnswer = new EmployeeAcrEvaluationAssessmentQuestionsAnswer();
        questionsAnswer.setId(id);
        questionsAnswer.setAnswer(answer);
        questionsAnswer.setAnswerBn(answerBn);
        questionsAnswer.setRecordStatus(ACTIVE);
        return questionsAnswer;
    }
}
