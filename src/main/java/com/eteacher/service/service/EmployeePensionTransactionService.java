package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeePensionTransactionAudit;
import com.eteacher.service.audit.EmployeePensionTransactionBreakdownAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.govtteacher.EmployeePensionTransaction;
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
public class EmployeePensionTransactionService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeePensionTransactionAudit> audit = e.getPensionTransactions()
                .stream()
                .map(EmployeePensionTransactionAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_PENSION_TRANSACTION_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee saveBreakDown(Employee employee, EmployeePensionTransaction transaction) {
        Employee e = repository.save(employee);
        List<EmployeePensionTransactionBreakdownAudit> audits = transaction.getTransactionBreakdowns()
                .stream()
                .map(EmployeePensionTransactionBreakdownAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                transaction.getId(),
                EMPLOYEE_PENSION_TRANSACTION_BREAKDOWN_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee j = repository.save(employee);
        List<EmployeePensionTransactionAudit> audit = j.getPensionTransactions()
                .stream()
                .map(EmployeePensionTransactionAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                j.getId(),
                EMPLOYEE_PENSION_TRANSACTION_UPDATE,
                objectToJson(audit)
        );
        return j;
    }

    @Transactional
    public Employee updateBreakDown(Employee employee, EmployeePensionTransaction transaction) {
        Employee e = repository.save(employee);
        List<EmployeePensionTransactionBreakdownAudit> audits = transaction.getTransactionBreakdowns()
                .stream()
                .map(EmployeePensionTransactionBreakdownAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                transaction.getId(),
                EMPLOYEE_PENSION_TRANSACTION_BREAKDOWN_UPDATE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeePensionTransactionAudit> audits = e.getPensionTransactions()
                .stream()
                .map(EmployeePensionTransactionAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_PENSION_TRANSACTION_DELETE,
                objectToJson(audits)
        );
    }

    @Transactional
    public void deleteBreakdown(Employee employee, EmployeePensionTransaction transaction) {
        Employee e = repository.save(employee);
        List<EmployeePensionTransactionBreakdownAudit> audits = transaction.getTransactionBreakdowns()
                .stream()
                .map(EmployeePensionTransactionBreakdownAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                transaction.getId(),
                EMPLOYEE_PENSION_TRANSACTION_BREAKDOWN_DELETE,
                objectToJson(audits)
        );
    }
}
