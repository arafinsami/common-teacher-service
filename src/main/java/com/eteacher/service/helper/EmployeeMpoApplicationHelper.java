package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeeMpoApplicationEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeMpoApplication;
import com.eteacher.service.entity.mpo.EmployeeMpoApplicationEncloser;
import com.eteacher.service.entity.mpo.EmployeeMpoApplicationReviewer;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeMpoApplicationEncloserProfile;
import com.eteacher.service.profile.EmployeeMpoApplicationProfile;
import com.eteacher.service.profile.EmployeeMpoApplicationReviewerProfile;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.utils.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_MPO_APPLICATION;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_MPO_APPLICATION_REVIEWER;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeMpoApplicationHelper extends FileUpload {

    @Resource
    private ActiveUserContext context;

    @Resource
    private Environment env;

    public Function<EmployeeMpoApplicationProfile, EmployeeMpoApplication> saveApplication = r -> {
        EmployeeMpoApplication application = r.to();
        application.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return application;
    };

    public Function<EmployeeMpoApplicationReviewerProfile, EmployeeMpoApplicationReviewer> saveReviewerProfile = r -> {
        EmployeeMpoApplicationReviewer reviewer = r.to();
        reviewer.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return reviewer;
    };

    public Function<EmployeeMpoApplicationEncloserProfile, EmployeeMpoApplicationEncloser> saveEncloserProfiles = r -> {
        EmployeeMpoApplicationEncloser encloser = r.to();
        return encloser;
    };

    public Function<EmployeeMpoApplicationProfile, EmployeeMpoApplication> updateApplication = r -> {
        EmployeeMpoApplication application = r.update();
        application.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return application;
    };

    public Function<EmployeeMpoApplicationReviewerProfile, EmployeeMpoApplicationReviewer> updateReviewerProfile = r -> {
        EmployeeMpoApplicationReviewer reviewer = r.update();
        reviewer.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return reviewer;
    };

    public List<EmployeeMpoApplication> save(List<EmployeeMpoApplicationProfile> profiles) {
        List<EmployeeMpoApplication> applications = profiles
                .stream()
                .map(saveApplication)
                .collect(Collectors.toList());
        return applications;
    }

    public EmployeeMpoApplicationReviewer save(EmployeeMpoApplicationReviewerProfile profile) {
        EmployeeMpoApplicationReviewer reviewer = saveReviewerProfile.apply(profile);
        return reviewer;
    }

    public List<EmployeeMpoApplicationEncloser> save(MultipartFile[] files, EmployeeMpoApplicationEncloserDto dto) {
        List<EmployeeMpoApplicationEncloser> lists = dto.getProfiles()
                .stream()
                .map(saveEncloserProfiles)
                .collect(Collectors.toList());

        for (int index = 0; index < lists.size(); index++) {
            for (EmployeeMpoApplicationEncloser encloser : lists) {
                if (encloser.getEncloserUrl().equals(files[index].getOriginalFilename())) {
                    encloser.setEncloserUrl(
                            upload(files[index], env.getProperty("mpoApplicationUploadPath"))
                    );
                }
            }
        }
        return lists;
    }

    public List<EmployeeMpoApplication> update(List<EmployeeMpoApplicationProfile> profiles) {
        List<EmployeeMpoApplication> applications = profiles
                .stream()
                .map(updateApplication)
                .collect(Collectors.toList());
        return applications;
    }

    public EmployeeMpoApplicationReviewer update(EmployeeMpoApplicationReviewerProfile profile) {
        EmployeeMpoApplicationReviewer reviewer = updateReviewerProfile.apply(profile);
        return reviewer;
    }

    private EmployeeMpoApplication findByApplicationId(Long applicationId, List<EmployeeMpoApplication> lists) {
        for (EmployeeMpoApplication application : lists) {
            if (application.getId().equals(applicationId)) {
                return application;
            }
        }
        return null;
    }

    private EmployeeMpoApplicationReviewer findByReviewerId(Long reviewerId, List<EmployeeMpoApplicationReviewer> lists) {
        for (EmployeeMpoApplicationReviewer reviewer : lists) {
            if (reviewer.getId().equals(reviewerId)) {
                return reviewer;
            }
        }
        return null;
    }

    public EmployeeMpoApplication findApplication(Long applicationId, Employee employee) {
        return Optional.ofNullable(findByApplicationId(applicationId, employee.getMpoApplications()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_MPO_APPLICATION + applicationId)
                );
    }

    public EmployeeMpoApplicationReviewer findReviewer(Long reviewerId, EmployeeMpoApplication application) {
        return Optional.ofNullable(findByReviewerId(reviewerId, application.getReviewers()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_MPO_APPLICATION_REVIEWER + reviewerId)
                );
    }

    public EmployeeMpoApplication deleteOne(EmployeeMpoApplication application) {
        application.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        application.setRecordStatus(DELETED);
        return application;
    }

    public EmployeeMpoApplicationReviewer deleteOne(EmployeeMpoApplicationReviewer reviewer) {
        reviewer.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        reviewer.setRecordStatus(DELETED);
        return reviewer;
    }
}
