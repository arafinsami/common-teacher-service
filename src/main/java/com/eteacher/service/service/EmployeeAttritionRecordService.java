package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeAttritionRecordAudit;
import com.eteacher.service.audit.EmployeeAttritionRecordEncloserAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeAttritionRecord;
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
public class EmployeeAttritionRecordService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeAttritionRecordAudit> audit = e.getAttritionRecords()
                .stream()
                .map(EmployeeAttritionRecordAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_ATTRITION_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee save(Employee employee, EmployeeAttritionRecord record) {
        Employee e = repository.save(employee);
        List<EmployeeAttritionRecordEncloserAudit> audits = record.getFiles()
                .stream()
                .map(EmployeeAttritionRecordEncloserAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                record.getId(),
                EMPLOYEE_ATTRITION_ENCLOSER_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee attritionRecord) {
        Employee e = repository.save(attritionRecord);
        List<EmployeeAttritionRecordAudit> audit = e.getAttritionRecords()
                .stream()
                .map(EmployeeAttritionRecordAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_ATTRITION_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee, EmployeeAttritionRecord record) {
        repository.save(employee);
        EmployeeAttritionRecordAudit audit = EmployeeAttritionRecordAudit.audit(record);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_ATTRITION_DELETE,
                objectToJson(audit)
        );
    }

}
