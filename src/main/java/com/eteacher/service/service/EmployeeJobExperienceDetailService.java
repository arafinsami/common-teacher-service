package com.eteacher.service.service;

import com.eteacher.service.audit.EmployeeJobExperienceDetailAudit;
import com.eteacher.service.dto.EmployeeJobExperienceDetailDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeJobExperienceDetail;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.EmployeeJobExperienceDetailHelper;
import com.eteacher.service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.EMPLOYEE_JOB_EXPERIENCE_DETAILS_SAVE;
import static com.eteacher.service.constant.MessageConstants.EMPLOYEE_TRAINING_DETAILS_UPDATE;
import static com.eteacher.service.enums.Action.SAVE;
import static com.eteacher.service.enums.Action.UPDATE;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DELETED;
import static com.eteacher.service.utils.CollectionUtils.distinct;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class EmployeeJobExperienceDetailService extends BaseService {

    private final ActionLogService actionLogService;

    private final EmployeeRepository repository;

    private final EmployeeJobExperienceDetailHelper helper;

    @Transactional
    public Employee save(Employee employee, EmployeeJobExperienceDetailDto request) {
        List<EmployeeJobExperienceDetail> details = distinct(helper.getJobExpDetail(request.getJobExperienceDetails(), false));
        helper.getSaveData(details);
        employee.addJobExperiences(details);
        Employee savedEmployee = repository.save(employee);
        List<EmployeeJobExperienceDetailAudit> audits = savedEmployee.getJobExperiences().stream()
                .map(EmployeeJobExperienceDetailAudit::from)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                savedEmployee.getId(),
                EMPLOYEE_JOB_EXPERIENCE_DETAILS_SAVE,
                objectToJson(audits)
        );
        return savedEmployee;
    }

    @Transactional
    public Employee update(Employee employee, EmployeeJobExperienceDetailDto request, RecordStatus status) {
        Boolean doDelete = status.equals(DELETED);
        helper.getUpdateData(distinct(helper.getJobExpDetail(request.getJobExperienceDetails(), doDelete)), ACTIVE);
        employee.getJobExperiences().clear();
        employee.addJobExperiences(distinct(helper.getJobExpDetail(request.getJobExperienceDetails(), doDelete)));
        Employee e = repository.save(employee);
        List<EmployeeJobExperienceDetailAudit> audits = e.getJobExperiences().stream()
                .map(EmployeeJobExperienceDetailAudit::from)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                e.getId(),
                EMPLOYEE_TRAINING_DETAILS_UPDATE,
                objectToJson(audits)
        );
        return e;
    }
}
