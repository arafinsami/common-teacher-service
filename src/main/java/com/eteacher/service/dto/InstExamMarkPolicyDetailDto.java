package com.eteacher.service.dto;

import com.eteacher.service.profile.InstituteExamMarkingPolicyDetailProfile;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class InstExamMarkPolicyDetailDto {

    @NotNull
    @Min(1)
    private Long policyId;

    @Valid
    private List<InstituteExamMarkingPolicyDetailProfile> policyDetails;

}
