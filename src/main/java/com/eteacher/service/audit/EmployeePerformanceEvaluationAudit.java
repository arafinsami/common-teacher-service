package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
import com.eteacher.service.entity.mpo.EmployeePerformanceEvaluation;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeePerformanceEvaluationAudit {

    private Long id;

    private Date assessmentDate;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private PerformanceEvaluationAssessmentTopic evaluationAssessmentTopic;

    private Long reasonForRejection;

    public EmployeePerformanceEvaluationAudit audit(EmployeePerformanceEvaluation evaluation) {
        EmployeePerformanceEvaluationAudit audit = new EmployeePerformanceEvaluationAudit();
        audit.setId(evaluation.getId());
        audit.setAssessmentDate(evaluation.getAssessmentDate());
        audit.setStatus(evaluation.getStatus());
        audit.setApproverUserId(evaluation.getApproverUserId());
        audit.setApproverNote(evaluation.getApproverNote());
        audit.setApproveDate(evaluation.getApproveDate());
        audit.setEvaluationAssessmentTopic(evaluation.getEvaluationAssessmentTopic());
        audit.setReasonForRejection(evaluation.getReasonForRejection());
        return audit;
    }
}
