package com.eteacher.service.helper;

import com.eteacher.service.entity.mpo.EmployeeMpoApplication;
import com.eteacher.service.entity.mpo.EmployeeMpoApplicationReviewer;
import com.eteacher.service.profile.EmployeeMpoApplicationProfile;
import com.eteacher.service.profile.EmployeeMpoApplicationReviewerProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.enums.RecordStatus.*;

@Component
@RequiredArgsConstructor
public class EmployeeMpoPerformanceEvaluationHelper {

    @Resource
    private ActiveUserContext context;

    public Function<EmployeeMpoApplicationProfile, EmployeeMpoApplication> saveApplication = r -> {
        EmployeeMpoApplication application = r.to();
        application.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        application.setRecordStatus(DRAFT);
        return application;
    };

    public Function<EmployeeMpoApplicationProfile, EmployeeMpoApplication> updateApplication = r -> {
        EmployeeMpoApplication application = r.to();
        application.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        application.setRecordStatus(ACTIVE);
        return application;
    };

    public Function<EmployeeMpoApplicationReviewerProfile, EmployeeMpoApplicationReviewer> saveReviewerProfile = r -> {
        EmployeeMpoApplicationReviewer reviewer = r.to();
        reviewer.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        reviewer.setRecordStatus(DRAFT);
        return reviewer;
    };

    public Function<EmployeeMpoApplicationReviewerProfile, EmployeeMpoApplicationReviewer> updateReviewerProfile = r -> {
        EmployeeMpoApplicationReviewer reviewer = r.to();
        reviewer.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        reviewer.setRecordStatus(ACTIVE);
        return reviewer;
    };

    public List<EmployeeMpoApplication> saveApplications(List<EmployeeMpoApplicationProfile> profiles) {
        List<EmployeeMpoApplication> applications = profiles.stream()
                .map(saveApplication)
                .collect(Collectors.toList());
        return applications;
    }

    public List<EmployeeMpoApplication> updateApplications(List<EmployeeMpoApplicationProfile> profiles) {
        List<EmployeeMpoApplication> applications = profiles.stream()
                .map(updateApplication)
                .collect(Collectors.toList());
        return applications;
    }

    public EmployeeMpoApplicationReviewer saveReviewer(EmployeeMpoApplicationReviewerProfile profile) {
        EmployeeMpoApplicationReviewer reviewer = saveReviewerProfile.apply(profile);
        return reviewer;
    }

    public EmployeeMpoApplicationReviewer updateReviewer(EmployeeMpoApplicationReviewerProfile profile) {
        EmployeeMpoApplicationReviewer reviewer = updateReviewerProfile.apply(profile);
        return reviewer;
    }

    public List<EmployeeMpoApplication> deleteOne(EmployeeMpoApplication application) {
        application.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        application.setRecordStatus(DELETED);
        return Arrays.asList(application);
    }
}
