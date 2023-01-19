package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.govtteacher.EmployeeAcrEvaluation;
import com.eteacher.service.entity.govtteacher.EmployeeAcrEvaluationAssessmentQuestionsAnswer;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeAcrEvaluationAssessmentQuestionsAnswerProfile;
import com.eteacher.service.profile.EmployeeAcrEvaluationProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_ACR_EVALUATION;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_ACR_EVALUATION_ANSWER;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeAcrEvaluationHelper {

    @Resource
    private ActiveUserContext context;

    public Function<EmployeeAcrEvaluationProfile, EmployeeAcrEvaluation> save = r -> {
        EmployeeAcrEvaluation evaluation = r.to();
        evaluation.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return evaluation;
    };

    public Function<EmployeeAcrEvaluationProfile, EmployeeAcrEvaluation> update = r -> {
        EmployeeAcrEvaluation evaluation = r.update();
        evaluation.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return evaluation;
    };

    public Function<EmployeeAcrEvaluationAssessmentQuestionsAnswerProfile, EmployeeAcrEvaluationAssessmentQuestionsAnswer> saveAnswerProfile = r -> {
        EmployeeAcrEvaluationAssessmentQuestionsAnswer answer = r.to();
        answer.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return answer;
    };

    public Function<EmployeeAcrEvaluationAssessmentQuestionsAnswerProfile, EmployeeAcrEvaluationAssessmentQuestionsAnswer> updateAnswerProfile = r -> {
        EmployeeAcrEvaluationAssessmentQuestionsAnswer answer = r.update();
        answer.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return answer;
    };

    public List<EmployeeAcrEvaluation> saveEvaluation(List<EmployeeAcrEvaluationProfile> profiles) {
        List<EmployeeAcrEvaluation> applications = profiles.stream()
                .map(save)
                .collect(Collectors.toList());
        return applications;
    }

    public List<EmployeeAcrEvaluation> updateEvaluation(List<EmployeeAcrEvaluationProfile> profiles) {
        List<EmployeeAcrEvaluation> applications = profiles.stream()
                .map(update)
                .collect(Collectors.toList());
        return applications;
    }

    public EmployeeAcrEvaluationAssessmentQuestionsAnswer saveAnswer(EmployeeAcrEvaluationAssessmentQuestionsAnswerProfile profile) {
        EmployeeAcrEvaluationAssessmentQuestionsAnswer answer = saveAnswerProfile.apply(profile);
        return answer;
    }

    public EmployeeAcrEvaluationAssessmentQuestionsAnswer updateAnswer(EmployeeAcrEvaluationAssessmentQuestionsAnswerProfile profile) {
        EmployeeAcrEvaluationAssessmentQuestionsAnswer answer = updateAnswerProfile.apply(profile);
        return answer;
    }

    public List<EmployeeAcrEvaluation> delete(EmployeeAcrEvaluation evaluation) {
        evaluation.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        evaluation.setRecordStatus(DELETED);
        return Arrays.asList(evaluation);
    }

    public EmployeeAcrEvaluationAssessmentQuestionsAnswer deleteAnswer(EmployeeAcrEvaluationAssessmentQuestionsAnswer answer) {
        answer.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        answer.setRecordStatus(DELETED);
        return answer;
    }

    private EmployeeAcrEvaluation findByEvaluationId(Long evaluationId, List<EmployeeAcrEvaluation> lists) {
        for (EmployeeAcrEvaluation evaluation : lists) {
            if (evaluation.getId().equals(evaluationId)) {
                return evaluation;
            }
        }
        return null;
    }

    private EmployeeAcrEvaluationAssessmentQuestionsAnswer findByAnswerId(Long answerId, List<EmployeeAcrEvaluationAssessmentQuestionsAnswer> lists) {
        for (EmployeeAcrEvaluationAssessmentQuestionsAnswer answer : lists) {
            if (answer.getId().equals(answerId)) {
                return answer;
            }
        }
        return null;
    }

    public EmployeeAcrEvaluation findEvaluation(Long evaluationId, Employee employee) {
        return Optional.ofNullable(findByEvaluationId(evaluationId, employee.getAcrEvaluations()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_ACR_EVALUATION + evaluationId)
                );
    }

    public EmployeeAcrEvaluationAssessmentQuestionsAnswer findAnswer(Long answerId, EmployeeAcrEvaluation evaluation) {
        return Optional.ofNullable(findByAnswerId(answerId, evaluation.getAnswers()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_ACR_EVALUATION_ANSWER + answerId)
                );
    }
}
