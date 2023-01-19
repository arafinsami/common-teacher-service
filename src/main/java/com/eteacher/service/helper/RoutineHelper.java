package com.eteacher.service.helper;


import com.eteacher.service.entity.commonteacher.Routine;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoutineHelper {

    private final ActiveUserContext context;

    public void getSaveData(Routine routine) {
        routine.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        routine.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(Routine routine, RecordStatus status) {
        routine.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        routine.setRecordStatus(status);
        routine.setRecordVersion(routine.getRecordVersion() + 1);
    }
}
