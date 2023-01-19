package com.eteacher.service.audit;

import com.eteacher.service.entity.ntrca.EmployeeDepartmentalExamMeritScore;
import lombok.Data;

@Data
public class EmployeeDepartmentalExamMeritScoreAudit {

    private Long id;

    private Double scoreAchieved;

    private Long examType;

    private Long subject;

    public static EmployeeDepartmentalExamMeritScoreAudit audit(EmployeeDepartmentalExamMeritScore meritScore) {
        EmployeeDepartmentalExamMeritScoreAudit audit = new EmployeeDepartmentalExamMeritScoreAudit();
        audit.setId(meritScore.getId());
        audit.setScoreAchieved(meritScore.getScoreAchieved());
        audit.setExamType(meritScore.getExamType());
        audit.setSubject(meritScore.getSubject());
        return audit;
    }
}
