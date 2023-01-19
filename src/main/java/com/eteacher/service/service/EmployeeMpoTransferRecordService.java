package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeTransferRecordAudit;
import com.eteacher.service.audit.EmployeeTransferRecordEncloserAudit;
import com.eteacher.service.audit.EmployeeTransferRecordPreferredInstituteAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeTransferRecord;
import com.eteacher.service.entity.mpo.EmployeeTransferRecordPreferredInstitute;
import com.eteacher.service.helper.EmployeeMpoTransferRecordHelper;
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
public class EmployeeMpoTransferRecordService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    private final EmployeeMpoTransferRecordHelper helper;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeTransferRecordAudit> audit = e.getTransferRecords()
                .stream()
                .map(EmployeeTransferRecordAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_TRANSFER_RECORD_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee save(Employee employee, Long recordId) {
        Employee e = repository.save(employee);
        EmployeeTransferRecord record = helper.findByRecord(recordId, employee);
        List<EmployeeTransferRecordPreferredInstituteAudit> audits = record.getPreferredInstitutes()
                .stream()
                .map(EmployeeTransferRecordPreferredInstituteAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                record.getId(),
                EMPLOYEE_TRANSFER_RECORD_INSTITUTE_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee saveFiles(Employee employee, Long recordId) {
        Employee e = repository.save(employee);
        EmployeeTransferRecord record = helper.findByRecord(recordId, employee);
        List<EmployeeTransferRecordEncloserAudit> audits = record.getFiles()
                .stream()
                .map(EmployeeTransferRecordEncloserAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                record.getId(),
                EMPLOYEE_TRANSFER_RECORD_FILE_SAVE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeeTransferRecordAudit> audit = e.getTransferRecords()
                .stream()
                .map(EmployeeTransferRecordAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_TRANSFER_RECORD_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee, Long recordId) {
        Employee e = repository.save(employee);
        EmployeeTransferRecord record = helper.findByRecord(recordId, employee);
        List<EmployeeTransferRecordPreferredInstituteAudit> audits = record.getPreferredInstitutes()
                .stream()
                .map(EmployeeTransferRecordPreferredInstituteAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                record.getId(),
                EMPLOYEE_TRANSFER_RECORD_INSTITUTE_UPDATE,
                objectToJson(audits)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee, EmployeeTransferRecord record) {
        repository.save(employee);
        EmployeeTransferRecordAudit audit = EmployeeTransferRecordAudit.audit(record);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_TRANSFER_RECORD_DELETE,
                objectToJson(audit)
        );
    }

    @Transactional
    public void delete(Employee employee, Long recordId, Long instituteId) {
        repository.save(employee);
        EmployeeTransferRecord record = helper.findByRecord(recordId, employee);
        EmployeeTransferRecordPreferredInstitute institute = helper.findByInstitute(instituteId, record);
        EmployeeTransferRecordPreferredInstituteAudit audit = EmployeeTransferRecordPreferredInstituteAudit.audit(institute);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_TRANSFER_RECORD_INSTITUTE_DELETE,
                objectToJson(audit)
        );
    }
}
