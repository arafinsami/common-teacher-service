package com.eteacher.service.response;

import com.eteacher.service.entity.ntrca.EmployeeDepartmentalExamMeritScore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDepartmentalExamMeritScoreResponse {

    private Long id;

    private Double scoreAchieved;

    private Long examType;

    private Long subject;

    public static EmployeeDepartmentalExamMeritScoreResponse response(EmployeeDepartmentalExamMeritScore score) {
        EmployeeDepartmentalExamMeritScoreResponse response = new EmployeeDepartmentalExamMeritScoreResponse();
        response.setId(score.getId());
        response.setScoreAchieved(score.getScoreAchieved());
        response.setExamType(score.getExamType());
        response.setSubject(score.getSubject());
        return response;
    }
}
