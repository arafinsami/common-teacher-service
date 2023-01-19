package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicy;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class InstituteExamMarkingPolicyHelper {

    @Resource
    private ActiveUserContext context;

    public void getSaveData(InstituteExamMarkingPolicy policy) {
        policy.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        policy.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(InstituteExamMarkingPolicy policy, RecordStatus status) {
        policy.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        policy.setRecordStatus(status);
    }
}