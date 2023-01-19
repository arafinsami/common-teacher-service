package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.EmployeeRoutineAssignment;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeeRoutineAssignmentsHelper {

    private final ActiveUserContext context;

    public void getSaveData(List<EmployeeRoutineAssignment> assignments) {
        assignments.stream().forEach(p -> {
            p.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
            p.setRecordStatus(RecordStatus.DRAFT);
        });
    }

    public void getUpdateData(List<EmployeeRoutineAssignment> assignments, RecordStatus status) {
        assignments.stream().forEach(p -> {
            p.setRecordVersion(p.getRecordVersion() + 1);
            p.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
            p.setRecordStatus(status);
        });
    }
}
