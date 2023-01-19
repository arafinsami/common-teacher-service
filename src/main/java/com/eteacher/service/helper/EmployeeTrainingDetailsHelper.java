package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.EmployeeTrainingDetail;
import com.eteacher.service.profile.EmployeeTrainingDetailsProfile;
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
public class EmployeeTrainingDetailsHelper {

    @Resource
    private ActiveUserContext context;

    public Function<EmployeeTrainingDetailsProfile, EmployeeTrainingDetail> saveProfile = r -> {
        EmployeeTrainingDetail detail = r.to();
        detail.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return detail;
    };

    public Function<EmployeeTrainingDetailsProfile, EmployeeTrainingDetail> updateProfile = r -> {
        EmployeeTrainingDetail detail = r.update();
        detail.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return detail;
    };

    public List<EmployeeTrainingDetail> save(List<EmployeeTrainingDetailsProfile> profiles) {
        List<EmployeeTrainingDetail> details = profiles.stream()
                .map(saveProfile)
                .collect(Collectors.toList());
        return details;
    }

    public List<EmployeeTrainingDetail> update(List<EmployeeTrainingDetailsProfile> profiles) {
        List<EmployeeTrainingDetail> details = profiles.stream()
                .map(updateProfile)
                .collect(Collectors.toList());
        return details;
    }

    public List<EmployeeTrainingDetail> deleteOne(EmployeeTrainingDetail assignment) {
        assignment.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        assignment.setRecordStatus(DELETED);
        return Arrays.asList(assignment);
    }

    public EmployeeTrainingDetail findByTrainingId(Long trainingId, List<EmployeeTrainingDetail> lists) {
        for (EmployeeTrainingDetail trainingDetail : lists) {
            if (trainingDetail.getId().equals(trainingId)) {
                return trainingDetail;
            }
        }
        return null;
    }
}
