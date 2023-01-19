package com.eteacher.service.service;

import com.eteacher.service.audit.SalaryScaleBreakdownAudit;
import com.eteacher.service.dto.SalaryScaleBreakdownDto;
import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.commonteacher.SalaryScaleBreakdown;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.SalaryScaleBreakdownHelper;
import com.eteacher.service.query.GenericQuery;
import com.eteacher.service.repository.SalaryBreakdownRepository;
import com.eteacher.service.repository.SalaryScaleBreakdownRepository;
import com.eteacher.service.repository.SalaryScaleRepository;
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
public class SalaryScaleBreakdownService extends GenericQuery {

    private final SalaryScaleBreakdownRepository repository;

    private final SalaryScaleRepository salaryScaleRepository;

    private final SalaryBreakdownRepository salaryBreakdownRepository;

    private final ActionLogService actionLogService;

    private final SalaryScaleBreakdownHelper helper;

    public Optional<SalaryScaleBreakdown> findBySalaryBreakdownAndSalaryScale(
            Long salaryBreakdown,
            Long salaryScale) {
        SalaryScale scale = salaryScaleRepository
                .findByIdAndRecordStatusNot(salaryScale, RecordStatus.DELETED)
                .orElseThrow(ResourceNotFoundException::new);
        SalaryBreakdown breakdown = salaryBreakdownRepository
                .findByIdAndRecordStatusNot(salaryBreakdown, RecordStatus.DELETED)
                .orElseThrow(ResourceNotFoundException::new);
        Optional<SalaryScaleBreakdown> salaryScaleBreakdown = repository
                .findBySalaryBreakdownAndSalaryScale(breakdown, scale);
        return salaryScaleBreakdown;
    }

    public Optional<SalaryScaleBreakdown> findBySalaryBreakdownAndSalaryScaleAndRecordStatusNot(
            SalaryBreakdown salaryBreakdown,
            SalaryScale salaryScale) {
        Optional<SalaryScaleBreakdown> salaryScaleBreakdown = repository
                .findBySalaryBreakdownAndSalaryScaleAndRecordStatusNot(salaryBreakdown, salaryScale, RecordStatus.DELETED);
        return salaryScaleBreakdown;
    }

    @Transactional
    public SalaryScaleBreakdown save(SalaryScaleBreakdownDto request, SalaryScaleBreakdown salaryScaleBreakdown) {
        helper.getSaveData(request, salaryScaleBreakdown);
        SalaryScaleBreakdown breakdown = repository.save(salaryScaleBreakdown);
        SalaryScaleBreakdownAudit audit = SalaryScaleBreakdownAudit.from(breakdown);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getSalaryBreakdownId(),
                SALARY_SCALE_BREAKDOWN_SAVE,
                objectToJson(audit)
        );
        return breakdown;
    }

    @Transactional
    public SalaryScaleBreakdown update(SalaryScaleBreakdown salaryScaleBreakdown) {
        helper.getUpdateData(salaryScaleBreakdown, RecordStatus.ACTIVE);
        SalaryScaleBreakdown breakdown = repository.save(salaryScaleBreakdown);
        SalaryScaleBreakdownAudit audit = SalaryScaleBreakdownAudit.from(breakdown);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getSalaryBreakdownId(),
                SALARY_SCALE_BREAKDOWN_UPDATE,
                objectToJson(audit)
        );
        return breakdown;
    }

    @Transactional
    public SalaryScaleBreakdown delete(SalaryScaleBreakdown salaryScaleBreakdown) {
        helper.getUpdateData(salaryScaleBreakdown, RecordStatus.DELETED);
        SalaryScaleBreakdown breakdown = repository.save(salaryScaleBreakdown);
        SalaryScaleBreakdownAudit audit = SalaryScaleBreakdownAudit.from(breakdown);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                SALARY_SCALE_BREAKDOWN_DELETE,
                objectToJson(audit)
        );
        return breakdown;
    }
}
