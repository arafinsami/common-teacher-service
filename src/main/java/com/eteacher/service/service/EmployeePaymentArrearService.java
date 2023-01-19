package com.eteacher.service.service;


import com.eteacher.service.audit.EmployeePaymentArrearAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeePaymentArrear;
import com.eteacher.service.helper.EmployeePaymentarrearHelper;
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
public class EmployeePaymentArrearService {

    private final ActionLogService actionLogService;

    private final EmployeeRepository repository;

    private final EmployeePaymentarrearHelper helper;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeePaymentArrearAudit> audits = e.getPaymentArrears()
                .stream()
                .map(EmployeePaymentArrearAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_ARREAR_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeePaymentArrearAudit> audits = e.getPaymentArrears()
                .stream()
                .map(EmployeePaymentArrearAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_ARREAR_UPDATE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee, Long arrearId) {
        repository.save(employee);
        EmployeePaymentArrear arrear = helper.findArrear(arrearId, employee);
        EmployeePaymentArrearAudit audit = EmployeePaymentArrearAudit.audit(arrear);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_ARREAR_DELETE,
                objectToJson(audit)
        );
    }
}
