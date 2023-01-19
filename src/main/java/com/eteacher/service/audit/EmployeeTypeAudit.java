package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeeType;
import lombok.Data;

@Data
public class EmployeeTypeAudit {

  private Long id;

  private String employeeTypeName;

  private String employeeTypeNameBn;

  public static EmployeeTypeAudit from(EmployeeType employeeType) {
    EmployeeTypeAudit audit = new EmployeeTypeAudit();
    audit.setId(employeeType.getId());
    audit.setEmployeeTypeName(employeeType.getEmployeeTypeName());
    audit.setEmployeeTypeNameBn(employeeType.getEmployeeTypeNameBn());
    return audit;
  }
}
