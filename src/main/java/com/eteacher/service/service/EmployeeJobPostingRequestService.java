package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeJobPostingRequestAudit;
import com.eteacher.service.audit.EmployeeJobPostingRequestEncloserAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeJobPostingRequest;
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
public class EmployeeJobPostingRequestService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    @Transactional
    public Employee save(Employee employee) {
        Employee j = repository.save(employee);
        List<EmployeeJobPostingRequestAudit> audit = j.getJobPostingRequests()
                .stream()
                .map(EmployeeJobPostingRequestAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                j.getId(),
                EMPLOYEE_JOB_POSTING_SAVE,
                objectToJson(audit)
        );
        return j;
    }

    @Transactional
    public Employee save(Employee employee, EmployeeJobPostingRequest jobPostingRequest) {
        Employee e = repository.save(employee);
        List<EmployeeJobPostingRequestEncloserAudit> audits = jobPostingRequest.getFiles()
                .stream()
                .map(EmployeeJobPostingRequestEncloserAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                jobPostingRequest.getId(),
                EMPLOYEE_JOB_POSTING_ENCLOSER_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeJobPostingRequestAudit> audit = e.getJobPostingRequests()
                .stream()
                .map(EmployeeJobPostingRequestAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_JOB_POSTING_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee, EmployeeJobPostingRequest request) {
        repository.save(employee);
        EmployeeJobPostingRequestAudit audit = EmployeeJobPostingRequestAudit.audit(request);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_JOB_POSTING_DELETE,
                objectToJson(audit)
        );
    }
}
