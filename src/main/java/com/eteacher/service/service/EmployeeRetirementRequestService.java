package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeRetirementRequestAudit;
import com.eteacher.service.audit.EmployeeRetirementRequestEncloserAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeRetirementRequest;
import com.eteacher.service.helper.EmployeeRetirementRequestHelper;
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
public class EmployeeRetirementRequestService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    private final EmployeeRetirementRequestHelper helper;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeRetirementRequestAudit> audits = e.getRetirementRequests()
                .stream()
                .map(EmployeeRetirementRequestAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_RETIREMENT_REQUEST_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee save(Employee employee, EmployeeRetirementRequest request) {
        Employee e = repository.save(employee);
        List<EmployeeRetirementRequestEncloserAudit> audits = request.getFiles()
                .stream()
                .map(EmployeeRetirementRequestEncloserAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_RETIREMENT_REQUEST_ENCLOSER_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeRetirementRequestAudit> audits = e.getRetirementRequests()
                .stream()
                .map(EmployeeRetirementRequestAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_RETIREMENT_REQUEST_UPDATE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee, Long requestId) {
        Employee e = repository.save(employee);
        EmployeeRetirementRequest request = helper.findRequest(requestId, employee);
        EmployeeRetirementRequestAudit audit = EmployeeRetirementRequestAudit.audit(request);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_RETIREMENT_REQUEST_DELETE,
                objectToJson(audit)
        );
    }
}
