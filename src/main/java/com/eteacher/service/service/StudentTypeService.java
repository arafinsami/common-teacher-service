package com.eteacher.service.service;

import com.eteacher.service.audit.StudentTypeAudit;
import com.eteacher.service.entity.commonteacher.EmployeeType;
import com.eteacher.service.entity.commonteacher.StudentType;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.GetListHelper;
import com.eteacher.service.helper.StudentTypeHelper;
import com.eteacher.service.repository.StudentTypeRepository;
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
public class StudentTypeService extends BaseService {

    private final StudentTypeRepository repository;

    private final ActionLogService actionLogService;

    private final StudentTypeHelper helper;

    private final EntityManager em;

    public Optional<StudentType> findByStudentTypeName(String studentTypeName) {
        Optional<StudentType> type = repository.findByStudentTypeName(studentTypeName);
        return type;
    }

    public Optional<StudentType> findByStudentTypeNameBn(String studentTypeNameBn) {
        Optional<StudentType> type = repository.findByStudentTypeNameBn(studentTypeNameBn);
        return type;
    }

    public Optional<StudentType> findByStudentTypeIdLegacy(String studentTypeIdLegacy) {
        Optional<StudentType> type = repository.findByStudentTypeIdLegacy(studentTypeIdLegacy);
        return type;
    }

    public Optional<StudentType> findById(Long id) {
        Optional<StudentType> type = repository.findById(id);
        return type;
    }

    public List<StudentType> findAll(String[] sortable, String sortBy) {
        return new GetListHelper<StudentType>(em, StudentType.class).findAll(sortable, sortBy);
    }

    @Transactional
    public StudentType save(StudentType studentType) {
        helper.getSaveData(studentType);
        StudentType s = repository.save(studentType);
        StudentTypeAudit audit = StudentTypeAudit.audit(s);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                STUDENT_TYPE_SAVE,
                objectToJson(audit)
        );
        return s;
    }

    @Transactional
    public StudentType update(StudentType studentType) {
        helper.getUpdateData(studentType, RecordStatus.ACTIVE);
        StudentType s = repository.save(studentType);
        StudentTypeAudit audit = StudentTypeAudit.audit(s);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                STUDENT_TYPE_UPDATE,
                objectToJson(audit)
        );
        return s;
    }

    @Transactional
    public StudentType delete(StudentType studentType) {
        helper.getUpdateData(studentType, RecordStatus.DELETED);
        StudentType s = repository.save(studentType);
        StudentTypeAudit audit = StudentTypeAudit.audit(s);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                STUDENT_TYPE_DELETE,
                objectToJson(audit)
        );
        return s;
    }
}
