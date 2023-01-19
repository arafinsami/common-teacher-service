package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.StudentType;
import com.eteacher.service.response.StudentTypeResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentTypeAudit {

    private Long id;

    private String studentTypeIdLegacy;

    private String studentTypeName;

    private String studentTypeNameBn;

    public static StudentTypeAudit audit(StudentType studentType) {
        StudentTypeAudit audit = new StudentTypeAudit();
        audit.setId(studentType.getId());
        audit.setStudentTypeIdLegacy(studentType.getStudentTypeIdLegacy());
        audit.setStudentTypeName(studentType.getStudentTypeName());
        audit.setStudentTypeNameBn(studentType.getStudentTypeNameBn());
        return audit;
    }
}
