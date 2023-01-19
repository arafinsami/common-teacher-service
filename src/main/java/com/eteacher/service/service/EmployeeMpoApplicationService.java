package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeMpoApplicationAudit;
import com.eteacher.service.audit.EmployeeMpoApplicationEncloserAudit;
import com.eteacher.service.audit.EmployeeMpoApplicationReviewerAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeMpoApplication;
import com.eteacher.service.entity.mpo.EmployeeMpoApplicationReviewer;
import com.eteacher.service.helper.EmployeeMpoApplicationHelper;
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
public class EmployeeMpoApplicationService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    private final EmployeeMpoApplicationHelper helper;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeMpoApplicationAudit> audit = e.getMpoApplications()
                .stream()
                .map(EmployeeMpoApplicationAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_APPLICATION_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee save(Employee employee, Long applicationId) {
        Employee e = repository.save(employee);
        EmployeeMpoApplication application = helper.findApplication(applicationId, employee);
        List<EmployeeMpoApplicationReviewerAudit> audits = application.getReviewers()
                .stream()
                .map(EmployeeMpoApplicationReviewerAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                application.getId(),
                EMPLOYEE_APPLICATION_REVIEWER_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee save(Employee employee, EmployeeMpoApplication application) {
        Employee e = repository.save(employee);
        List<EmployeeMpoApplicationEncloserAudit> audits = application.getFiles()
                .stream()
                .map(EmployeeMpoApplicationEncloserAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                application.getId(),
                EMPLOYEE_APPLICATION_ENCLOSER_SAVE,
                objectToJson(audits)
        );
        return e;
    }


    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeMpoApplicationAudit> audit = e.getMpoApplications()
                .stream()
                .map(EmployeeMpoApplicationAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_APPLICATION_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee, Long applicationId) {
        Employee e = repository.save(employee);
        EmployeeMpoApplication application = helper.findApplication(applicationId, employee);
        List<EmployeeMpoApplicationReviewerAudit> audits = application.getReviewers()
                .stream()
                .map(EmployeeMpoApplicationReviewerAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                application.getId(),
                EMPLOYEE_APPLICATION_REVIEWER_UPDATE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee, Long applicationId) {
        repository.save(employee);
        EmployeeMpoApplication application = helper.findApplication(applicationId, employee);
        EmployeeMpoApplicationAudit audit = EmployeeMpoApplicationAudit.audit(application);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_APPLICATION_DELETE,
                objectToJson(audit)
        );
    }

    @Transactional
    public void delete(Employee employee, Long applicationId, Long reviewerId) {
        repository.save(employee);
        EmployeeMpoApplication application = helper.findApplication(applicationId, employee);
        EmployeeMpoApplicationReviewer reviewer = helper.findReviewer(reviewerId, application);
        EmployeeMpoApplicationReviewerAudit audit = EmployeeMpoApplicationReviewerAudit.audit(reviewer);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                application.getId(),
                EMPLOYEE_APPLICATION_REVIEWER_DELETE,
                objectToJson(audit)
        );
    }
}
