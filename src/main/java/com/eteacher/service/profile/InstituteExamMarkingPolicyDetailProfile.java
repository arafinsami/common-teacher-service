package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicyDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class InstituteExamMarkingPolicyDetailProfile {

    private Long id;

    @NotNull
    private Double percentageMarks;

    @NotNull
    private Long subjectId;

    @NotNull
    private Long examTermId;

    @NotNull
    private Long examTypeId;

    public InstituteExamMarkingPolicyDetail to() {
        InstituteExamMarkingPolicyDetail policyDetail = new InstituteExamMarkingPolicyDetail();
        policyDetail.setId(id);
        policyDetail.setPercentageMarks(percentageMarks);
        policyDetail.setSubjectId(subjectId);
        policyDetail.setExamTerm(examTermId);
        policyDetail.setExamType(examTypeId);
        policyDetail.setRecordStatus(DRAFT);
        return policyDetail;
    }

    public InstituteExamMarkingPolicyDetail update() {
        InstituteExamMarkingPolicyDetail policyDetail = new InstituteExamMarkingPolicyDetail();
        policyDetail.setId(id);
        policyDetail.setPercentageMarks(percentageMarks);
        policyDetail.setSubjectId(subjectId);
        policyDetail.setExamTerm(examTermId);
        policyDetail.setExamType(examTypeId);
        policyDetail.setRecordStatus(ACTIVE);
        return policyDetail;
    }
}
