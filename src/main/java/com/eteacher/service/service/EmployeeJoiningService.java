package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeJoiningAudit;
import com.eteacher.service.audit.EmployeeJoiningEncloserAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeJoining;
import com.eteacher.service.query.GenericQuery;
import com.eteacher.service.repository.EmployeeJoiningRepository;
import com.eteacher.service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class EmployeeJoiningService extends GenericQuery {

    private final EmployeeRepository repository;

    private final EmployeeJoiningRepository joiningRepository;

    private final ActionLogService actionLogService;

    public Optional<EmployeeJoining> findById(Long id) {
        Optional<EmployeeJoining> employeeJoining = joiningRepository.findById(id);
        return employeeJoining;
    }

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeJoiningAudit> audits = e.getEmployeeJoinings()
                .stream()
                .map(EmployeeJoiningAudit::from)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_JOINING_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee saveFiles(Employee employee, EmployeeJoining joining) {
        Employee e = repository.save(employee);
        List<EmployeeJoiningEncloserAudit> audits = joining.getFiles()
                .stream()
                .map(EmployeeJoiningEncloserAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                joining.getId(),
                EMPLOYEE_JOINING_ENCLOSER_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeJoiningAudit> audits = e.getEmployeeJoinings()
                .stream()
                .map(EmployeeJoiningAudit::from)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_JOINING_UPDATE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeJoiningAudit> audits = e.getEmployeeJoinings()
                .stream()
                .map(EmployeeJoiningAudit::from)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_JOINING_DELETE,
                objectToJson(audits)
        );
    }

    public Optional<EmployeeJoining> findByJoiningFromDesignation(Long fromDesignationId, Employee e) {
        Optional<EmployeeJoining> joining = repository.findByJoiningFromDesignation(fromDesignationId, e);
        return joining;
    }

    public Optional<EmployeeJoining> findByJoiningToDesignation(Long toDesignationId, Employee e) {
        Optional<EmployeeJoining> joining = repository.findByJoiningToDesignation(toDesignationId, e);
        return joining;
    }

    public Optional<EmployeeJoining> findByJoiningFromInstitute(Long fromInstituteId, Employee e) {
        Optional<EmployeeJoining> joining = repository.findByJoiningFromInstitute(fromInstituteId, e);
        return joining;
    }

    public Optional<EmployeeJoining> findByJoiningToInstitute(Long toInstituteId, Employee e) {
        Optional<EmployeeJoining> joining = repository.findByJoiningToInstitute(toInstituteId, e);
        return joining;
    }

    public Optional<EmployeeJoining> findByLanguageSkillId(Long joiningId) {
        Optional<EmployeeJoining> joining = repository.findByJoiningId(joiningId);
        return joining;
    }

}
