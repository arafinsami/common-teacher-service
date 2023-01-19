package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluation;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class PerformanceEvaluationHelper {

    private final ActiveUserContext context;

    public void getSaveData(PerformanceEvaluation performanceEvaluation, Long previousId) {
        performanceEvaluation.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        performanceEvaluation.setRecordStatus(RecordStatus.DRAFT);
        performanceEvaluation.setRecordId(StringUtils.getRecordId(previousId));
    }

    public void getUpdateData(PerformanceEvaluation performanceEvaluation) {
        performanceEvaluation.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        performanceEvaluation.setRecordStatus(RecordStatus.ACTIVE);
        performanceEvaluation.setRecordVersion(performanceEvaluation.getRecordVersion() + 1);
    }
}
