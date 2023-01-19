package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeePensionRequestAudit;
import com.eteacher.service.audit.EmployeePensionRequestEncloserAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.govtteacher.EmployeePensionRequest;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.SAVE;
import static com.eteacher.service.enums.Action.UPDATE;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class EmployeePensionRequestService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    @Transactional
    public Employee save(Employee employee) {
        Employee j = repository.save(employee);
        List<EmployeePensionRequestAudit> audit = j.getPensionRequests().stream()
                .map(EmployeePensionRequestAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                j.getId(),
                RECRUITMENT_PENSION_REQUEST_SAVE,
                objectToJson(audit)
        );
        return j;
    }

    @Transactional
    public Employee saveEncloser(Employee employee, EmployeePensionRequest pensionRequest) {
        Employee e = repository.save(employee);
        List<EmployeePensionRequestEncloserAudit> audits = pensionRequest.getFiles()
                .stream()
                .map(EmployeePensionRequestEncloserAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                pensionRequest.getId(),
                RECRUITMENT_PENSION_REQUEST_ENCLOSER_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee j = repository.save(employee);
        List<EmployeePensionRequestAudit> audit = j.getPensionRequests().stream()
                .map(EmployeePensionRequestAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                j.getId(),
                RECRUITMENT_PENSION_REQUEST_UPDATE,
                objectToJson(audit)
        );
        return j;
    }

    @Transactional
    public void delete(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeePensionRequestAudit> audits = e.getPensionRequests().stream()
                .map(EmployeePensionRequestAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                RECRUITMENT_PENSION_REQUEST_DELETE,
                objectToJson(audits)
        );
    }
}
