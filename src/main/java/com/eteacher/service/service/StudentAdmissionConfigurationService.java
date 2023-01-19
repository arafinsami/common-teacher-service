package com.eteacher.service.service;

import com.eteacher.service.audit.StudentAdmissionConfigurationAudit;
import com.eteacher.service.entity.commonteacher.StudentAdmissionConfiguration;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.StudentAdmissionConfigurationHelper;
import com.eteacher.service.repository.StudentAdmissionConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class StudentAdmissionConfigurationService {

    private final StudentAdmissionConfigurationRepository repository;

    private final ActionLogService actionLogService;

    private final StudentAdmissionConfigurationHelper helper;

    public Optional<StudentAdmissionConfiguration> findByStudentAdmissionConfigurationName(String configurationName) {
        Optional<StudentAdmissionConfiguration> configuration = repository.findByConfigurationName(configurationName);
        return configuration;
    }

    public Optional<StudentAdmissionConfiguration> findBySvgId(Long svgId) {
        Optional<StudentAdmissionConfiguration> configuration = repository.findBySvgId(svgId);
        return configuration;
    }

    public Optional<StudentAdmissionConfiguration> findByClassId(Long classId) {
        Optional<StudentAdmissionConfiguration> configuration = repository.findByClassId(classId);
        return configuration;
    }

    public Optional<StudentAdmissionConfiguration> findByInstituteId(Long instituteId) {
        Optional<StudentAdmissionConfiguration> configuration = repository.findByInstituteId(instituteId);
        return configuration;
    }

    public Optional<StudentAdmissionConfiguration> findById(Long id) {
        Optional<StudentAdmissionConfiguration> configuration = repository.findById(id);
        return configuration;
    }

    @Transactional
    public StudentAdmissionConfiguration save(StudentAdmissionConfiguration studentAdmissionConfiguration) {
        StudentAdmissionConfiguration s = repository.save(studentAdmissionConfiguration);
        StudentAdmissionConfigurationAudit audit = StudentAdmissionConfigurationAudit.audit(s);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                STUDENT_ADMISSION_CONFIGURAION_SAVE,
                objectToJson(audit)
        );
        return s;
    }

    @Transactional
    public StudentAdmissionConfiguration update(StudentAdmissionConfiguration studentAdmissionConfiguration) {
        StudentAdmissionConfiguration s = repository.save(studentAdmissionConfiguration);
        StudentAdmissionConfigurationAudit audit = StudentAdmissionConfigurationAudit.audit(s);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                STUDENT_ADMISSION_CONFIGURAION_UPDATE,
                objectToJson(audit)
        );
        return s;
    }

    public StudentAdmissionConfiguration delete(StudentAdmissionConfiguration studentAdmissionConfiguration) {
        helper.getUpdateData(studentAdmissionConfiguration, RecordStatus.DELETED);
        StudentAdmissionConfiguration s = repository.save(studentAdmissionConfiguration);
        StudentAdmissionConfigurationAudit audit = StudentAdmissionConfigurationAudit.audit(s);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                STUDENT_ADMISSION_CONFIGURAION_DELETE,
                objectToJson(audit)
        );
        return s;
    }
}
