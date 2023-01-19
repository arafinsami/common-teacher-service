package com.eteacher.service.search;

public class EmployeeAttendanceSearch {

  public static final String[] sortable = {
          "id",
          "dateOfAttendance",
          "inAt",
          "outAt",
          "status",
          "remarks",
          "approverUserId",
          "approverNote",
          "approveDate",
          "employee",
          "reasonForRejection"
  };

  public static final String[] searchable = {
          "approverNote"
  };
}
