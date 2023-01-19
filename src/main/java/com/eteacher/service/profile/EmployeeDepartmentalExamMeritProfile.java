package com.eteacher.service.profile;

import com.eteacher.service.entity.ntrca.EmployeeDepartmentalExamMerit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeDepartmentalExamMeritProfile {

    private Long id;

    @NotNull
    private Integer meritPosition;

    public EmployeeDepartmentalExamMerit to() {
        EmployeeDepartmentalExamMerit examMerit = new EmployeeDepartmentalExamMerit();
        examMerit.setMeritPosition(meritPosition);
        examMerit.setRecordStatus(DRAFT);
        return examMerit;
    }

    public EmployeeDepartmentalExamMerit update() {
        EmployeeDepartmentalExamMerit examMerit = new EmployeeDepartmentalExamMerit();
        examMerit.setId(id);
        examMerit.setMeritPosition(meritPosition);
        examMerit.setRecordStatus(ACTIVE);
        return examMerit;
    }
}
