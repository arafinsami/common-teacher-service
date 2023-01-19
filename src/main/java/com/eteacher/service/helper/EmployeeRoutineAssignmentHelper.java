package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.EmployeeRoutineAssignment;
import com.eteacher.service.profile.EmployeeRoutineAssignmentProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeRoutineAssignmentHelper {

    @Resource
    private ActiveUserContext context;

    public Function<EmployeeRoutineAssignmentProfile, EmployeeRoutineAssignment> saveProfile = r -> {
        EmployeeRoutineAssignment application = r.to();
        application.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return application;
    };

    public Function<EmployeeRoutineAssignmentProfile, EmployeeRoutineAssignment> updateProfile = r -> {
        EmployeeRoutineAssignment assignment = r.update();
        assignment.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return assignment;
    };

    public List<EmployeeRoutineAssignment> save(List<EmployeeRoutineAssignmentProfile> profiles) {
        List<EmployeeRoutineAssignment> applications = profiles.stream()
                .map(saveProfile)
                .collect(Collectors.toList());
        return applications;
    }

    public List<EmployeeRoutineAssignment> update(List<EmployeeRoutineAssignmentProfile> profiles) {
        List<EmployeeRoutineAssignment> applications = profiles.stream()
                .map(updateProfile)
                .collect(Collectors.toList());
        return applications;
    }

    public List<EmployeeRoutineAssignment> deleteOne(EmployeeRoutineAssignment assignment) {
        assignment.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        assignment.setRecordStatus(DELETED);
        return Arrays.asList(assignment);
    }

    public EmployeeRoutineAssignment findByAssignmentId(Long assignmentId, List<EmployeeRoutineAssignment> lists) {
        for (EmployeeRoutineAssignment assignment : lists) {
            if (assignment.getId().equals(assignmentId)) {
                return assignment;
            }
        }
        return null;
    }

}
