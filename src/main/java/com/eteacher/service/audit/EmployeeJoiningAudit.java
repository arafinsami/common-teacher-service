package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeeJoining;
import lombok.Data;

import java.util.Date;

import static com.eteacher.service.utils.StringUtils.nonNull;

@Data
public class EmployeeJoiningAudit {

  private Long id;

  private Date effectiveDate;

  private Integer postingStatus;

  private Long approverUserId;

  private String approverNote;

  private Date approveDate;

  private Long fromDesignation;

  private Long toDesignation;

  private Long fromInstitute;

  private Long toInstitute;

  private Long reasonForRejection;

  public static EmployeeJoiningAudit from(EmployeeJoining employeeJoining) {
    EmployeeJoiningAudit audit = new EmployeeJoiningAudit();
    audit.setId(employeeJoining.getId());
    audit.setEffectiveDate(employeeJoining.getEffectiveDate());
    audit.setPostingStatus(employeeJoining.getPostingStatus());
    audit.setApproverUserId(employeeJoining.getApproverUserId());
    audit.setApproverNote(employeeJoining.getApproverNote());
    audit.setApproveDate(employeeJoining.getApproveDate());
    audit.setFromDesignation(employeeJoining.getFromDesignation());
    audit.setToDesignation(employeeJoining.getToDesignation());
    audit.setFromInstitute(employeeJoining.getFromInstitute());
    audit.setToInstitute(employeeJoining.getToInstitute());
    audit.setReasonForRejection(employeeJoining.getReasonForRejection());
    return audit;
  }
}
