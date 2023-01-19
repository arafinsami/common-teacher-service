package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeTypeAudit;
import com.eteacher.service.entity.commonteacher.EmployeeType;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.EmployeeTypeHelper;
import com.eteacher.service.helper.GetListHelper;
import com.eteacher.service.query.GenericQuery;
import com.eteacher.service.repository.EmployeeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class EmployeeTypeService extends GenericQuery {

    private final EmployeeTypeRepository repository;

    private final ActionLogService actionLogService;

    private final EmployeeTypeHelper helper;

    private final EntityManager em;

    public List<EmployeeType> findAll(String[] sortable, String sortBy) {
        return new GetListHelper<EmployeeType>(em, EmployeeType.class).findAll(sortable, sortBy);
    }

    public Optional<EmployeeType> findById(Long id) {
        Optional<EmployeeType> employeeType = repository.findById(id);
        return employeeType;
    }

    public Optional<EmployeeType> findByEmployeeTypeNameAndRecordStatusNot(String employeeTypeName) {
        Optional<EmployeeType> employeeType = repository.findByEmployeeTypeNameAndRecordStatusNot(
                employeeTypeName, RecordStatus.DELETED);
        return employeeType;
    }

    public Optional<EmployeeType> findByEmployeeTypeNameBnAndRecordStatusNot(String employeeTypeNameBn) {
        Optional<EmployeeType> employeeType = repository.findByEmployeeTypeNameBnAndRecordStatusNot(
                employeeTypeNameBn, RecordStatus.DELETED);
        return employeeType;
    }

    @Transactional
    public EmployeeType save(EmployeeType employeeType) {
        helper.getSaveData(employeeType);
        EmployeeType e = repository.save(employeeType);
        EmployeeTypeAudit audit = EmployeeTypeAudit.from(e);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_TYPE_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public EmployeeType update(EmployeeType employeeType) {
        helper.getUpdateData(employeeType, RecordStatus.ACTIVE);
        EmployeeType e = repository.save(employeeType);
        EmployeeTypeAudit audit = EmployeeTypeAudit.from(e);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_TYPE_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public void delete(EmployeeType employeeType) {
        helper.getUpdateData(employeeType, RecordStatus.DELETED);
        EmployeeType e = repository.save(employeeType);
        EmployeeTypeAudit audit = EmployeeTypeAudit.from(e);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                EMPLOYEE_TYPE_DELETE,
                objectToJson(audit)
        );
    }
}
