package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeDepartmentExaminationMeritAudit;
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
public class EmployeeDepartmentalExamMeritService extends BaseService {

    private final ActionLogService actionLogService;

    private final EmployeeRepository repository;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeDepartmentExaminationMeritAudit> audits = e.getDepartmentalExamMerits()
                .stream()
                .map(EmployeeDepartmentExaminationMeritAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_EXAM_MERIT_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeDepartmentExaminationMeritAudit> audits = e.getDepartmentalExamMerits()
                .stream()
                .map(EmployeeDepartmentExaminationMeritAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_EXAM_MERIT_UPDATE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeDepartmentExaminationMeritAudit> audits = e.getDepartmentalExamMerits()
                .stream()
                .map(EmployeeDepartmentExaminationMeritAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_EXAM_MERIT_DELETE,
                objectToJson(audits)
        );
    }
}
