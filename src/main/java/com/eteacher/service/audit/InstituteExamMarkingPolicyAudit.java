package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicy;
import lombok.Data;

@Data
public class InstituteExamMarkingPolicyAudit {

    private Long id;

    private String remarks;

    private Long instituteId;

    public static InstituteExamMarkingPolicyAudit audit(InstituteExamMarkingPolicy instituteExamMarkingPolicy) {
        InstituteExamMarkingPolicyAudit audit = new InstituteExamMarkingPolicyAudit();
        audit.setId(instituteExamMarkingPolicy.getId());
        audit.setRemarks(instituteExamMarkingPolicy.getRemarks());
        audit.setInstituteId(instituteExamMarkingPolicy.getInstituteId());
        return audit;
    }
}
