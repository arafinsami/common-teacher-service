package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicyDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstituteExamMarkingPolicyDetailResponse {

    private Long id;

    private Double percentageMarks;

    private Long subjectId;

    private Long examTermId;

    private Long examTypeId;

    public static InstituteExamMarkingPolicyDetailResponse response(InstituteExamMarkingPolicyDetail policyDetail) {
        InstituteExamMarkingPolicyDetailResponse response = new InstituteExamMarkingPolicyDetailResponse();
        response.setId(policyDetail.getId());
        response.setPercentageMarks(policyDetail.getPercentageMarks());
        response.setSubjectId(policyDetail.getSubjectId());
        response.setExamTermId(policyDetail.getExamTerm());
        response.setExamTypeId(policyDetail.getExamType());
        return response;
    }
}
