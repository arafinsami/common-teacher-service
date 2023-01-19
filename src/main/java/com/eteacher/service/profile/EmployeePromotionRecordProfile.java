package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.EmployeePromotionRecord;
import com.eteacher.service.enums.RecordStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeePromotionRecordProfile {

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

    public EmployeePromotionRecord to() {
        EmployeePromotionRecord promotionRecord = new EmployeePromotionRecord();
        promotionRecord.setId(id);
        promotionRecord.setEffectiveDate(effectiveDate);
        promotionRecord.setPromotionStatus(promotionStatus);
        promotionRecord.setApproverUserId(approverUserId);
        promotionRecord.setApproverNote(approverNote);
        promotionRecord.setApproveDate(approveDate);
        promotionRecord.setFromDesignation(fromDesignation);
        promotionRecord.setToDesignation(toDesignation);
        promotionRecord.setReasonForRejection(reasonForRejection);
        promotionRecord.setRecordStatus(RecordStatus.DRAFT);
        return promotionRecord;
    }

    public EmployeePromotionRecord update() {
        EmployeePromotionRecord promotionRecord = new EmployeePromotionRecord();
        promotionRecord.setId(id);
        promotionRecord.setEffectiveDate(effectiveDate);
        promotionRecord.setPromotionStatus(promotionStatus);
        promotionRecord.setApproverUserId(approverUserId);
        promotionRecord.setApproverNote(approverNote);
        promotionRecord.setApproveDate(approveDate);
        promotionRecord.setFromDesignation(fromDesignation);
        promotionRecord.setToDesignation(toDesignation);
        promotionRecord.setReasonForRejection(reasonForRejection);
        promotionRecord.setRecordStatus(RecordStatus.ACTIVE);
        return promotionRecord;
    }
}
