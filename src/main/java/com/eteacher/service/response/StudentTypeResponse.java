package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.StudentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class StudentTypeResponse {

    private Long id;

    private String studentTypeIdLegacy;

    private String studentTypeName;

    private String studentTypeNameBn;

    public static StudentTypeResponse response(StudentType studentType) {
        StudentTypeResponse response = new StudentTypeResponse();
        response.setId(studentType.getId());
        response.setStudentTypeIdLegacy(studentType.getStudentTypeIdLegacy());
        response.setStudentTypeName(studentType.getStudentTypeName());
        response.setStudentTypeNameBn(studentType.getStudentTypeNameBn());
        return response;
    }

    public static Map<String, Object> search(
            String studentTypeIdLegacy,
            String studentTypeName,
            String studentTypeNameBn) {
        Map<String, Object> map = new HashMap<>();
        map.put("studentTypeIdLegacy", studentTypeIdLegacy);
        map.put("studentTypeName", studentTypeName);
        map.put("studentTypeNameBn", studentTypeNameBn);
        return map;
    }
}
