package com.eteacher.service.helper;

import com.eteacher.service.entity.job.JobCircular;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JobCircularHelper {

    private final ActiveUserContext context;

    public void getSaveData(JobCircular circular) {
        circular.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        circular.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(JobCircular circular, RecordStatus status) {
        circular.setRecordVersion(circular.getRecordVersion() + 1);
        circular.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        circular.setRecordStatus(status);
    }
}
