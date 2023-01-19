package com.eteacher.service.response;

import com.eteacher.service.entity.ntrca.EmployeeDepartmentalExamMerit;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDepartmentalExamMeritResponse {

    private Long id;

    private Integer meritPosition;

    public static EmployeeDepartmentalExamMeritResponse response(EmployeeDepartmentalExamMerit merit) {
        EmployeeDepartmentalExamMeritResponse response = new EmployeeDepartmentalExamMeritResponse();
        response.setId(merit.getId());
        response.setMeritPosition(merit.getMeritPosition());
        return response;
    }
}
