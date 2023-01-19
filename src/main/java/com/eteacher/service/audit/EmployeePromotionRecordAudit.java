package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeePromotionRecord;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeePromotionRecordAudit {

    private Long id;

    private Date effectiveDate;

    private Integer promotionStatus;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long fromDesignation;

    private Long toDesignation;

//    private EmployeePromotionRecordEncloser employeePromotionRecordEncloser;

    private Long reasonForRejection;

//    private SalaryScale salaryScale;

//    private Employee employee;

    public static EmployeePromotionRecordAudit audit(EmployeePromotionRecord records) {
        EmployeePromotionRecordAudit audit = new EmployeePromotionRecordAudit();
        audit.setId(records.getId());
        audit.setEffectiveDate(records.getEffectiveDate());
        audit.setPromotionStatus(records.getPromotionStatus());
        audit.setApproverUserId(records.getApproverUserId());
        audit.setApproverNote(records.getApproverNote());
        audit.setApproveDate(records.getApproveDate());
        audit.setFromDesignation(records.getFromDesignation());
        audit.setToDesignation(records.getToDesignation());
        audit.setReasonForRejection(records.getReasonForRejection());
        return audit;
    }
}
