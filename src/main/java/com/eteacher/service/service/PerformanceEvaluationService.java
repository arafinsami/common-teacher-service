package com.eteacher.service.service;


import com.eteacher.service.audit.PerformanceEvaluationAudit;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluation;
import com.eteacher.service.helper.PerformanceEvaluationHelper;
import com.eteacher.service.query.DynamicQuery;
import com.eteacher.service.query.GenericQuery;
import com.eteacher.service.repository.PerformanceEvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.eteacher.service.constant.MessageConstants.PERFORM_EVAL_SAVE;
import static com.eteacher.service.constant.MessageConstants.PERFORM_EVAL_UPDATE;
import static com.eteacher.service.enums.Action.SAVE;
import static com.eteacher.service.enums.Action.UPDATE;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.isNotEmpty;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class PerformanceEvaluationService extends GenericQuery {

    private final PerformanceEvaluationRepository repository;

    private final EntityManager em;

    private final ActionLogService actionLogService;

    private final PerformanceEvaluationHelper helper;

    public Optional<PerformanceEvaluation> findByEvaluationDescription(String evaluationDescription) {
        Optional<PerformanceEvaluation> evaluation = repository.findByEvaluationDescription(evaluationDescription);
        return evaluation;
    }

    public Optional<PerformanceEvaluation> findByEvaluationDescriptionBn(String evaluationDescriptionBn) {
        Optional<PerformanceEvaluation> evaluation = repository.findByEvaluationDescriptionBn(evaluationDescriptionBn);
        return evaluation;
    }

    @Transactional
    public PerformanceEvaluation save(PerformanceEvaluation performanceEvaluation) {

        Optional<PerformanceEvaluation> find = repository.findTopByOrderByIdDesc();

        if (!find.isEmpty()) {
            helper.getSaveData(performanceEvaluation, find.get().getRecordId());
        } else {
            helper.getSaveData(performanceEvaluation, 0L);
        }

        PerformanceEvaluation s = repository.save(performanceEvaluation);
        PerformanceEvaluationAudit audit = PerformanceEvaluationAudit.from(s);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                PERFORM_EVAL_SAVE,
                objectToJson(audit)
        );
        return s;
    }

    public List<PerformanceEvaluation> findAll(String[] sortable, String sortBy) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PerformanceEvaluation> query = cb.createQuery(PerformanceEvaluation.class);
        Root<PerformanceEvaluation> root = query.from(PerformanceEvaluation.class);
        query.select(root);

        if (isNotEmpty(sortBy)) {
            sortBy(sortable, sortBy, cb, query, root);
        }

        TypedQuery<PerformanceEvaluation> tQuery = em.createQuery(query);

        return tQuery.getResultList();

    }

    public Optional<PerformanceEvaluation> findById(Long id) {
        return repository.findById(id);
    }


    public void delete(PerformanceEvaluation performanceEvaluation) {
//        actionLogService.publishActivity(
//                DELETE,
//                E_TEACHER,
//                performanceEvaluation.getId(),
//                PERFORM_EVAL_DELETE,
//                objectToJson(performanceEvaluation)
//        );
        repository.delete(performanceEvaluation);

    }

    public Map<String, Object> getList(
            String[] sortable, String[] searchable,
            String sortBy, String search,
            Integer page, Integer size,
            Map<String, Object> filterMap) {

        return new DynamicQuery<>(em, PerformanceEvaluation.class)
                .getList(sortable, searchable, sortBy, search, filterMap, page, size);
    }

    @Transactional
    public PerformanceEvaluation update(PerformanceEvaluation employeeType) {
        helper.getUpdateData(employeeType);
        PerformanceEvaluation s = repository.save(employeeType);
        PerformanceEvaluationAudit audit = PerformanceEvaluationAudit.from(s);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                PERFORM_EVAL_UPDATE,
                objectToJson(audit)
        );
        return s;
    }

}
