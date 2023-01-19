package com.eteacher.service.enums;

public enum AttendanceStatus {
  PRESENT("PRESENT"),
  LATE("LATE"),
  ABSENT("ABSENT");

  private final String label;

  AttendanceStatus(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
