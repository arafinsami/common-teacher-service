package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.StudentAdmissionConfiguration;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
public class StudentAdmissionConfigurationDto {

    private Long id;

    @NotBlank
    private String configurationName;

    private String configurationNameBn;

    @NotNull
    private Long svgId;

    private Double minGradePointGeneral;

    private Double minGradePointInternal;

    private Double minGradePointSpecial;

    @NotNull
    private Long classId;

    @NotNull
    private Long instituteId;

    public StudentAdmissionConfiguration to() {
        StudentAdmissionConfiguration studentAdmissionConfiguration = new StudentAdmissionConfiguration();
        studentAdmissionConfiguration.setConfigurationName(configurationName);
        studentAdmissionConfiguration.setConfigurationNameBn(configurationNameBn);
        studentAdmissionConfiguration.setSvgId(svgId);
        studentAdmissionConfiguration.setMinGradePointGeneral(minGradePointGeneral);
        studentAdmissionConfiguration.setMinGradePointInternal(minGradePointInternal);
        studentAdmissionConfiguration.setClassId(classId);
        studentAdmissionConfiguration.setInstituteId(instituteId);
        return studentAdmissionConfiguration;
    }

    public void update(StudentAdmissionConfiguration studentAdmissionConfiguration) {
        studentAdmissionConfiguration.setConfigurationName(configurationName);
        studentAdmissionConfiguration.setConfigurationNameBn(configurationNameBn);
        studentAdmissionConfiguration.setSvgId(svgId);
        studentAdmissionConfiguration.setMinGradePointGeneral(minGradePointGeneral);
        studentAdmissionConfiguration.setMinGradePointInternal(minGradePointInternal);
        studentAdmissionConfiguration.setClassId(classId);
        studentAdmissionConfiguration.setInstituteId(instituteId);
    }
}
