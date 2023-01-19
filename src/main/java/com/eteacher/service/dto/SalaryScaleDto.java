package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.SalaryScale;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class SalaryScaleDto {

    private Long id;

    @NotBlank
    private String scaleName;

    @NotBlank
    private String scaleNameBn;

    @NotNull
    private Boolean isNationalScale;

    @NotNull
    private Long instituteType;

    public SalaryScale to() {
        SalaryScale salaryScale = new SalaryScale();
        salaryScale.setSalaryScaleName(scaleName);
        salaryScale.setSalaryScaleNameBn(scaleNameBn);
        salaryScale.setIsNationalScale(isNationalScale);
        salaryScale.setInstituteType(instituteType);
        return salaryScale;
    }

    public void update(SalaryScale salaryScale) {
        salaryScale.setSalaryScaleName(scaleName);
        salaryScale.setSalaryScaleNameBn(scaleNameBn);
        salaryScale.setIsNationalScale(isNationalScale);
        salaryScale.setInstituteType(instituteType);
    }
}
