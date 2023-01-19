package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.StudentAdmissionConfiguration;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentAdmissionConfigurationAudit {

    private Long id;

    private String studentAdmissionConfigurationName;

    private String studentAdmissionConfigurationNameBn;

    private Long svgId;

    private Double minGradePointGeneral;

    private Double minGradePointInternal;

    private Double minGradePointSpecial;

    private Long classId;

    private Long instituteId;

    public static StudentAdmissionConfigurationAudit audit(StudentAdmissionConfiguration studentAdmissionConfiguration) {
        StudentAdmissionConfigurationAudit audit = new StudentAdmissionConfigurationAudit();
        audit.setId(studentAdmissionConfiguration.getId());
        audit.setStudentAdmissionConfigurationName(studentAdmissionConfiguration.getConfigurationName());
        audit.setStudentAdmissionConfigurationNameBn(studentAdmissionConfiguration.getConfigurationNameBn());
        audit.setSvgId(studentAdmissionConfiguration.getSvgId());
        audit.setMinGradePointGeneral(studentAdmissionConfiguration.getMinGradePointGeneral());
        audit.setMinGradePointInternal(studentAdmissionConfiguration.getMinGradePointInternal());
        audit.setMinGradePointSpecial(studentAdmissionConfiguration.getMinGradePointSpecial());
        audit.setClassId(studentAdmissionConfiguration.getClassId());
        audit.setInstituteId(studentAdmissionConfiguration.getInstituteId());
        return audit;
    }
}
