package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.StudentAdmissionConfiguration;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class StudentAdmissionConfigurationResponse {

    private Long id;

    private String studentAdmissionConfigurationName;

    private String studentAdmissionConfigurationNameBn;

    private Long svgId;

    private Double minGradePointGeneral;

    private Double minGradePointInternal;

    private Double minGradePointSpecial;

    private Long classId;

    private Long instituteId;

    public static StudentAdmissionConfigurationResponse response(StudentAdmissionConfiguration studentAdmissionConfiguration) {
        StudentAdmissionConfigurationResponse response = new StudentAdmissionConfigurationResponse();
        response.setId(studentAdmissionConfiguration.getId());
        response.setStudentAdmissionConfigurationName(studentAdmissionConfiguration.getConfigurationName());
        response.setStudentAdmissionConfigurationNameBn(studentAdmissionConfiguration.getConfigurationNameBn());
        response.setSvgId(studentAdmissionConfiguration.getSvgId());
        response.setMinGradePointGeneral(studentAdmissionConfiguration.getMinGradePointGeneral());
        response.setMinGradePointInternal(studentAdmissionConfiguration.getMinGradePointInternal());
        response.setMinGradePointSpecial(studentAdmissionConfiguration.getMinGradePointSpecial());
        response.setClassId(studentAdmissionConfiguration.getClassId());
        response.setInstituteId(studentAdmissionConfiguration.getInstituteId());
        return response;
    }

    public static Map<String, Object> search(
            String studentAdmissionConfigurationName,
            String studentAdmissionConfigurationNameBn,
            Long svgId,
            Double minGradePointGeneral,
            Double minGradePointInternal,
            Double minGradePointSpecial,
            Long classId,
            Long instituteId) {
        Map<String, Object> map = new HashMap<>();
        map.put("studentAdmissionConfigurationName", studentAdmissionConfigurationName);
        map.put("studentAdmissionConfigurationNameBn", studentAdmissionConfigurationNameBn);
        map.put("svgId", svgId);
        map.put("minGradePointGeneral", minGradePointGeneral);
        map.put("minGradePointInternal", minGradePointInternal);
        map.put("minGradePointSpecial", minGradePointSpecial);
        map.put("classId", classId);
        map.put("instituteId", instituteId);
        return map;
    }
}
