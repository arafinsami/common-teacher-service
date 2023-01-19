package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeAcrEvaluationAssessmentQuestionsAnswerProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class EmployeeAcrEvaluationAssessmentQuestionsAnswerDto {

    @NotNull
    private Long employeeId;

    @NotNull
    private Long evaluationId;

    @Valid
    private EmployeeAcrEvaluationAssessmentQuestionsAnswerProfile answer;
}
