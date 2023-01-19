package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeAcrEvaluationAssessmentQuestionsAnswerAudit;
import com.eteacher.service.audit.EmployeeAcrEvaluationAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.govtteacher.EmployeeAcrEvaluation;
import com.eteacher.service.helper.EmployeeAcrEvaluationHelper;
import com.eteacher.service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class EmployeeArcEvaluationService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    private final EmployeeAcrEvaluationHelper helper;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeAcrEvaluationAudit> audit = e.getAcrEvaluations()
                .stream()
                .map(EmployeeAcrEvaluationAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_ACR_EVALUATION_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee saveAnswer(Employee employee, EmployeeAcrEvaluation evaluation) {
        Employee e = repository.save(employee);
        List<EmployeeAcrEvaluationAssessmentQuestionsAnswerAudit> audits = evaluation.getAnswers()
                .stream()
                .map(EmployeeAcrEvaluationAssessmentQuestionsAnswerAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                evaluation.getId(),
                EMPLOYEE_ACR_EVALUATION_ASSESMENT_ANSWER_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeAcrEvaluationAudit> audit = e.getAcrEvaluations()
                .stream()
                .map(EmployeeAcrEvaluationAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_ACR_EVALUATION_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee updateAnswer(Employee employee, EmployeeAcrEvaluation evaluation) {
        Employee e = repository.save(employee);
        List<EmployeeAcrEvaluationAssessmentQuestionsAnswerAudit> audits = evaluation.getAnswers()
                .stream()
                .map(EmployeeAcrEvaluationAssessmentQuestionsAnswerAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                evaluation.getId(),
                EMPLOYEE_ACR_EVALUATION_ASSESMENT_ANSWER_UPDATE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeAcrEvaluationAudit> audit = e.getAcrEvaluations()
                .stream()
                .map(EmployeeAcrEvaluationAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_ACR_EVALUATION_DELETE,
                objectToJson(audit)
        );
    }

    @Transactional
    public void deleteAnswer(Employee employee, EmployeeAcrEvaluation evaluation) {
        Employee e = repository.save(employee);
        List<EmployeeAcrEvaluationAssessmentQuestionsAnswerAudit> audits = evaluation.getAnswers()
                .stream()
                .map(EmployeeAcrEvaluationAssessmentQuestionsAnswerAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                evaluation.getId(),
                EMPLOYEE_ACR_EVALUATION_ASSESMENT_ANSWER_DELETE,
                objectToJson(audits)
        );
    }
}
