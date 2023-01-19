package com.eteacher.service.audit;

import com.eteacher.service.entity.govtteacher.EmployeeAcrEvaluation;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeAcrEvaluationAudit {

    private Long id;

    private Date assessmentDate;

    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public static EmployeeAcrEvaluationAudit audit(EmployeeAcrEvaluation acrEvaluation) {
        EmployeeAcrEvaluationAudit audit = new EmployeeAcrEvaluationAudit();
        audit.setAssessmentDate(acrEvaluation.getAssessmentDate());
        audit.setStatus(acrEvaluation.getStatus());
        audit.setApproverUserId(acrEvaluation.getApproverUserId());
        audit.setApproverNote(acrEvaluation.getApproverNote());
        audit.setApproveDate(acrEvaluation.getApproveDate());
        audit.setReasonForRejection(acrEvaluation.getReasonForRejection());
        return audit;
    }
}
