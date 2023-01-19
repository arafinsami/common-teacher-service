package com.eteacher.service.dto;

import com.eteacher.service.entity.ntrca.Department;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class DepartmentDto {

    private Long id;

    @NotBlank
    private String departmentName;

    @NotBlank
    private String departmentNameBn;

    @NotBlank
    private String description;

    @NotBlank
    private String descriptionBn;

    public Department to() {
        Department department = new Department();
        department.setDepartmentName(departmentName);
        department.setDepartmentNameBn(departmentNameBn);
        department.setDescription(description);
        department.setDescriptionBn(descriptionBn);
        return department;
    }

    public void update(Department department) {
        department.setDepartmentName(departmentName);
        department.setDepartmentNameBn(departmentNameBn);
        department.setDescription(description);
        department.setDescriptionBn(descriptionBn);
    }
}
