package com.eteacher.service.helper;

import com.eteacher.service.entity.ntrca.Department;
import com.eteacher.service.entity.ntrca.DepartmentalExamination;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.DepartmentalExaminationProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.DEPARTMENTAL_EXAM;
import static com.eteacher.service.enums.RecordStatus.*;

@Component
@RequiredArgsConstructor
public class DepartmentHelper {

    @Resource
    private ActiveUserContext context;

    public void getSaveData(Department department) {
        department.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        department.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(Department department, RecordStatus status) {
        department.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        department.setRecordStatus(status);
    }

    public Function<DepartmentalExaminationProfile, DepartmentalExamination> saveDepartmentalExam = r -> {
        DepartmentalExamination examination = r.to();
        examination.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        examination.setRecordStatus(DRAFT);
        return examination;
    };

    public Function<DepartmentalExaminationProfile, DepartmentalExamination> updateDepartmentalExam = r -> {
        DepartmentalExamination examination = r.to();
        examination.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        examination.setRecordStatus(ACTIVE);
        return examination;
    };

    public List<DepartmentalExamination> save(List<DepartmentalExaminationProfile> profiles) {
        List<DepartmentalExamination> applications = profiles
                .stream()
                .map(saveDepartmentalExam)
                .collect(Collectors.toList());
        return applications;
    }

    public List<DepartmentalExamination> update(List<DepartmentalExaminationProfile> profiles) {
        List<DepartmentalExamination> applications = profiles
                .stream()
                .map(updateDepartmentalExam)
                .collect(Collectors.toList());
        return applications;
    }

    public List<DepartmentalExamination> delete(DepartmentalExamination examination) {
        examination.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        examination.setRecordStatus(DELETED);
        return Arrays.asList(examination);
    }

    public DepartmentalExamination findByExamId(Long examId, List<DepartmentalExamination> lists) {
        for (DepartmentalExamination examination : lists) {
            if (examination.getId().equals(examId)) {
                return examination;
            }
        }
        return null;
    }

    public DepartmentalExamination findExam(Long examId, Department department) {
        return Optional.ofNullable(findByExamId(examId, department.getExaminations()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(DEPARTMENTAL_EXAM + examId)
                );
    }
}
