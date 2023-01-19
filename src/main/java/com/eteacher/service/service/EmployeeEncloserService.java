package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeEncloserAudit;
import com.eteacher.service.audit.EmployeeLeaveRecordAudit;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeEncloser;
import com.eteacher.service.repository.EmployeeEncloserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.EMPLOYEE_ENCLOSER_SAVE;
import static com.eteacher.service.constant.MessageConstants.EMPLOYEE_LEAVE_RECORD_SAVE;
import static com.eteacher.service.enums.Action.SAVE;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class EmployeeEncloserService {

    private final EmployeeEncloserRepository repository;

    private final ActionLogService actionLogService;

    public Optional<EmployeeEncloser> findById(Long id) {
        Optional<EmployeeEncloser> encloser = repository.findById(id);
        return encloser;
    }

    public EmployeeEncloser save(EmployeeEncloser encloser){
        EmployeeEncloser e = repository.save(encloser);
        EmployeeEncloserAudit audit = EmployeeEncloserAudit.audit(e);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_ENCLOSER_SAVE,
                objectToJson(audit)
        );
        return e;
    }
}
