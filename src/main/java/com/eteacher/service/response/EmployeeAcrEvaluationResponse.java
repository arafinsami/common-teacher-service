package com.eteacher.service.response;

import com.eteacher.service.entity.govtteacher.EmployeeAcrEvaluation;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeAcrEvaluationResponse {

    private Long id;

    private Date assessmentDate;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public static EmployeeAcrEvaluationResponse response(EmployeeAcrEvaluation acrEvaluation) {
        EmployeeAcrEvaluationResponse response = new EmployeeAcrEvaluationResponse();
        response.setId(acrEvaluation.getId());
        response.setAssessmentDate(acrEvaluation.getAssessmentDate());
        response.setStatus(acrEvaluation.getStatus());
        response.setApproverUserId(acrEvaluation.getApproverUserId());
        response.setApproverNote(acrEvaluation.getApproverNote());
        response.setApproveDate(acrEvaluation.getApproveDate());
        response.setReasonForRejection(acrEvaluation.getReasonForRejection());
        return response;
    }
}
