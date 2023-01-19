package com.eteacher.service.service;

import com.eteacher.service.audit.PerformanceEvaluationAssessmentQuestionAudit;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestion;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.PerformanceEvaluationAssessmentQuestionHelper;
import com.eteacher.service.query.GenericQuery;
import com.eteacher.service.repository.PerformanceEvaluationAssessmentQuestionRepository;
import com.eteacher.service.dto.PerformanceEvaluationAssessmentQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class PerformanceEvaluationAssessmentQuestionService extends GenericQuery {

    private final PerformanceEvaluationAssessmentQuestionRepository repository;
    //        private final EntityManager em;
    private final ActionLogService actionLogService;
    private final PerformanceEvaluationAssessmentQuestionHelper helper;


    public Optional<PerformanceEvaluationAssessmentQuestion> findByAssessmentQuestion(String assessmentQuestion) {
        return repository.findByAssessmentQuestion(assessmentQuestion);
    }

    public Optional<PerformanceEvaluationAssessmentQuestion> findByAssessmentQuestionBn(String assessmentQuestionBn) {
        return repository.findByAssessmentQuestionBn(assessmentQuestionBn);
    }

    @Transactional
    public PerformanceEvaluationAssessmentQuestion save(
            PerformanceEvaluationAssessmentQuestion answerOption,
            PerformanceEvaluationAssessmentQuestionDto request) {

        helper.getSaveData(answerOption);
        answerOption = helper.getParentData(answerOption, request);
        PerformanceEvaluationAssessmentQuestion saveAnswerOption = repository.save(answerOption);
        PerformanceEvaluationAssessmentQuestionAudit audit = PerformanceEvaluationAssessmentQuestionAudit.from(saveAnswerOption);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                PERFORM_EVAL_ASSESSMENT_QUESTION_SAVE,
                objectToJson(audit)
        );
        return saveAnswerOption;
    }

    public Optional<PerformanceEvaluationAssessmentQuestion> findById(Long id) {
        Optional<PerformanceEvaluationAssessmentQuestion> question = repository.findById(id);
        return question;
    }

    @Transactional
    public PerformanceEvaluationAssessmentQuestion  update(PerformanceEvaluationAssessmentQuestion question, PerformanceEvaluationAssessmentQuestionDto request) {
        helper.getUpdateData(question, RecordStatus.ACTIVE);
        question = helper.getParentData(question, request);

        PerformanceEvaluationAssessmentQuestion updateQuestion = repository.save(question);
        PerformanceEvaluationAssessmentQuestionAudit audit = PerformanceEvaluationAssessmentQuestionAudit.from(updateQuestion);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                PERFORM_EVAL_ASSESSMENT_QUESTION_UPDATE,
                objectToJson(audit)
        );
        return updateQuestion;
    }
}
