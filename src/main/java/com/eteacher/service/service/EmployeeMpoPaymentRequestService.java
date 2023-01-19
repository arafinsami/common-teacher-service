package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeMpoPaymentRequestAudit;
import com.eteacher.service.audit.EmployeeMpoPaymentRequestBreakdownAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeMpoPaymentRequest;
import com.eteacher.service.entity.mpo.EmployeeMpoPaymentRequestBreakdown;
import com.eteacher.service.helper.EmployeeMpoPaymentRequestHelper;
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
public class EmployeeMpoPaymentRequestService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    private final EmployeeMpoPaymentRequestHelper helper;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeMpoPaymentRequestAudit> audit = e.getMpoPaymentRequests()
                .stream()
                .map(EmployeeMpoPaymentRequestAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_PAYMENT_REQUEST_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee save(Employee employee, Long paymentRequestId) {
        Employee e = repository.save(employee);
        EmployeeMpoPaymentRequest paymentRequest = helper.findPayment(paymentRequestId, employee);
        List<EmployeeMpoPaymentRequestBreakdownAudit> audits = paymentRequest.getRequestBreakdowns()
                .stream()
                .map(EmployeeMpoPaymentRequestBreakdownAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                paymentRequest.getId(),
                EMPLOYEE_PAYMENT_REQUEST_BREAKDOWN_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeMpoPaymentRequestAudit> audit = e.getMpoPaymentRequests()
                .stream()
                .map(EmployeeMpoPaymentRequestAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_PAYMENT_REQUEST_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee, Long paymentRequestId) {
        Employee e = repository.save(employee);
        EmployeeMpoPaymentRequest paymentRequest = helper.findPayment(paymentRequestId, employee);
        List<EmployeeMpoPaymentRequestBreakdownAudit> audits = paymentRequest.getRequestBreakdowns().stream()
                .map(EmployeeMpoPaymentRequestBreakdownAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                paymentRequest.getId(),
                EMPLOYEE_PAYMENT_REQUEST_BREAKDOWN_UPDATE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee, Long paymentRequestId) {
        repository.save(employee);
        EmployeeMpoPaymentRequest paymentRequest = helper.findPayment(paymentRequestId, employee);
        EmployeeMpoPaymentRequestAudit audit = EmployeeMpoPaymentRequestAudit.audit(paymentRequest);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_PAYMENT_REQUEST_DELETE,
                objectToJson(audit)
        );
    }

    @Transactional
    public void delete(Employee employee, Long paymentRequestId, Long breakDownId) {
        repository.save(employee);
        EmployeeMpoPaymentRequest paymentRequest = helper.findPayment(paymentRequestId, employee);
        EmployeeMpoPaymentRequestBreakdown breakdown = helper.findBreakdown(breakDownId, paymentRequest);
        EmployeeMpoPaymentRequestBreakdownAudit audit = EmployeeMpoPaymentRequestBreakdownAudit.audit(breakdown);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_PAYMENT_REQUEST_BREAKDOWN_DELETE,
                objectToJson(audit)
        );
    }
}
