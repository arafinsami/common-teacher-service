package com.eteacher.service.enums;

public enum EmploymentType {

  PERMANENT("PERMANENT"),
  PARTTIME("PARTTIME");

  private final String label;

  EmploymentType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
