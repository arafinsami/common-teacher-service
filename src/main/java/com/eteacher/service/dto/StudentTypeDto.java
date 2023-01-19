package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.StudentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class StudentTypeDto {

    private Long id;

    @NotBlank
    private String studentTypeIdLegacy;

    @NotBlank
    private String studentTypeName;

    @NotBlank
    private String studentTypeNameBn;

    public StudentType to() {
        StudentType studentType = new StudentType();
        studentType.setStudentTypeIdLegacy(studentTypeIdLegacy);
        studentType.setStudentTypeName(studentTypeName);
        studentType.setStudentTypeNameBn(studentTypeNameBn);
        return studentType;
    }

    public void update(StudentType studentType) {
        studentType.setStudentTypeIdLegacy(studentTypeIdLegacy);
        studentType.setStudentTypeName(studentTypeName);
        studentType.setStudentTypeNameBn(studentTypeNameBn);
    }
}
