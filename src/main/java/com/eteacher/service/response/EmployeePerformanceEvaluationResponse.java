package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
import com.eteacher.service.entity.mpo.EmployeePerformanceEvaluation;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeePerformanceEvaluationResponse {

    private Long id;

    private Date assessmentDate;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private PerformanceEvaluationAssessmentTopic evaluationAssessmentTopic;

    private Long reasonForRejection;

    public EmployeePerformanceEvaluationResponse response(EmployeePerformanceEvaluation evaluation) {
        EmployeePerformanceEvaluationResponse response = new EmployeePerformanceEvaluationResponse();
        response.setId(evaluation.getId());
        response.setAssessmentDate(evaluation.getAssessmentDate());
        response.setStatus(evaluation.getStatus());
        response.setApproverUserId(evaluation.getApproverUserId());
        response.setApproverNote(evaluation.getApproverNote());
        response.setApproveDate(evaluation.getApproveDate());
        response.setEvaluationAssessmentTopic(evaluation.getEvaluationAssessmentTopic());
        response.setReasonForRejection(evaluation.getReasonForRejection());
        return response;
    }
}
