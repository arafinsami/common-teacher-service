package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeReleaseRecordAudit;
import com.eteacher.service.audit.EmployeeReleaseRecordEncloserAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeReleaseRecord;
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
public class EmployeeReleaseRecordService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeReleaseRecordAudit> audit = e.getReleaseRecords()
                .stream()
                .map(EmployeeReleaseRecordAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_RELEASE_RECORD_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee save(Employee employee, EmployeeReleaseRecord record) {
        Employee e = repository.save(employee);
        List<EmployeeReleaseRecordEncloserAudit> audits = record.getFiles()
                .stream()
                .map(EmployeeReleaseRecordEncloserAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                record.getId(),
                EMPLOYEE_RELEASE_RECORD_ENCLOSER_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeReleaseRecordAudit> audit = e.getReleaseRecords()
                .stream()
                .map(EmployeeReleaseRecordAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_RELEASE_RECORD_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee, EmployeeReleaseRecord record) {
        repository.save(employee);
        EmployeeReleaseRecordAudit audit = EmployeeReleaseRecordAudit.audit(record);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_RELEASE_RECORD_DELETE,
                objectToJson(audit)
        );
    }
}
