package com.eteacher.service.response;

import com.eteacher.service.entity.ntrca.DepartmentalExamination;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentalExaminationResponse {

    private Long id;

    private Integer year;

    public static DepartmentalExaminationResponse response(DepartmentalExamination examination) {
        DepartmentalExaminationResponse response = new DepartmentalExaminationResponse();
        response.setId(examination.getId());
        response.setYear(examination.getYear());
        return response;
    }
}
