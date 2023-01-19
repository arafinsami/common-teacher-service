package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.EmployeeType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class EmployeeTypeDto {

    private Long id;

    @NotBlank
    private String employeeTypeName;

    @NotBlank
    private String employeeTypeNameBn;

    public EmployeeType to() {
        EmployeeType employeeType = new EmployeeType();
        employeeType.setEmployeeTypeName(employeeTypeName);
        employeeType.setEmployeeTypeNameBn(employeeTypeNameBn);
        return employeeType;
    }

    public void update(EmployeeType employeeType) {
        employeeType.setEmployeeTypeName(employeeTypeName);
        employeeType.setEmployeeTypeNameBn(employeeTypeNameBn);
    }
}
