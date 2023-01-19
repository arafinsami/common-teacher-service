package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.RecruitmentType;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RecruitmentTypeHelper {

  private final ActiveUserContext context;

  public void getSaveData(RecruitmentType recruitmentType) {
    recruitmentType.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
    recruitmentType.setRecordStatus(RecordStatus.DRAFT);
  }

  public void getUpdateData(RecruitmentType recruitmentType, RecordStatus status) {
    recruitmentType.setRecordVersion(recruitmentType.getRecordVersion() + 1);
    recruitmentType.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
    recruitmentType.setRecordStatus(status);
  }
}
