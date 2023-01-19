package com.eteacher.service.service;

import com.eteacher.service.audit.InstituteExamMarkingPolicyAudit;
import com.eteacher.service.audit.InstituteExamMarkingPolicyDetailAudit;
import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicy;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.InstituteExamMarkingPolicyHelper;
import com.eteacher.service.repository.InstExamMarkPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class InstExamMarkPolicyService extends BaseService {

    private final InstExamMarkPolicyRepository repository;

    private final ActionLogService actionLogService;

    private final InstituteExamMarkingPolicyHelper helper;

    public Optional<InstituteExamMarkingPolicy> findById(Long id) {
        Optional<InstituteExamMarkingPolicy> instituteExamMarkingPolicy = repository.findById(id);
        return instituteExamMarkingPolicy;
    }

    public Optional<InstituteExamMarkingPolicy> findByInstituteId(Long instExamMarkPolId) {
        return repository.findByInstituteId(instExamMarkPolId);
    }

    @Transactional
    public InstituteExamMarkingPolicy save(InstituteExamMarkingPolicy instituteExamMarkingPolicy) {
        helper.getSaveData(instituteExamMarkingPolicy);
        InstituteExamMarkingPolicy s = repository.save(instituteExamMarkingPolicy);
        InstituteExamMarkingPolicyAudit audit = InstituteExamMarkingPolicyAudit.audit(s);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                INST_EXAM_MARK_POL_SAVE,
                objectToJson(audit)
        );
        return s;
    }

    @Transactional
    public InstituteExamMarkingPolicy savePolicy(InstituteExamMarkingPolicy policy) {
        InstituteExamMarkingPolicy i = repository.save(policy);
        List<InstituteExamMarkingPolicyDetailAudit> audits = i.getPolicyDetails()
                .stream()
                .map(InstituteExamMarkingPolicyDetailAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                i.getId(),
                JOB_APPLICANT_CONTACT_SAVE,
                objectToJson(audits)
        );
        return i;
    }

    @Transactional
    public InstituteExamMarkingPolicy update(InstituteExamMarkingPolicy policy) {
        helper.getUpdateData(policy, RecordStatus.ACTIVE);
        InstituteExamMarkingPolicy i = repository.save(policy);
        InstituteExamMarkingPolicyAudit audit = InstituteExamMarkingPolicyAudit.audit(i);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                INST_EXAM_MARK_POL_UPDATE,
                objectToJson(audit)
        );
        return i;
    }

    @Transactional
    public InstituteExamMarkingPolicy updatePolicy(InstituteExamMarkingPolicy policy) {
        InstituteExamMarkingPolicy a = repository.save(policy);
        List<InstituteExamMarkingPolicyDetailAudit> details = a.getPolicyDetails()
                .stream()
                .map(InstituteExamMarkingPolicyDetailAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                a.getId(),
                INST_EXAM_MARK_POL_DETAIL_UPDATE,
                objectToJson(details)
        );
        return a;
    }

    @Transactional
    public InstituteExamMarkingPolicy delete(InstituteExamMarkingPolicy policy) {
        helper.getUpdateData(policy, RecordStatus.DELETED);
        InstituteExamMarkingPolicy i = repository.save(policy);
        InstituteExamMarkingPolicyAudit audit = InstituteExamMarkingPolicyAudit
                .audit(i);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                INST_EXAM_MARK_POL_DELETE,
                objectToJson(audit)
        );
        return i;
    }

    @Transactional
    public InstituteExamMarkingPolicy deletePolicy(InstituteExamMarkingPolicy policy) {
        InstituteExamMarkingPolicy i = repository.save(policy);
        List<InstituteExamMarkingPolicyDetailAudit> audits = i.getPolicyDetails()
                .stream()
                .map(InstituteExamMarkingPolicyDetailAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                i.getId(),
                INST_EXAM_MARK_POL_DETAIL_DELETE,
                objectToJson(audits)
        );
        return i;
    }
}
