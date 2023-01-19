package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeRetirementPaymentAudit;
import com.eteacher.service.audit.EmployeeRetirementPaymentBreakdownAudit;
import com.eteacher.service.audit.EmployeeRetirementPaymentEncloserAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeRetirementPayment;
import com.eteacher.service.entity.mpo.EmployeeRetirementPaymentBreakdown;
import com.eteacher.service.helper.EmployeeRetirementPaymentHelper;
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
public class EmployeeRetirementPaymentService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    private final EmployeeRetirementPaymentHelper helper;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeRetirementPaymentAudit> audit = e.getRetirementPayments()
                .stream()
                .map(EmployeeRetirementPaymentAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_RETIREMENT_PAYMENT_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee save(Employee employee, Long paymentId) {
        Employee e = repository.save(employee);
        EmployeeRetirementPayment payment = helper.findPayment(paymentId, employee);
        List<EmployeeRetirementPaymentBreakdownAudit> audits = payment.getBreakdowns()
                .stream()
                .map(EmployeeRetirementPaymentBreakdownAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                payment.getId(),
                EMPLOYEE_RETIREMENT_PAYMENT_BREAKDOWN_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee saveFiles(Employee employee, Long paymentId) {
        Employee e = repository.save(employee);
        EmployeeRetirementPayment payment = helper.findPayment(paymentId, employee);
        List<EmployeeRetirementPaymentEncloserAudit> audits = payment.getFiles()
                .stream()
                .map(EmployeeRetirementPaymentEncloserAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                payment.getId(),
                EMPLOYEE_RETIREMENT_PAYMENT_ENCLOSER_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeRetirementPaymentAudit> audit = e.getRetirementPayments()
                .stream()
                .map(EmployeeRetirementPaymentAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_RETIREMENT_PAYMENT_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee, Long paymentId, Long breakdownId) {
        Employee e = repository.save(employee);
        EmployeeRetirementPayment payment = helper.findPayment(paymentId, employee);
        EmployeeRetirementPaymentBreakdown breakdown = helper.findBreakdown(breakdownId, payment);
        EmployeeRetirementPaymentBreakdownAudit audit = EmployeeRetirementPaymentBreakdownAudit.audit(breakdown);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_RETIREMENT_PAYMENT_BREAKDOWN_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee, Long paymentId) {
        repository.save(employee);
        EmployeeRetirementPayment payment = helper.findPayment(paymentId, employee);
        EmployeeRetirementPaymentAudit audit = EmployeeRetirementPaymentAudit.audit(payment);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_RETIREMENT_PAYMENT_DELETE,
                objectToJson(audit)
        );
    }

    @Transactional
    public void delete(Employee employee, Long paymentId, Long breakdownId) {
        repository.save(employee);
        EmployeeRetirementPayment payment = helper.findPayment(paymentId, employee);
        EmployeeRetirementPaymentBreakdown breakdown = helper.findBreakdown(breakdownId, payment);
        EmployeeRetirementPaymentBreakdownAudit audit = EmployeeRetirementPaymentBreakdownAudit.audit(breakdown);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_RETIREMENT_PAYMENT_BREAKDOWN_DELETE,
                objectToJson(audit)
        );
    }
}
