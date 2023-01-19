package com.eteacher.service.service;

import com.eteacher.service.audit.PerformanceEvaluationAssessmentTopicAudit;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.GetListHelper;
import com.eteacher.service.helper.PerformanceEvaluationAssessmentTopicHelper;
import com.eteacher.service.query.DynamicQuery;
import com.eteacher.service.query.GenericQuery;
import com.eteacher.service.repository.PerformanceEvaluationAssessmentTopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class PerformanceEvaluationAssessmentTopicService extends GenericQuery {

  private final PerformanceEvaluationAssessmentTopicRepository repository;

  private final EntityManager em;

  private final ActionLogService actionLogService;

  private final PerformanceEvaluationAssessmentTopicHelper helper;

  public Map<String, Object> getList(
          String[] sortable,
          String[] searchable,
          String sortBy,
          String search,
          Integer page,
          Integer size,
          Map<String, Object> filterMap) {
    return new DynamicQuery<>(em, PerformanceEvaluationAssessmentTopic.class)
            .getList(sortable, searchable, sortBy, search, filterMap, page, size);
  }

  public List<PerformanceEvaluationAssessmentTopic> findAll(String[] sortable, String sortBy) {
    return new GetListHelper<>(em, PerformanceEvaluationAssessmentTopic.class).findAll(sortable, sortBy);
  }


  public Optional<PerformanceEvaluationAssessmentTopic> findById(Long id) {
    Optional<PerformanceEvaluationAssessmentTopic> topic = repository.findById(id);
    return topic;
  }

  public Optional<PerformanceEvaluationAssessmentTopic> findByIdAndRecordStatusNot(Long id) {
    Optional<PerformanceEvaluationAssessmentTopic> performanceEvaluationAssessmentTopic = repository
            .findByIdAndRecordStatusNot(id, RecordStatus.DELETED);
    return performanceEvaluationAssessmentTopic;
  }

  public Optional<PerformanceEvaluationAssessmentTopic> findByTopicDescription(String performanceEvaluationAssessmentTopicDescription) {
    Optional<PerformanceEvaluationAssessmentTopic> topic = repository.findByTopicDescription(performanceEvaluationAssessmentTopicDescription);
    return topic;
  }

  public Optional<PerformanceEvaluationAssessmentTopic> findByTopicDescriptionBn(String performanceEvaluationAssessmentTopicDescriptionBn) {
    Optional<PerformanceEvaluationAssessmentTopic> topic = repository.findByTopicDescriptionBn(performanceEvaluationAssessmentTopicDescriptionBn);
    return topic;
  }

  @Transactional
  public PerformanceEvaluationAssessmentTopic save(PerformanceEvaluationAssessmentTopic topic) {
    helper.getSaveData(topic);
    PerformanceEvaluationAssessmentTopic saveTopic = repository.save(topic);
    PerformanceEvaluationAssessmentTopicAudit audit = PerformanceEvaluationAssessmentTopicAudit.from(saveTopic);
    actionLogService.publishActivity(
            SAVE,
            E_TEACHER,
            audit.getId(),
            PERFORM_EVAL_ASSESSMENT_TOPIC_SAVE,
            objectToJson(audit)
    );
    return saveTopic;
  }

  @Transactional
  public PerformanceEvaluationAssessmentTopic update(PerformanceEvaluationAssessmentTopic topic) {
    helper.getUpdateData(topic, RecordStatus.ACTIVE);
    PerformanceEvaluationAssessmentTopic updatedTopic = repository.save(topic);
    PerformanceEvaluationAssessmentTopicAudit audit = PerformanceEvaluationAssessmentTopicAudit.from(updatedTopic);
    actionLogService.publishActivity(
            UPDATE,
            E_TEACHER,
            audit.getId(),
            PERFORM_EVAL_ASSESSMENT_TOPIC_UPDATE,
            objectToJson(audit)
    );
    return updatedTopic;
  }

  @Transactional
  public PerformanceEvaluationAssessmentTopic delete(
          PerformanceEvaluationAssessmentTopic topic) {
    helper.getUpdateData(topic, RecordStatus.DELETED);
    PerformanceEvaluationAssessmentTopic deletedTopic = repository
            .save(topic);
    PerformanceEvaluationAssessmentTopicAudit audit = PerformanceEvaluationAssessmentTopicAudit
            .from(deletedTopic);
    actionLogService.publishActivity(
            DELETE,
            E_TEACHER,
            audit.getId(),
            PERFORM_EVAL_ASSESSMENT_TOPIC_DELETE,
            objectToJson(audit)
    );
    return deletedTopic;
  }
}
