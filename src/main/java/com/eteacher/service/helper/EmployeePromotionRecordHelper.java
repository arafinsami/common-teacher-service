package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.EmployeePromotionRecord;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.profile.EmployeePromotionRecordProfile;
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
public class EmployeePromotionRecordHelper {

    @Resource
    private ActiveUserContext context;

    public void getSaveData(List<EmployeePromotionRecord> records) {
        records.stream().forEach(p -> {
            p.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
            p.setRecordStatus(RecordStatus.DRAFT);
        });
    }

    public void getUpdateData(List<EmployeePromotionRecord> records, RecordStatus status) {
        records.stream().forEach(p -> {
            p.setRecordVersion(p.getRecordVersion() + 1);
            p.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
            p.setRecordStatus(status);
        });
    }

    public Function<EmployeePromotionRecordProfile, EmployeePromotionRecord> saveProfile = r -> {
        EmployeePromotionRecord application = r.to();
        application.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return application;
    };

    public Function<EmployeePromotionRecordProfile, EmployeePromotionRecord> updateProfile = r -> {
        EmployeePromotionRecord assignment = r.update();
        assignment.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return assignment;
    };

    public List<EmployeePromotionRecord> save(List<EmployeePromotionRecordProfile> profiles) {
        List<EmployeePromotionRecord> applications = profiles.stream()
                .map(saveProfile)
                .collect(Collectors.toList());
        return applications;
    }

    public List<EmployeePromotionRecord> update(List<EmployeePromotionRecordProfile> profiles) {
        List<EmployeePromotionRecord> applications = profiles.stream()
                .map(updateProfile)
                .collect(Collectors.toList());
        return applications;
    }

    public List<EmployeePromotionRecord> deleteOne(EmployeePromotionRecord assignment) {
        assignment.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        assignment.setRecordStatus(DELETED);
        return Arrays.asList(assignment);
    }

    public EmployeePromotionRecord findByRecordId(Long recordId, List<EmployeePromotionRecord> lists) {
        for (EmployeePromotionRecord record : lists) {
            if (record.getId().equals(recordId)) {
                return record;
            }
        }
        return null;
    }
}
