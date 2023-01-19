package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeAudit;
import com.eteacher.service.dto.EmployeeDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.EmployeeHelper;
import com.eteacher.service.helper.GetListHelper;
import com.eteacher.service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.enums.RecordStatus.DELETED;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class EmployeeService extends BaseService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    private final EmployeeHelper helper;

    private final EntityManager em;

    public List<Employee> findAll(String[] sortable, String sortBy) {
        return new GetListHelper<Employee>(em, Employee.class).findAll(sortable, sortBy);
    }

    public Optional<Employee> findById(Long id) {
        Optional<Employee> employee = repository.findById(id);
        return employee;
    }

    public Optional<Employee> findByIdAndRecordStatusNot(Long id) {
        Optional<Employee> employee = repository.findByIdAndRecordStatusNot(id, DELETED);
        return employee;
    }

    public Optional<Employee> findByNidAndRecordStatusNot(String nid) {
        Optional<Employee> employee = repository
                .findByNidAndRecordStatusNot(nid, DELETED);
        return employee;
    }

    public Optional<Employee> findByBirthRegNoAndRecordStatusNot(String birthRegNo) {
        Optional<Employee> employee = repository
                .findByBirthRegNoAndRecordStatusNot(birthRegNo, DELETED);
        return employee;
    }

    public Optional<Employee> findByPassportNoAndRecordStatusNot(String passportNo) {
        Optional<Employee> employee = repository
                .findByPassportNoAndRecordStatusNot(passportNo, DELETED);
        return employee;
    }

    @Transactional
    public Employee save(Employee employee, EmployeeDto request) {
        helper.getSaveData(employee);
        employee = helper.getData(employee, request);
        Employee e = repository.save(employee);
        EmployeeAudit audit = EmployeeAudit.from(e);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee update(EmployeeDto request, Employee employee) {
        employee = helper.getData(employee, request);
        helper.getUpdateData(employee, RecordStatus.ACTIVE);
        Employee e = repository.save(employee);
        EmployeeAudit audit = EmployeeAudit.from(e);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee) {
        helper.getUpdateData(employee, DELETED);
        Employee e = repository.save(employee);
        EmployeeAudit audit = EmployeeAudit.from(e);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_DELETE,
                objectToJson(audit)
        );
    }

    public Map<String, Object> getList(Long institute, Integer page, Integer size, String sortBy, String search) {

        GetListHelper<Employee> helper = new GetListHelper<>(em, Employee.class);
        return helper.getList(repository.searchEmployee(search, institute,
                helper.getPageable(sortBy, page, size)), page, size);
    }

    public Map<String, Object> getList(Long institute, Long department, Integer page, Integer size, String sortBy, String search) {

        GetListHelper<Employee> helper = new GetListHelper<>(em, Employee.class);
        return helper.getList(repository.searchEmployee(search, institute, department,
                helper.getPageable(sortBy, page, size)), page, size);
    }
}
