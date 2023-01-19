package com.eteacher.service.service;

import com.eteacher.service.audit.SalaryScaleAudit;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.commonteacher.StudentType;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.GetListHelper;
import com.eteacher.service.helper.SalaryScaleHelper;
import com.eteacher.service.repository.SalaryScaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class SalaryScaleService extends BaseService {

    private final SalaryScaleRepository repository;

    private final ActionLogService actionLogService;

    private final SalaryScaleHelper helper;

    private final EntityManager em;

    public Optional<SalaryScale> findById(Long id) {
        Optional<SalaryScale> salaryScale = repository.findById(id);
        return salaryScale;
    }

    public List<SalaryScale> findAll(String[] sortable, String sortBy) {
        return new GetListHelper<SalaryScale>(em, SalaryScale.class).findAll(sortable, sortBy);
    }

    public Optional<SalaryScale> findByIdAndRecordStatusNot(Long id) {
        Optional<SalaryScale> salaryScale = repository.findByIdAndRecordStatusNot(id, RecordStatus.DELETED);
        return salaryScale;
    }

    public Optional<SalaryScale> findBySalaryScaleName(String salaryScaleNameBn) {
        Optional<SalaryScale> salaryScale = repository.findBySalaryScaleNameAndRecordStatusNot(
                salaryScaleNameBn, RecordStatus.DELETED);
        return salaryScale;
    }

    public Optional<SalaryScale> findBySalaryScaleNameBn(String salaryScaleNameBn) {
        Optional<SalaryScale> salaryScale = repository.findBySalaryScaleNameBnAndRecordStatusNot(
                salaryScaleNameBn, RecordStatus.DELETED);
        return salaryScale;
    }

    @Transactional
    public SalaryScale save(SalaryScale salaryScale) {
        helper.getSaveData(salaryScale);
        SalaryScale s = repository.save(salaryScale);
        SalaryScaleAudit audit = SalaryScaleAudit.audit(s);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                SALARY_SCALE_SAVE,
                objectToJson(audit)
        );
        return s;
    }

    @Transactional
    public SalaryScale update(SalaryScale salaryScale) {
        helper.getUpdateData(salaryScale, RecordStatus.ACTIVE);
        SalaryScale s = repository.save(salaryScale);
        SalaryScaleAudit audit = SalaryScaleAudit.audit(s);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                SALARY_SCALE_UPDATE,
                objectToJson(audit)
        );
        return s;
    }

    public SalaryScale delete(SalaryScale salaryScale) {
        helper.getUpdateData(salaryScale, RecordStatus.DELETED);
        SalaryScale s = repository.save(salaryScale);
        SalaryScaleAudit audit = SalaryScaleAudit.audit(s);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                SALARY_SCALE_DELETE,
                objectToJson(audit)
        );
        return s;
    }
}
