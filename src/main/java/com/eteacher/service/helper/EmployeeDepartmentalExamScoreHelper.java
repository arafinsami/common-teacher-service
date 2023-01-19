package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.ntrca.EmployeeDepartmentalExamMeritScore;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeDepartmentExamMeritScoreProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.DEPARTMENTAL_EXAM_MERIT_SCORE;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeDepartmentalExamScoreHelper {

    private final ActiveUserContext context;

    public Function<EmployeeDepartmentExamMeritScoreProfile, EmployeeDepartmentalExamMeritScore> saveMeritProfile = p -> {
        EmployeeDepartmentalExamMeritScore score = p.to();
        return score;
    };

    public Function<EmployeeDepartmentExamMeritScoreProfile, EmployeeDepartmentalExamMeritScore> updateMeritProfile = p -> {
        EmployeeDepartmentalExamMeritScore score = p.update();
        return score;
    };

    public List<EmployeeDepartmentalExamMeritScore> save(List<EmployeeDepartmentExamMeritScoreProfile> profiles) {
        List<EmployeeDepartmentalExamMeritScore> lists = profiles
                .stream()
                .map(saveMeritProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeDepartmentalExamMeritScore> update(List<EmployeeDepartmentExamMeritScoreProfile> profiles) {
        List<EmployeeDepartmentalExamMeritScore> lists = profiles
                .stream()
                .map(updateMeritProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeDepartmentalExamMeritScore> delete(EmployeeDepartmentalExamMeritScore examMerit) {
        examMerit.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        examMerit.setRecordStatus(DELETED);
        return Arrays.asList(examMerit);
    }

    public EmployeeDepartmentalExamMeritScore findById(Long examMeritId, List<EmployeeDepartmentalExamMeritScore> lists) {
        for (EmployeeDepartmentalExamMeritScore merit : lists) {
            if (merit.getId().equals(examMeritId)) {
                return merit;
            }
        }
        return null;
    }

    public EmployeeDepartmentalExamMeritScore findExamMeritScore(Long examMeritId, Employee employee) {
        return Optional.ofNullable(findById(examMeritId, employee.getDepartmentalExamMeritScores()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(DEPARTMENTAL_EXAM_MERIT_SCORE + examMeritId)
                );
    }
}
