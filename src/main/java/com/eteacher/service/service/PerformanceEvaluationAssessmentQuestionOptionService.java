package com.eteacher.service.service;

import com.eteacher.service.audit.PerformanceEvaluationAssessmentQuestionAnswerOptionAudit;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestionAnswerOption;
import com.eteacher.service.helper.PerformanceEvaluationAssessmentQuestionOptionHelper;
import com.eteacher.service.query.GenericQuery;
import com.eteacher.service.repository.PerformanceEvaluationAssessmentQuestionAnswerOptionRepository;
import com.eteacher.service.dto.PerformanceEvaluationAssessmentQuestionAnswerOptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

import static com.eteacher.service.constant.MessageConstants.PERFORM_EVAL_ASSESSMENT_QUESTION_OPTION_SAVE;
import static com.eteacher.service.enums.Action.SAVE;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class PerformanceEvaluationAssessmentQuestionOptionService extends GenericQuery {
    public final PerformanceEvaluationAssessmentQuestionAnswerOptionRepository repository;
    private final EntityManager em;
    private final ActionLogService actionLogService;
    private final PerformanceEvaluationAssessmentQuestionOptionHelper helper;

//    public Optional<PerformanceEvaluationAssessmentQuestionAnswerOption> findByIdAndRecordStatusNot(Long id, RecordStatus status) {
//        Optional<PerformanceEvaluationAssessmentQuestionAnswerOption> answerOption = repository.findByIdAndRecordStatusNot(id, status);
//        return answerOption;
//    }

    public Optional<PerformanceEvaluationAssessmentQuestionAnswerOption>
    findByPerformanceEvaluationAssessmentQuestionAnswerOption(
            String assessmentQuestionAnswerOption) {
        Optional<PerformanceEvaluationAssessmentQuestionAnswerOption> answerOption =
                repository.findByAssessmentQuestionAnswerOption(
                        assessmentQuestionAnswerOption);
        return answerOption;
    }

    public Optional<PerformanceEvaluationAssessmentQuestionAnswerOption>
    findByPerformanceEvaluationAssessmentQuestionAnswerOptionBn(
            String assessmentQuestionAnswerOptionBn) {
        Optional<PerformanceEvaluationAssessmentQuestionAnswerOption> answerOption =
                repository.findByAssessmentQuestionAnswerOptionBn(
                        assessmentQuestionAnswerOptionBn);
        return answerOption;
    }

    @Transactional
    public PerformanceEvaluationAssessmentQuestionAnswerOption save(
            PerformanceEvaluationAssessmentQuestionAnswerOption answerOption,
            PerformanceEvaluationAssessmentQuestionAnswerOptionDto request) {

        helper.getSaveData(answerOption);
        answerOption = helper.getPerformanceQuestion(answerOption, request);
        PerformanceEvaluationAssessmentQuestionAnswerOption saveAnswerOption = repository.save(answerOption);
        PerformanceEvaluationAssessmentQuestionAnswerOptionAudit audit = PerformanceEvaluationAssessmentQuestionAnswerOptionAudit.from(saveAnswerOption);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                PERFORM_EVAL_ASSESSMENT_QUESTION_OPTION_SAVE,
                objectToJson(audit)
        );
        return saveAnswerOption;
    }
}
