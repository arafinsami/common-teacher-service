package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeSalaryIncrement;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeSalaryIncrementProfile {

    private Long id;

    @NotBlank
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date effectiveDate;

    private String note;

    private Boolean smsNotified;

    private Boolean emailNotified;

    private Integer status;

    private Long approverUserId;

    private String approverNote;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date approveDate;

//  @NotNull
//  private Long employee;

    private Long reasonForRejection;

    @NotNull
    private Long salaryScaleFrom;

    @NotNull
    private Long salaryScale;

    public EmployeeSalaryIncrement to() {
        EmployeeSalaryIncrement increment = new EmployeeSalaryIncrement();
        increment.setEffectiveDate(effectiveDate);
        increment.setNote(note);
        increment.setSmsNotified(smsNotified);
        increment.setEmailNotified(emailNotified);
        increment.setStatus(status);
        increment.setApproverUserId(approverUserId);
        increment.setApproverNote(approverNote);
        increment.setApproveDate(approveDate);
        increment.setReasonForRejection(reasonForRejection);
        increment.setRecordStatus(DRAFT);
        return increment;
    }

    public EmployeeSalaryIncrement update() {
        EmployeeSalaryIncrement increment = new EmployeeSalaryIncrement();
        increment.setId(id);
        increment.setEffectiveDate(effectiveDate);
        increment.setNote(note);
        increment.setSmsNotified(smsNotified);
        increment.setEmailNotified(emailNotified);
        increment.setStatus(status);
        increment.setApproverUserId(approverUserId);
        increment.setApproverNote(approverNote);
        increment.setApproveDate(approveDate);
        increment.setReasonForRejection(reasonForRejection);
        increment.setRecordStatus(ACTIVE);
        return increment;
    }
}
