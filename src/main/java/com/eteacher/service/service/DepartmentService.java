package com.eteacher.service.service;

import com.eteacher.service.audit.DepartmentAudit;
import com.eteacher.service.audit.DepartmentalExaminationAudit;
import com.eteacher.service.entity.commonteacher.EmployeeType;
import com.eteacher.service.entity.ntrca.Department;
import com.eteacher.service.entity.ntrca.DepartmentalExamination;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.DepartmentHelper;
import com.eteacher.service.helper.GetListHelper;
import com.eteacher.service.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class DepartmentService extends BaseService {

    private final DepartmentRepository repository;

    private final ActionLogService actionLogService;

    private final DepartmentHelper helper;

    private final EntityManager em;

    @Transactional
    public Department save(Department department) {
        helper.getSaveData(department);
        Department d = repository.save(department);
        DepartmentAudit audit = DepartmentAudit.from(d);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                DEPARTMENT_SAVE,
                objectToJson(audit)
        );
        return d;
    }

    @Transactional
    public Department saveExam(Department department) {
        Department d = repository.save(department);
        List<DepartmentalExaminationAudit> audits = d.getExaminations()
                .stream()
                .map(DepartmentalExaminationAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                d.getId(),
                DEPARTMENT_EXANM_SAVE,
                objectToJson(audits)
        );
        return d;
    }

    @Transactional
    public Department update(Department department) {
        helper.getUpdateData(department, RecordStatus.ACTIVE);
        Department d = repository.save(department);
        DepartmentAudit audit = DepartmentAudit.from(d);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                INST_EXAM_MARK_POL_UPDATE,
                objectToJson(audit)
        );
        return d;
    }

    @Transactional
    public Department updateExam(Department department) {
        Department d = repository.save(department);
        List<DepartmentalExaminationAudit> audits = d.getExaminations()
                .stream()
                .map(DepartmentalExaminationAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                d.getId(),
                DEPARTMENT_EXAM_UPDATE,
                objectToJson(audits)
        );
        return d;
    }

    @Transactional
    public Department delete(Department department) {
        helper.getUpdateData(department, RecordStatus.DELETED);
        Department d = repository.save(department);
        DepartmentAudit audit = DepartmentAudit.from(d);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                DEPARTMENT_DELETE,
                objectToJson(audit)
        );
        return d;
    }

    @Transactional
    public void deleteExam(Department department) {
        Department d = repository.save(department);
        List<DepartmentalExaminationAudit> audits = d.getExaminations()
                .stream()
                .map(DepartmentalExaminationAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                d.getId(),
                DEPARTMENT_EXAM_DELETE,
                objectToJson(audits)
        );
    }

    public Optional<Department> findById(Long id) {
        Optional<Department> department = repository.findById(id);
        return department;
    }

    public List<Department> findAll(String[] sortable, String sortBy) {
        return new GetListHelper<Department>(em, Department.class).findAll(sortable, sortBy);
    }

    public Optional<Department> findByIdAndRecordStatusNot(Long id) {
        Optional<Department> department = repository.findByIdAndRecordStatusNot(id, RecordStatus.DELETED);
        return department;
    }

    public Optional<Department> findByDepartmentName(String departmentName) {
        Optional<Department> department = repository.findByDepartmentName(departmentName);
        return department;
    }

    public Optional<DepartmentalExamination> findByExamYear(Integer year) {
        Optional<DepartmentalExamination> examination = repository.findByExamYear(year);
        return examination;
    }

    public Map<String, Object> searchDepartment(String departmentName, Integer page, Integer size, String sortBy){
        GetListHelper<Department> helper = new GetListHelper<>(em, Department.class);
        return helper.getList(repository.searchDepartment(departmentName, helper.getPageable(sortBy, page, size)), page, size);
    }
}
