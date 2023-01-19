package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.EmployeeJobExperienceDetail;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.profile.EmployeeJobExperienceDetailProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeJobExperienceDetailHelper {

    private final ActiveUserContext context;

    public void getSaveData(List<EmployeeJobExperienceDetail> details) {
        details.stream().forEach(p -> {
            p.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
            p.setRecordStatus(RecordStatus.DRAFT);
        });
    }

    public void getUpdateData(List<EmployeeJobExperienceDetail> details, RecordStatus status) {
        details.stream().forEach(p -> {
            p.setRecordVersion(p.getRecordVersion() + 1);
            p.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
            p.setRecordStatus(status);
        });
    }

    public Function<EmployeeJobExperienceDetailProfile, EmployeeJobExperienceDetail> jobExpDetail = profileConverter -> {
        EmployeeJobExperienceDetail details = profileConverter.to(false);
        return details;
    };

    public Function<EmployeeJobExperienceDetailProfile, EmployeeJobExperienceDetail> deleteJobExpDetail = profileConverter -> {
        EmployeeJobExperienceDetail details = profileConverter.to(true);
        return details;
    };

    public List<EmployeeJobExperienceDetail> getJobExpDetail(List<EmployeeJobExperienceDetailProfile> profiles, Boolean doDelete) {
        Function<EmployeeJobExperienceDetailProfile, EmployeeJobExperienceDetail> mapConverter = doDelete ? deleteJobExpDetail : jobExpDetail;
        List<EmployeeJobExperienceDetail> details = profiles.stream()
                .map(mapConverter)
                .collect(Collectors.toList());
        return details;
    }

}
