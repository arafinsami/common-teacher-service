package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeePromotionRecordAudit;
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
public class EmployeePromotionRecordService {

    private final EmployeeRepository repository;

    private final ActionLogService actionLogService;

    @Transactional
    public Employee save(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeePromotionRecordAudit> audit = e.getPromotionRecords()
                .stream()
                .map(EmployeePromotionRecordAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_PROMOTION_RECORD_SAVE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public Employee update(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeePromotionRecordAudit> audit = e.getPromotionRecords()
                .stream()
                .map(EmployeePromotionRecordAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_PROMOTION_RECORD_UPDATE,
                objectToJson(audit)
        );
        return e;
    }

    @Transactional
    public void delete(Employee employee) {
        Employee e = repository.save(employee);
        List<EmployeePromotionRecordAudit> audits = e.getPromotionRecords()
                .stream()
                .map(EmployeePromotionRecordAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_PROMOTION_RECORD_DELETE,
                objectToJson(audits)
        );
    }
}
