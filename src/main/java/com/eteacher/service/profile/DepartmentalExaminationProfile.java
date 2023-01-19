package com.eteacher.service.profile;

import com.eteacher.service.entity.ntrca.DepartmentalExamination;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentalExaminationProfile {

    private Long id;

    private Integer year;

    public DepartmentalExamination to() {
        DepartmentalExamination exam = new DepartmentalExamination();
        exam.setId(id);
        exam.setYear(year);
        return exam;
    }
}
