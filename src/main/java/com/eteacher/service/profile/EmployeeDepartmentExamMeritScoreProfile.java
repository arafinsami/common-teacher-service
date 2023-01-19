package com.eteacher.service.profile;

import com.eteacher.service.entity.ntrca.EmployeeDepartmentalExamMeritScore;
import lombok.Data;

import javax.validation.constraints.NotNull;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
public class EmployeeDepartmentExamMeritScoreProfile {

    private Long id;

    @NotNull
    private Double scoreAchieved;

    @NotNull
    private Long examType;

    @NotNull
    private Long subject;

    public EmployeeDepartmentalExamMeritScore to() {
        EmployeeDepartmentalExamMeritScore meritScore = new EmployeeDepartmentalExamMeritScore();
        meritScore.setScoreAchieved(scoreAchieved);
        meritScore.setExamType(examType);
        meritScore.setSubject(subject);
        meritScore.setRecordStatus(DRAFT);
        return meritScore;
    }

    public EmployeeDepartmentalExamMeritScore update() {
        EmployeeDepartmentalExamMeritScore meritScore = new EmployeeDepartmentalExamMeritScore();
        meritScore.setId(id);
        meritScore.setScoreAchieved(scoreAchieved);
        meritScore.setExamType(examType);
        meritScore.setSubject(subject);
        meritScore.setRecordStatus(ACTIVE);
        return meritScore;
    }
}
