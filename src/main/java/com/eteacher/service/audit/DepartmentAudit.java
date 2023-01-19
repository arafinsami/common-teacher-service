package com.eteacher.service.audit;

import com.eteacher.service.entity.ntrca.Department;
import lombok.Data;

@Data
public class DepartmentAudit {

  private Long id;

  private String departmentName;

  private String departmentNameBn;

  private String description;

  private String descriptionBn;

  public static DepartmentAudit from(Department department){
    DepartmentAudit audit = new DepartmentAudit();
    audit.setId(department.getId());
    audit.setDepartmentName(department.getDepartmentName());
    audit.setDepartmentNameBn(department.getDepartmentNameBn());
    audit.setDescription(department.getDescription());
    audit.setDescriptionBn(department.getDescriptionBn());
    return audit;
  }
}
