package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeePromotionRecord;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeePromotionRecordResponse {

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

    public static EmployeePromotionRecordResponse response(EmployeePromotionRecord records) {
        EmployeePromotionRecordResponse response = new EmployeePromotionRecordResponse();
        response.setId(records.getId());
        response.setEffectiveDate(records.getEffectiveDate());
        response.setPromotionStatus(records.getPromotionStatus());
        response.setApproverUserId(records.getApproverUserId());
        response.setApproverNote(records.getApproverNote());
        response.setApproveDate(records.getApproveDate());
        response.setFromDesignation(records.getFromDesignation());
        response.setToDesignation(records.getToDesignation());
        response.setReasonForRejection(records.getReasonForRejection());
        return response;
    }
}
