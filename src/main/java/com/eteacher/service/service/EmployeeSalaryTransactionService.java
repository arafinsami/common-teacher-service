package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeSalaryTransactionAudit;
import com.eteacher.service.audit.EmployeeSalaryTransactionBreakdownAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeSalaryTransaction;
import com.eteacher.service.entity.mpo.EmployeeSalaryTransactionBreakdown;
import com.eteacher.service.helper.EmployeeSalaryTransactionHelper;
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
public class EmployeeSalaryTransactionService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    private final EmployeeSalaryTransactionHelper helper;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeSalaryTransactionAudit> audit = e.getSalaryTransactions()
                .stream()
                .map(EmployeeSalaryTransactionAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_SALARY_TRANSACTION_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee save(Employee employee, Long transactionId) {
        Employee e = repository.save(employee);
        EmployeeSalaryTransaction transaction = helper.findTransaction(transactionId, employee);
        List<EmployeeSalaryTransactionBreakdownAudit> audits = transaction.getBreakdowns()
                .stream()
                .map(EmployeeSalaryTransactionBreakdownAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                transaction.getId(),
                EMPLOYEE_SALARY_TRANSACTION_BREAKDOWN_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee j = repository.save(employee);
        List<EmployeeSalaryTransactionAudit> audit = j.getSalaryTransactions()
                .stream()
                .map(EmployeeSalaryTransactionAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                j.getId(),
                EMPLOYEE_SALARY_TRANSACTION_UPDATE,
                objectToJson(audit)
        );
        return j;
    }

    @Transactional
    public Employee update(Employee employee, Long transactionId, Long breakdownId) {
        Employee e = repository.save(employee);
        EmployeeSalaryTransaction transaction = helper.findTransaction(transactionId, employee);
        EmployeeSalaryTransactionBreakdown breakdown = helper.findBreakdown(breakdownId, transaction);
        EmployeeSalaryTransactionBreakdownAudit audit = EmployeeSalaryTransactionBreakdownAudit.audit(breakdown);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_SALARY_TRANSACTION_BREAKDOWN_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee, EmployeeSalaryTransaction transaction) {
        repository.save(employee);
        EmployeeSalaryTransactionAudit audit = EmployeeSalaryTransactionAudit.audit(transaction);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_SALARY_TRANSACTION_DELETE,
                objectToJson(audit)
        );
    }

    @Transactional
    public void delete(Employee employee, Long transactionId, Long breakdownId) {
        Employee e = repository.save(employee);
        EmployeeSalaryTransaction transaction = helper.findTransaction(transactionId, e);
        EmployeeSalaryTransactionBreakdown breakdown = helper.findBreakdown(breakdownId, transaction);
        EmployeeSalaryTransactionBreakdownAudit audit = EmployeeSalaryTransactionBreakdownAudit.audit(breakdown);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_SALARY_TRANSACTION_BREAKDOWN_DELETE,
                objectToJson(audit)
        );
    }
}
