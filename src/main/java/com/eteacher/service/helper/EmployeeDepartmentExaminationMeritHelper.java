package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.ntrca.EmployeeDepartmentalExamMerit;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeDepartmentalExamMeritProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.DEPARTMENTAL_EXAM_MERIT;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeDepartmentExaminationMeritHelper {

    private final ActiveUserContext context;

    public Function<EmployeeDepartmentalExamMeritProfile, EmployeeDepartmentalExamMerit> saveMeritProfile = profileConverter -> {
        EmployeeDepartmentalExamMerit examMerit = profileConverter.to();
        return examMerit;
    };

    public Function<EmployeeDepartmentalExamMeritProfile, EmployeeDepartmentalExamMerit> updateMeritProfile = profileConverter -> {
        EmployeeDepartmentalExamMerit examMerit = profileConverter.update();
        return examMerit;
    };


    public List<EmployeeDepartmentalExamMerit> save(List<EmployeeDepartmentalExamMeritProfile> profiles) {
        List<EmployeeDepartmentalExamMerit> lists = profiles
                .stream()
                .map(saveMeritProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeDepartmentalExamMerit> update(List<EmployeeDepartmentalExamMeritProfile> profiles) {
        List<EmployeeDepartmentalExamMerit> lists = profiles
                .stream()
                .map(updateMeritProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeDepartmentalExamMerit> delete(EmployeeDepartmentalExamMerit examMerit) {
        examMerit.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        examMerit.setRecordStatus(DELETED);
        return Arrays.asList(examMerit);
    }

    public EmployeeDepartmentalExamMerit findById(Long examMeritId, List<EmployeeDepartmentalExamMerit> lists) {
        for (EmployeeDepartmentalExamMerit merit : lists) {
            if (merit.getId().equals(examMeritId)) {
                return merit;
            }
        }
        return null;
    }

    public EmployeeDepartmentalExamMerit findExamMerit(Long examMeritId, Employee employee) {
        return Optional.ofNullable(findById(examMeritId, employee.getDepartmentalExamMerits()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(DEPARTMENTAL_EXAM_MERIT + examMeritId)
                );
    }
}
