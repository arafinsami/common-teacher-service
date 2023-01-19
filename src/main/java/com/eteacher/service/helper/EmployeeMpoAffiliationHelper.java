package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeeMpoAffiliationEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeMpoAffiliation;
import com.eteacher.service.entity.mpo.EmployeeMpoAffiliationEncloser;
import com.eteacher.service.entity.mpo.EmployeeMpoAffiliationReviewer;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeMpoAffiliationEncloserProfile;
import com.eteacher.service.profile.EmployeeMpoAffiliationProfile;
import com.eteacher.service.profile.EmployeeMpoAffiliationReviewerProfile;
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

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_MPO_AFFILIATION;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_MPO_AFFILIATION_REVIEWER;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeMpoAffiliationHelper extends FileUpload {

    @Resource
    private ActiveUserContext context;

    @Resource
    private Environment env;

    public Function<EmployeeMpoAffiliationProfile, EmployeeMpoAffiliation> saveAffiliation = r -> {
        EmployeeMpoAffiliation affiliation = r.to();
        affiliation.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return affiliation;
    };

    public Function<EmployeeMpoAffiliationReviewerProfile, EmployeeMpoAffiliationReviewer> saveReviewerProfile = r -> {
        EmployeeMpoAffiliationReviewer reviewer = r.to();
        reviewer.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return reviewer;
    };

    public Function<EmployeeMpoAffiliationEncloserProfile, EmployeeMpoAffiliationEncloser> saveEncloserProfiles = r -> {
        EmployeeMpoAffiliationEncloser encloser = r.to();
        return encloser;
    };

    public Function<EmployeeMpoAffiliationProfile, EmployeeMpoAffiliation> updateAffiliation = r -> {
        EmployeeMpoAffiliation affiliation = r.update();
        affiliation.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return affiliation;
    };

    public Function<EmployeeMpoAffiliationReviewerProfile, EmployeeMpoAffiliationReviewer> updateReviewerProfile = r -> {
        EmployeeMpoAffiliationReviewer reviewer = r.update();
        reviewer.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return reviewer;
    };

    public List<EmployeeMpoAffiliation> save(List<EmployeeMpoAffiliationProfile> profiles) {
        List<EmployeeMpoAffiliation> affiliations = profiles
                .stream()
                .map(saveAffiliation)
                .collect(Collectors.toList());
        return affiliations;
    }

    public EmployeeMpoAffiliationReviewer save(EmployeeMpoAffiliationReviewerProfile profile) {
        EmployeeMpoAffiliationReviewer reviewer = saveReviewerProfile.apply(profile);
        return reviewer;
    }

    public List<EmployeeMpoAffiliationEncloser> save(MultipartFile[] files, EmployeeMpoAffiliationEncloserDto dto) {
        List<EmployeeMpoAffiliationEncloser> lists = dto.getProfiles()
                .stream()
                .map(saveEncloserProfiles)
                .collect(Collectors.toList());

        for (int index = 0; index < lists.size(); index++) {
            for (EmployeeMpoAffiliationEncloser encloser : lists) {
                if (encloser.getEncloserUrl().equals(files[index].getOriginalFilename())) {
                    encloser.setEncloserUrl(
                            upload(files[index], env.getProperty("affiliationUploadPath"))
                    );
                }
            }
        }
        return lists;
    }

    public EmployeeMpoAffiliationReviewer update(EmployeeMpoAffiliationReviewerProfile profile) {
        EmployeeMpoAffiliationReviewer reviewer = updateReviewerProfile.apply(profile);
        return reviewer;
    }

    public List<EmployeeMpoAffiliation> update(List<EmployeeMpoAffiliationProfile> profiles) {
        List<EmployeeMpoAffiliation> affiliations = profiles
                .stream()
                .map(updateAffiliation)
                .collect(Collectors.toList());
        return affiliations;
    }

    public EmployeeMpoAffiliation deleteOne(EmployeeMpoAffiliation affiliation) {
        affiliation.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        affiliation.setRecordStatus(DELETED);
        return affiliation;
    }

    public EmployeeMpoAffiliationReviewer deleteOne(EmployeeMpoAffiliationReviewer reviewer) {
        reviewer.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        reviewer.setRecordStatus(DELETED);
        return reviewer;
    }

    private EmployeeMpoAffiliation findByAffiliationId(Long affiliationId, List<EmployeeMpoAffiliation> lists) {
        for (EmployeeMpoAffiliation affiliation : lists) {
            if (affiliation.getId().equals(affiliationId)) {
                return affiliation;
            }
        }
        return null;
    }

    private EmployeeMpoAffiliationReviewer findByReviewerId(Long reviewerId, List<EmployeeMpoAffiliationReviewer> lists) {
        for (EmployeeMpoAffiliationReviewer reviewer : lists) {
            if (reviewer.getId().equals(reviewerId)) {
                return reviewer;
            }
        }
        return null;
    }

    public EmployeeMpoAffiliation findAffiliation(Long affiliationId, Employee employee) {
        return Optional.ofNullable(findByAffiliationId(affiliationId, employee.getMpoAffiliations()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_MPO_AFFILIATION + affiliationId)
                );
    }

    public EmployeeMpoAffiliationReviewer findReviewer(Long reviewerId, EmployeeMpoAffiliation affiliation) {
        return Optional.ofNullable(findByReviewerId(reviewerId, affiliation.getReviewers()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_MPO_AFFILIATION_REVIEWER + reviewerId)
                );
    }

}
