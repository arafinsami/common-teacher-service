package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.RecruitmentType;
import lombok.Data;

@Data
public class RecruitmentTypeAudit {

    private Long id;

    private String recruitmentTypeName;

    private String recruitmentTypeNameBn;

    private String description;

    private String descriptionBn;

    public static RecruitmentTypeAudit audit(RecruitmentType recruitmentType) {
        RecruitmentTypeAudit audit = new RecruitmentTypeAudit();
        audit.setId(recruitmentType.getId());
        audit.setRecruitmentTypeName(recruitmentType.getRecruitmentTypeName());
        audit.setRecruitmentTypeNameBn(recruitmentType.getRecruitmentTypeNameBn());
        audit.setDescription(recruitmentType.getDescription());
        audit.setDescriptionBn(recruitmentType.getDescriptionBn());
        return audit;

    }
}
