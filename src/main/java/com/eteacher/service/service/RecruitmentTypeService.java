package com.eteacher.service.service;

import com.eteacher.service.audit.RecruitmentTypeAudit;
import com.eteacher.service.entity.commonteacher.RecruitmentType;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.RecruitmentTypeHelper;
import com.eteacher.service.repository.RecruitmentTypeRepository;
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
public class RecruitmentTypeService {

    private final RecruitmentTypeRepository repository;

    private final ActionLogService actionLogService;

    private final RecruitmentTypeHelper helper;

    public Optional<RecruitmentType> findById(Long id) {
        Optional<RecruitmentType> recruitmentType = repository.findById(id);
        return recruitmentType;
    }

  public Optional<RecruitmentType> findByRecruitmentTypeName(String recruitmentTypeName) {
    Optional<RecruitmentType> recruitmentType = repository.findByRecruitmentTypeNameAndRecordStatusNot(
            recruitmentTypeName, RecordStatus.DELETED);
    return recruitmentType;
  }

    public Optional<RecruitmentType> findByRecruitmentTypeNameBn(String recruitmentTypeNameBn) {
        Optional<RecruitmentType> recruitmentType = repository.findByRecruitmentTypeNameBnAndRecordStatusNot(
                recruitmentTypeNameBn, RecordStatus.DELETED);
        return recruitmentType;
    }

    public Optional<RecruitmentType> findByDescription(String recruitmentTypeNameBn) {
        Optional<RecruitmentType> recruitmentType = repository.findByDescriptionAndRecordStatusNot(
                recruitmentTypeNameBn, RecordStatus.DELETED);
        return recruitmentType;
    }

    public Optional<RecruitmentType> findByDescriptionBn(String recruitmentTypeNameBn) {
        Optional<RecruitmentType> recruitmentType = repository.findByDescriptionBnAndRecordStatusNot(
                recruitmentTypeNameBn, RecordStatus.DELETED);
        return recruitmentType;
    }

    @Transactional
    public RecruitmentType save(RecruitmentType recruitmentType) {
        helper.getSaveData(recruitmentType);
        RecruitmentType r = repository.save(recruitmentType);
        RecruitmentTypeAudit audit = RecruitmentTypeAudit.audit(r);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                RECRUITMENT_TYPE_SAVE,
                objectToJson(audit)
        );
        return r;
    }

    @Transactional
    public RecruitmentType update(RecruitmentType recruitmentType) {
        helper.getUpdateData(recruitmentType, RecordStatus.ACTIVE);
        RecruitmentType r = repository.save(recruitmentType);
        RecruitmentTypeAudit audit = RecruitmentTypeAudit.audit(r);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                RECRUITMENT_TYPE_UPDATE,
                objectToJson(audit)
        );
        return r;
    }

    @Transactional
    public RecruitmentType delete(RecruitmentType recruitmentType) {
        helper.getUpdateData(recruitmentType, RecordStatus.DELETED);
        RecruitmentType r = repository.save(recruitmentType);
        RecruitmentTypeAudit audit = RecruitmentTypeAudit.audit(r);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                RECRUITMENT_TYPE_DELETE,
                objectToJson(audit)
        );
        return r;
    }
}
