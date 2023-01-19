package com.eteacher.service.service;

import com.eteacher.service.audit.JobCircularAudit;
import com.eteacher.service.entity.job.JobCircular;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.JobCircularHelper;
import com.eteacher.service.repository.JobCircularRepository;
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
public class JobCircularService {

    private final JobCircularRepository repository;

    private final ActionLogService actionLogService;

    private final JobCircularHelper helper;

    @Transactional
    public JobCircular save(JobCircular circular) {
        helper.getSaveData(circular);
        JobCircular j = repository.save(circular);
        JobCircularAudit audit = JobCircularAudit.audit(j);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                JOB_CIRCULAR_SAVE,
                objectToJson(audit)
        );
        return j;
    }

    @Transactional
    public JobCircular update(JobCircular circular) {
        helper.getUpdateData(circular, RecordStatus.ACTIVE);
        JobCircular j = repository.save(circular);
        JobCircularAudit audit = JobCircularAudit.audit(j);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                JOB_CIRCULAR_UPDATE,
                objectToJson(audit)
        );
        return j;
    }

    public Optional<JobCircular> findById(Long id) {
        Optional<JobCircular> circular = repository.findById(id);
        return circular;
    }

    @Transactional
    public void delete(JobCircular circular) {
        helper.getUpdateData(circular, RecordStatus.DELETED);
        JobCircularAudit audit = JobCircularAudit.audit(circular);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                JOB_CIRCULAR_DELETE,
                objectToJson(audit)
        );
    }
}
