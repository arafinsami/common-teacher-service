package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeSalaryIncrement;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import static com.eteacher.service.utils.StringUtils.nonNull;

@Data
@NoArgsConstructor
public class EmployeeSalaryIncrementResponse {

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

    private Long salaryScaleFrom;

    private Long salaryScale;

    private Long employee;

    public static EmployeeSalaryIncrementResponse from(EmployeeSalaryIncrement increment) {
        EmployeeSalaryIncrementResponse response = new EmployeeSalaryIncrementResponse();
        response.setId(increment.getId());
        response.setEffectiveDate(increment.getEffectiveDate());
        response.setNote(increment.getNote());
        response.setSmsNotified(increment.getSmsNotified());
        response.setEmailNotified(increment.getEmailNotified());
        response.setStatus(increment.getStatus());
        response.setApproverUserId(increment.getApproverUserId());
        response.setApproverNote(increment.getApproverNote());
        response.setApproveDate(increment.getApproveDate());
        response.setReasonForRejection(increment.getReasonForRejection());
        response.setEmployee(nonNull(
                increment.getEmployee()) ?
                increment.getEmployee().getId()
                : 0l
        );
        response.setSalaryScaleFrom(nonNull(
                increment.getSalaryScale()) ?
                increment.getSalaryScale().getId()
                : 0l
        );
        response.setSalaryScale(nonNull(
                increment.getSalaryScal1()) ?
                increment.getSalaryScal1().getId()
                : 0l
        );
        return response;
    }
}
