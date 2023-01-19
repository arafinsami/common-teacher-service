package com.eteacher.service.service;

import com.eteacher.service.audit.SalaryBreakdownAudit;
import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.SalaryBreakdownHelper;
import com.eteacher.service.repository.SalaryBreakdownRepository;
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
public class SalaryBreakdownService extends BaseService {

    private final SalaryBreakdownRepository repository;

    private final SalaryBreakdownHelper helper;

    private final ActionLogService actionLogService;

    public Optional<SalaryBreakdown> findById(Long id) {
        Optional<SalaryBreakdown> salaryBreakdown = repository.findById(id);
        return salaryBreakdown;
    }

    public Optional<SalaryBreakdown> findByBreakdownNameAndRecordStatusNot(String salaryBreakdownName, RecordStatus status) {
        Optional<SalaryBreakdown> salaryBreakdown = repository.findByBreakdownNameAndRecordStatusNot(salaryBreakdownName, status);
        return salaryBreakdown;
    }

    public Optional<SalaryBreakdown> findByBreakdownNameBnAndRecordStatusNot(String salaryBreakdownNameBn, RecordStatus status) {
        Optional<SalaryBreakdown> salaryBreakdown = repository.findByBreakdownNameBnAndRecordStatusNot(salaryBreakdownNameBn, status);
        return salaryBreakdown;
    }

    @Transactional
    public SalaryBreakdown save(SalaryBreakdown salaryBreakdown) {
        helper.getSaveData(salaryBreakdown);
        SalaryBreakdown savedSalaryBreakdown = repository.save(salaryBreakdown);
        SalaryBreakdownAudit audit = SalaryBreakdownAudit.audit(savedSalaryBreakdown);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                SALARY_BREAKDOWN_SAVE,
                objectToJson(audit)
        );
        return savedSalaryBreakdown;
    }

    @Transactional
    public SalaryBreakdown update(SalaryBreakdown salaryBreakdown) {
        helper.getUpdateData(salaryBreakdown, RecordStatus.ACTIVE);
        SalaryBreakdown updatedSalaryBreakdown = repository.save(salaryBreakdown);
        SalaryBreakdownAudit audit = SalaryBreakdownAudit.audit(updatedSalaryBreakdown);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                SALARY_BREAKDOWN_UPDATE,
                objectToJson(audit)
        );
        return updatedSalaryBreakdown;
    }

    @Transactional
    public SalaryBreakdown delete(SalaryBreakdown salaryBreakdown) {
        helper.getUpdateData(salaryBreakdown, RecordStatus.DELETED);
        SalaryBreakdown deletedSalaryBreakdown = repository.save(salaryBreakdown);
        SalaryBreakdownAudit audit = SalaryBreakdownAudit.audit(deletedSalaryBreakdown);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                SALARY_BREAKDOWN_DELETE,
                objectToJson(audit)
        );
        return deletedSalaryBreakdown;
    }
}
