package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicyDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstituteExamMarkingPolicyDetailAudit {

    private Long id;

    private Double percentageMarks;

    private Long subjectId;

    private Long examTermId;

    private Long examTypeId;

    public static InstituteExamMarkingPolicyDetailAudit audit(InstituteExamMarkingPolicyDetail instExamMarkPolicyDetail) {
        InstituteExamMarkingPolicyDetailAudit audit = new InstituteExamMarkingPolicyDetailAudit();
        audit.setId(instExamMarkPolicyDetail.getId());
        audit.setPercentageMarks(instExamMarkPolicyDetail.getPercentageMarks());
        audit.setSubjectId(instExamMarkPolicyDetail.getSubjectId());
        audit.setExamTermId(instExamMarkPolicyDetail.getExamTerm());
        audit.setExamTypeId(instExamMarkPolicyDetail.getExamType());
        return audit;
    }
}
