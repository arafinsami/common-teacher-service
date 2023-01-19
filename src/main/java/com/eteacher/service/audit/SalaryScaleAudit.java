package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.SalaryScale;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SalaryScaleAudit {

    private Long id;

    private String scaleName;

    private String scaleNameBn;

    private Boolean isNationalScale;

    private Long instituteType;

    public static SalaryScaleAudit audit(SalaryScale salaryScale) {
        SalaryScaleAudit audit = new SalaryScaleAudit();
        audit.setId(salaryScale.getId());
        audit.setScaleName(salaryScale.getSalaryScaleName());
        audit.setScaleNameBn(salaryScale.getSalaryScaleNameBn());
        audit.setIsNationalScale(salaryScale.getIsNationalScale());
        audit.setInstituteType(salaryScale.getInstituteType());
        return audit;
    }
}
