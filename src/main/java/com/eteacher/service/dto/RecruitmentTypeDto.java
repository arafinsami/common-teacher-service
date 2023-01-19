package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.RecruitmentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class RecruitmentTypeDto {

    private Long id;

    @NotBlank
    private String recruitmentTypeName;

    @NotBlank
    private String recruitmentTypeNameBn;

    @NotBlank
    private String description;

    @NotBlank
    private String descriptionBn;

    public RecruitmentType to() {
        RecruitmentType recruitmentType = new RecruitmentType();
        recruitmentType.setRecruitmentTypeName(recruitmentTypeName);
        recruitmentType.setRecruitmentTypeNameBn(recruitmentTypeNameBn);
        recruitmentType.setDescription(description);
        recruitmentType.setDescriptionBn(descriptionBn);
        return recruitmentType;
    }

    public void update(RecruitmentType recruitmentType) {
        recruitmentType.setRecruitmentTypeName(recruitmentTypeName);
        recruitmentType.setRecruitmentTypeNameBn(recruitmentTypeNameBn);
        recruitmentType.setDescription(description);
        recruitmentType.setDescriptionBn(descriptionBn);
    }
}
