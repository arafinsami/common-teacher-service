package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.EmployeeContactDetail;
import com.eteacher.service.profile.EmployeeContactDetailProfile;
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
public class EmployeeContactDetailHelper {

    @Resource
    private ActiveUserContext context;

    public Function<EmployeeContactDetailProfile, EmployeeContactDetail> saveProfile = converter -> {
        EmployeeContactDetail detail = converter.to();
        detail.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return detail;
    };

    public Function<EmployeeContactDetailProfile, EmployeeContactDetail> updateProfile = converter -> {
        EmployeeContactDetail detail = converter.update();
        detail.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return detail;
    };

    public List<EmployeeContactDetail> save(List<EmployeeContactDetailProfile> profiles) {
        List<EmployeeContactDetail> lists = profiles.stream()
                .map(saveProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeContactDetail> update(List<EmployeeContactDetailProfile> profiles) {
        List<EmployeeContactDetail> lists = profiles.stream()
                .map(updateProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeContactDetail> deleteOne(EmployeeContactDetail detail) {
        detail.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        detail.setRecordStatus(DELETED);
        return Arrays.asList(detail);
    }

    public EmployeeContactDetail findByContactId(Long contactId, List<EmployeeContactDetail> contactDetails) {
        for (EmployeeContactDetail contactDetail : contactDetails) {
            if (contactDetail.getId().equals(contactId)) {
                return contactDetail;
            }
        }
        return null;
    }
}
