package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.RecruitmentType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecruitmentTypeResponse {

    private Long id;

    private String recruitmentTypeName;

    private String recruitmentTypeNameBn;

    private String description;

    private String descriptionBn;

    public static RecruitmentTypeResponse response(RecruitmentType recruitmentType) {
        RecruitmentTypeResponse response = new RecruitmentTypeResponse();
        response.setId(recruitmentType.getId());
        response.setRecruitmentTypeName(recruitmentType.getRecruitmentTypeName());
        response.setRecruitmentTypeNameBn(recruitmentType.getRecruitmentTypeNameBn());
        response.setDescription(recruitmentType.getDescription());
        response.setDescriptionBn(recruitmentType.getDescriptionBn());
        return response;
    }
}
