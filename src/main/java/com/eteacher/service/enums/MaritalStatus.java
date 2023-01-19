package com.eteacher.service.enums;

public enum MaritalStatus {

  MARRIED("MARRIED"),
  UNMARRIED("UNMARRIED");

  private final String label;

  MaritalStatus(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
