package com.eteacher.service.audit;

import com.eteacher.service.entity.ntrca.DepartmentalExamination;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentalExaminationAudit {

    private Long id;

    private Integer year;

    public static DepartmentalExaminationAudit audit(DepartmentalExamination examination) {
        DepartmentalExaminationAudit audit = new DepartmentalExaminationAudit();
        audit.setId(examination.getId());
        audit.setYear(examination.getYear());
        return audit;
    }
}
