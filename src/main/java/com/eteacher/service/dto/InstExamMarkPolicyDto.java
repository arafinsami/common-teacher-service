package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicy;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class InstExamMarkPolicyDto {

    private Long id;

    @NotBlank
    private String remarks;

    @NotNull
    private Long instituteId;

    public InstituteExamMarkingPolicy to() {
        InstituteExamMarkingPolicy policy = new InstituteExamMarkingPolicy();
        policy.setRemarks(remarks);
        policy.setInstituteId(instituteId);
        return policy;
    }

    public void update(InstituteExamMarkingPolicy policy) {
        policy.setRemarks(remarks);
        policy.setInstituteId(instituteId);
    }
}
