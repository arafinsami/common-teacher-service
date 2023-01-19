package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeTrainingDetailsAudit;
import com.eteacher.service.entity.commonteacher.Employee;
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
public class EmployeeTrainingDetailsService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeTrainingDetailsAudit> audit = e.getTrainings()
                .stream()
                .map(EmployeeTrainingDetailsAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_TRAINING_DETAILS_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeTrainingDetailsAudit> audit = e.getTrainings()
                .stream()
                .map(EmployeeTrainingDetailsAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_TRAINING_DETAILS_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeTrainingDetailsAudit> audits = e.getTrainings()
                .stream()
                .map(EmployeeTrainingDetailsAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_TRAINING_DETAILS_DELETE,
                objectToJson(audits)
        );
    }
}
