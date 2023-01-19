package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeMpoSalaryExtraBenefitAudit;
import com.eteacher.service.audit.EmployeeMpoSalaryExtraBenefitBreakdownAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeMpoSalaryExtraBenefit;
import com.eteacher.service.entity.mpo.EmployeeMpoSalaryExtraBenefitBreakdown;
import com.eteacher.service.helper.EmployeeMpoSalaryExtraBenefitHelper;
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
public class EmployeeMpoSalaryExtraBenefitService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    private final EmployeeMpoSalaryExtraBenefitHelper helper;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeMpoSalaryExtraBenefitAudit> audit = e.getMpoSalaryExtraBenefits()
                .stream()
                .map(EmployeeMpoSalaryExtraBenefitAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_SALARY_EXTRA_BENEFIT_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee save(Employee employee, Long salaryId) {
        Employee e = repository.save(employee);
        EmployeeMpoSalaryExtraBenefit salary = helper.findSalary(salaryId, employee);
        List<EmployeeMpoSalaryExtraBenefitBreakdownAudit> audits = salary.getBreakdowns()
                .stream()
                .map(EmployeeMpoSalaryExtraBenefitBreakdownAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                salary.getId(),
                EMPLOYEE_SALARY_EXTRA_BENEFIT_BREAKDOWN_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeMpoSalaryExtraBenefitAudit> audit = e.getMpoSalaryExtraBenefits()
                .stream()
                .map(EmployeeMpoSalaryExtraBenefitAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_SALARY_EXTRA_BENEFIT_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee, Long salaryId) {
        Employee e = repository.save(employee);
        EmployeeMpoSalaryExtraBenefit salary = helper.findSalary(salaryId, employee);
        List<EmployeeMpoSalaryExtraBenefitBreakdownAudit> audits = salary.getBreakdowns()
                .stream()
                .map(EmployeeMpoSalaryExtraBenefitBreakdownAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                salary.getId(),
                EMPLOYEE_SALARY_EXTRA_BENEFIT_BREAKDOWN_UPDATE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee, Long salaryId) {
        repository.save(employee);
        EmployeeMpoSalaryExtraBenefit salary = helper.findSalary(salaryId, employee);
        EmployeeMpoSalaryExtraBenefitAudit audit = EmployeeMpoSalaryExtraBenefitAudit.audit(salary);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_SALARY_EXTRA_BENEFIT_DELETE,
                objectToJson(audit)
        );
    }

    @Transactional
    public void delete(Employee employee, Long salaryId, Long breakdownId) {
        repository.save(employee);
        EmployeeMpoSalaryExtraBenefit salary = helper.findSalary(salaryId, employee);
        EmployeeMpoSalaryExtraBenefitBreakdown breakdown = helper.findBreakdown(breakdownId, salary);
        EmployeeMpoSalaryExtraBenefitBreakdownAudit audit = EmployeeMpoSalaryExtraBenefitBreakdownAudit.audit(breakdown);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_SALARY_EXTRA_BENEFIT_BREAKDOWN_DELETE,
                objectToJson(audit)
        );
    }
}
