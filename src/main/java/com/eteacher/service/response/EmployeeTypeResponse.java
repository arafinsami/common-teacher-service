package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeeType;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EmployeeTypeResponse {

    private Long id;

    private String employeeTypeName;

    private String employeeTypeNameBn;

    public static EmployeeTypeResponse from(EmployeeType employeeType) {
        EmployeeTypeResponse response = new EmployeeTypeResponse();
        response.setId(employeeType.getId());
        response.setEmployeeTypeName(employeeType.getEmployeeTypeName());
        response.setEmployeeTypeNameBn(employeeType.getEmployeeTypeNameBn());
        return response;
    }
}
