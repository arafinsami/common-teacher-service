package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.EmployeeEducationalQualification;
import com.eteacher.service.profile.EmployeeEducationalQualificationProfile;
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
public class EmployeeEducationalQualificationHelper {

    @Resource
    private ActiveUserContext context;

    public Function<EmployeeEducationalQualificationProfile, EmployeeEducationalQualification> saveProfile = converter -> {
        EmployeeEducationalQualification contactDetail = converter.to();
        contactDetail.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));

        return contactDetail;
    };

    public Function<EmployeeEducationalQualificationProfile, EmployeeEducationalQualification> updateProfile = converter -> {
        EmployeeEducationalQualification contactDetail = converter.to();
        contactDetail.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return contactDetail;
    };

    public List<EmployeeEducationalQualification> save(List<EmployeeEducationalQualificationProfile> profiles) {
        List<EmployeeEducationalQualification> lists = profiles.stream()
                .map(saveProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeEducationalQualification> update(List<EmployeeEducationalQualificationProfile> profiles) {
        List<EmployeeEducationalQualification> lists = profiles.stream()
                .map(updateProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeEducationalQualification> deleteOne(EmployeeEducationalQualification qualification) {
        qualification.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        qualification.setRecordStatus(DELETED);
        return Arrays.asList(qualification);
    }

    public EmployeeEducationalQualification findByQualificationId(Long qualificationId, List<EmployeeEducationalQualification> qualifications) {
        for (EmployeeEducationalQualification qualification : qualifications) {
            if (qualification.getId().equals(qualificationId)) {
                return qualification;
            }
        }
        return null;
    }
}
