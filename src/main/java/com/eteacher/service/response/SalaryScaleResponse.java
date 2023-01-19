package com.eteacher.service.response;


import com.eteacher.service.entity.commonteacher.SalaryScale;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SalaryScaleResponse {

    private Long id;

    private String scaleName;

    private String scaleNameBn;

    private Boolean isNationalScale;

    private Long instituteType;

    public static SalaryScaleResponse response(SalaryScale salaryScale) {
        SalaryScaleResponse response = new SalaryScaleResponse();
        response.setId(salaryScale.getId());
        response.setScaleName(salaryScale.getSalaryScaleName());
        response.setScaleNameBn(salaryScale.getSalaryScaleNameBn());
        response.setIsNationalScale(salaryScale.getIsNationalScale());
        response.setInstituteType(salaryScale.getInstituteType());
        return response;
    }
}
