package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeSalaryIncrementAudit;
import com.eteacher.service.audit.EmployeeSalaryIncrementBreakdownAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeSalaryIncrement;
import com.eteacher.service.entity.mpo.EmployeeSalaryIncrementBreakdown;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeSalaryIncrementHelper;
import com.eteacher.service.repository.EmployeeRepository;
import com.eteacher.service.repository.EmployeeSalaryIncrementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.SAVE;
import static com.eteacher.service.enums.Action.UPDATE;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class EmployeeSalaryIncrementService extends BaseService {

    private final EmployeeSalaryIncrementRepository incrementRepository;

    private final ActionLogService actionLogService;

    private final EmployeeRepository repository;

    private final EmployeeSalaryIncrementHelper helper;

    public EmployeeSalaryIncrement findByEffectiveDate(Employee emp, Date effectiveDate) {
        EmployeeSalaryIncrement increment = incrementRepository
                .findByEmployeeAndEffectiveDateAndRecordStatusNot(emp, effectiveDate, RecordStatus.DELETED);
        return increment;
    }

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeSalaryIncrementAudit> audits = e.getSalaryIncrements()
                .stream()
                .map(EmployeeSalaryIncrementAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_SALARY_INCREMENT_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee save(Employee employee, Long salaryId) {
        Employee e = repository.save(employee);
        EmployeeSalaryIncrement salary = e.getSalaryIncrements()
                .stream()
                .filter(f -> f.getId().equals(salaryId))
                .findAny()
                .orElseThrow(ResourceNotFoundException::new);
        List<EmployeeSalaryIncrementBreakdownAudit> audits = salary.getBreakdowns()
                .stream()
                .map(EmployeeSalaryIncrementBreakdownAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                salary.getId(),
                EMPLOYEE_SALARY_INCREMENT_BREAKDOWN_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeSalaryIncrementAudit> audits = e.getSalaryIncrements()
                .stream()
                .map(EmployeeSalaryIncrementAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_SALARY_INCREMENT_UPDATE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee, Long salaryId) {
        Employee e = repository.save(employee);
        EmployeeSalaryIncrement salary = e.getSalaryIncrements()
                .stream()
                .filter(f -> f.getId().equals(salaryId))
                .findAny()
                .orElseThrow(ResourceNotFoundException::new);
        List<EmployeeSalaryIncrementBreakdownAudit> audits = salary.getBreakdowns()
                .stream()
                .map(EmployeeSalaryIncrementBreakdownAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                salary.getId(),
                EMPLOYEE_SALARY_INCREMENT_BREAKDOWN_UPDATE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee, Long salaryId) {
        repository.save(employee);
        EmployeeSalaryIncrement salary = helper.findIncrement(salaryId, employee);
        EmployeeSalaryIncrementAudit audit = EmployeeSalaryIncrementAudit.audit(salary);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_SALARY_INCREMENT_DELETE,
                objectToJson(audit)
        );
    }

    @Transactional
    public void delete(Employee employee, Long salaryId, Long breakdownId) {
        repository.save(employee);
        EmployeeSalaryIncrement salary = helper.findIncrement(salaryId, employee);
        EmployeeSalaryIncrementBreakdown breakdown = helper.findBreakdown(breakdownId, salary);
        EmployeeSalaryIncrementBreakdownAudit audit = EmployeeSalaryIncrementBreakdownAudit.audit(breakdown);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_SALARY_INCREMENT_BREAKDOWN_DELETE,
                objectToJson(audit)
        );
    }
}
