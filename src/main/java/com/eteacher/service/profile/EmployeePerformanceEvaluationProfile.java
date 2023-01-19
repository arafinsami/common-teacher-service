package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
import com.eteacher.service.entity.mpo.EmployeePerformanceEvaluation;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeePerformanceEvaluationProfile {

    private Long id;

    private Date assessmentDate;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private PerformanceEvaluationAssessmentTopic evaluationAssessmentTopic;

    private Long reasonForRejection;

    public EmployeePerformanceEvaluation to() {
        EmployeePerformanceEvaluation evaluation = new EmployeePerformanceEvaluation();
        evaluation.setId(id);
        evaluation.setAssessmentDate(assessmentDate);
        evaluation.setStatus(status);
        evaluation.setApproverUserId(approverUserId);
        evaluation.setApproverNote(approverNote);
        evaluation.setApproveDate(approveDate);
        evaluation.setEvaluationAssessmentTopic(evaluationAssessmentTopic);
        evaluation.setReasonForRejection(reasonForRejection);
        return evaluation;
    }
}
