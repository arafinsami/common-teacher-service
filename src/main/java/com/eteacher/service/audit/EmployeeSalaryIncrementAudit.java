package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeSalaryIncrement;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeSalaryIncrementAudit {

    private Long id;

    private Date effectiveDate;

    private String note;

    private Boolean smsNotified;

    private Boolean emailNotified;

    private Integer status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public static EmployeeSalaryIncrementAudit audit(EmployeeSalaryIncrement increment) {
        EmployeeSalaryIncrementAudit audit = new EmployeeSalaryIncrementAudit();
        audit.setId(increment.getId());
        audit.setEffectiveDate(increment.getEffectiveDate());
        audit.setNote(increment.getNote());
        audit.setSmsNotified(increment.getSmsNotified());
        audit.setEmailNotified(increment.getEmailNotified());
        audit.setStatus(increment.getStatus());
        audit.setApproverUserId(increment.getApproverUserId());
        audit.setApproverNote(increment.getApproverNote());
        audit.setApproveDate(increment.getApproveDate());
        audit.setReasonForRejection(increment.getReasonForRejection());
        return audit;
    }
}
