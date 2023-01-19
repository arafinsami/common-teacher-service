package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeMpoAffiliationAudit;
import com.eteacher.service.audit.EmployeeMpoAffiliationEncloserAudit;
import com.eteacher.service.audit.EmployeeMpoAffiliationReviewerAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeMpoAffiliation;
import com.eteacher.service.entity.mpo.EmployeeMpoAffiliationReviewer;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeMpoAffiliationHelper;
import com.eteacher.service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class EmployeeMpoAffiliationService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    private final EmployeeMpoAffiliationHelper helper;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeMpoAffiliationAudit> audit = e.getMpoAffiliations()
                .stream()
                .map(EmployeeMpoAffiliationAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_AFFILIATION_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee save(Employee employee, Long affiliationId) {
        Employee e = repository.save(employee);
        EmployeeMpoAffiliation affiliation = helper.findAffiliation(affiliationId, e);
        List<EmployeeMpoAffiliationReviewerAudit> audits = affiliation.getReviewers()
                .stream()
                .map(EmployeeMpoAffiliationReviewerAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                affiliation.getId(),
                EMPLOYEE_AFFILIATION_REVIEWER_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee saveFiles(Employee employee, Long affiliationId) {
        Employee e = repository.save(employee);
        EmployeeMpoAffiliation affiliation = helper.findAffiliation(affiliationId, e);
        List<EmployeeMpoAffiliationEncloserAudit> audits = affiliation.getFiles()
                .stream()
                .map(EmployeeMpoAffiliationEncloserAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                affiliation.getId(),
                EMPLOYEE_AFFILIATION_ENCLOSER_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee, Long affiliationId) {
        Employee e = repository.save(employee);
        EmployeeMpoAffiliation affiliation = helper.findAffiliation(affiliationId, e);
        List<EmployeeMpoAffiliationReviewerAudit> audits = affiliation.getReviewers()
                .stream()
                .map(EmployeeMpoAffiliationReviewerAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                affiliation.getId(),
                EMPLOYEE_AFFILIATION_REVIEWER_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeMpoAffiliationAudit> audit = e.getMpoAffiliations()
                .stream()
                .map(EmployeeMpoAffiliationAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_AFFILIATION_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee, Long affiliationId) {
        Employee e = repository.save(employee);
        EmployeeMpoAffiliation affiliation = helper.findAffiliation(affiliationId, e);
        EmployeeMpoAffiliationAudit audit = EmployeeMpoAffiliationAudit.audit(affiliation);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_AFFILIATION_DELETE,
                objectToJson(audit)
        );
    }

    @Transactional
    public void delete(Employee employee, Long affiliationId, Long reviewerId) {
        Employee e = repository.save(employee);
        EmployeeMpoAffiliation affiliation = helper.findAffiliation(affiliationId, e);
        EmployeeMpoAffiliationReviewer reviewer = helper.findReviewer(reviewerId, affiliation);
        EmployeeMpoAffiliationReviewerAudit audit = EmployeeMpoAffiliationReviewerAudit.audit(reviewer);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_AFFILIATION_REVIEWER_DELETE,
                objectToJson(audit)
        );
    }
}
