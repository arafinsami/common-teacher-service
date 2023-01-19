package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicy;
import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicyDetail;
import com.eteacher.service.profile.InstExamMarkPolicyDetailProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.utils.StringUtils.isNotEmpty;

@Data
@NoArgsConstructor
public class InstExamMarkPolicyResponse {

    private Long id;

    private String remarks;

    private Long instituteId;

    private List<InstExamMarkPolicyDetailProfile> profiles;

    public static InstExamMarkPolicyResponse response(InstituteExamMarkingPolicy instituteExamMarkingPolicy) {
        InstExamMarkPolicyResponse response = new InstExamMarkPolicyResponse();
        response.setId(instituteExamMarkingPolicy.getId());
        response.setRemarks(instituteExamMarkingPolicy.getRemarks());
        response.setInstituteId(instituteExamMarkingPolicy.getInstituteId());
        response.setProfiles(getPolicyDetail(instituteExamMarkingPolicy.getPolicyDetails()));
        return response;
    }

    public static Function<InstituteExamMarkingPolicyDetail, InstExamMarkPolicyDetailProfile> profile = p -> {
        InstExamMarkPolicyDetailProfile detail = new InstExamMarkPolicyDetailProfile();
        detail.setId(p.getId());
        detail.setPercentageMarks(p.getPercentageMarks());
        detail.setSubjectId(p.getSubjectId());
        detail.setExamTerm(p.getExamTerm());
        detail.setExamType(p.getExamType());
        return detail;
    };

    public static List<InstExamMarkPolicyDetailProfile> getPolicyDetail(
            List<InstituteExamMarkingPolicyDetail> profiles) {
        List<InstExamMarkPolicyDetailProfile> details = new ArrayList<>();
        if (isNotEmpty(profiles)) {
            details = profiles.stream().map(profile).collect(Collectors.toList());
        }
        return details;
    }
}
