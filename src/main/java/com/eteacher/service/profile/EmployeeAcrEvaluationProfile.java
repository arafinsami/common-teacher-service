package com.eteacher.service.profile;

import com.eteacher.service.entity.govtteacher.EmployeeAcrEvaluation;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeAcrEvaluationProfile {

    private Long id;

    @NotNull
    private Date assessmentDate;

    @NotNull
    private Status status;

    @NotNull
    private Long approverUserId;

    @NotNull
    private String approverNote;

    @NotNull
    private Date approveDate;

    private Long reasonForRejection;

    public EmployeeAcrEvaluation to() {
        EmployeeAcrEvaluation evaluation = new EmployeeAcrEvaluation();
        evaluation.setAssessmentDate(assessmentDate);
        evaluation.setStatus(status);
        evaluation.setApproverUserId(approverUserId);
        evaluation.setApproverNote(approverNote);
        evaluation.setApproveDate(approveDate);
        evaluation.setReasonForRejection(reasonForRejection);
        evaluation.setRecordStatus(DRAFT);
        return evaluation;
    }

    public EmployeeAcrEvaluation update() {
        EmployeeAcrEvaluation evaluation = new EmployeeAcrEvaluation();
        evaluation.setId(id);
        evaluation.setAssessmentDate(assessmentDate);
        evaluation.setStatus(status);
        evaluation.setApproverUserId(approverUserId);
        evaluation.setApproverNote(approverNote);
        evaluation.setApproveDate(approveDate);
        evaluation.setReasonForRejection(reasonForRejection);
        evaluation.setRecordStatus(ACTIVE);
        return evaluation;
    }
}
