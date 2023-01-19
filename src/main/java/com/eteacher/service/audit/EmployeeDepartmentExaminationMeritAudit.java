package com.eteacher.service.audit;

import com.eteacher.service.entity.ntrca.EmployeeDepartmentalExamMerit;
import lombok.Data;

@Data
public class EmployeeDepartmentExaminationMeritAudit {

    private Long id;

    private Integer meritPosition;

    public static EmployeeDepartmentExaminationMeritAudit audit(EmployeeDepartmentalExamMerit examMerit) {
        EmployeeDepartmentExaminationMeritAudit audit = new EmployeeDepartmentExaminationMeritAudit();
        audit.setId(examMerit.getId());
        audit.setMeritPosition(examMerit.getMeritPosition());
        return audit;
    }

}
