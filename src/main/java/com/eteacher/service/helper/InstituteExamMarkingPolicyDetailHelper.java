package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicyDetail;
import com.eteacher.service.profile.InstituteExamMarkingPolicyDetailProfile;
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
public class InstituteExamMarkingPolicyDetailHelper {

    @Resource
    private ActiveUserContext context;

    public Function<InstituteExamMarkingPolicyDetailProfile, InstituteExamMarkingPolicyDetail> savePolicyProfile = converter -> {
        InstituteExamMarkingPolicyDetail policyDetail = converter.to();
        policyDetail.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return policyDetail;
    };

    public Function<InstituteExamMarkingPolicyDetailProfile, InstituteExamMarkingPolicyDetail> updatePolicyProfile = converter -> {
        InstituteExamMarkingPolicyDetail policyDetail = converter.update();
        policyDetail.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return policyDetail;
    };

    public List<InstituteExamMarkingPolicyDetail> savePolicy(List<InstituteExamMarkingPolicyDetailProfile> profiles) {
        List<InstituteExamMarkingPolicyDetail> lists = profiles.stream()
                .map(savePolicyProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<InstituteExamMarkingPolicyDetail> updatePolicy(List<InstituteExamMarkingPolicyDetailProfile> profiles) {
        List<InstituteExamMarkingPolicyDetail> lists = profiles.stream()
                .map(updatePolicyProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<InstituteExamMarkingPolicyDetail> deletePolicy(InstituteExamMarkingPolicyDetail policyDetail) {
        policyDetail.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        policyDetail.setRecordStatus(DELETED);
        return Arrays.asList(policyDetail);
    }

    public InstituteExamMarkingPolicyDetail findByPolicyId(Long policyId, List<InstituteExamMarkingPolicyDetail> lists) {
        for (InstituteExamMarkingPolicyDetail detail : lists) {
            if (detail.getId().equals(policyId)) {
                return detail;
            }
        }
        return null;
    }
}
