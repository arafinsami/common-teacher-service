package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.mpo.EmployeeMpoSalaryExtraBenefit;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeMpoSalaryExtraBenefitProfile {

    private Long id;

    @NotNull
    private Boolean isApproved;

    @NotNull
    private Long approverUserId;

    @NotBlank
    private String approverNote;

    @NotNull
    private Date approveDate;

    @NotNull
    private PaymentPeriod paymentPeriod;

    private Long reasonForRejection;

    public EmployeeMpoSalaryExtraBenefit to() {
        EmployeeMpoSalaryExtraBenefit benefit = new EmployeeMpoSalaryExtraBenefit();
        benefit.setIsApproved(isApproved);
        benefit.setApproverUserId(approverUserId);
        benefit.setApproverNote(approverNote);
        benefit.setApproveDate(approveDate);
        benefit.setPaymentPeriod(paymentPeriod);
        benefit.setReasonForRejection(reasonForRejection);
        benefit.setRecordStatus(DRAFT);
        return benefit;
    }

    public EmployeeMpoSalaryExtraBenefit update() {
        EmployeeMpoSalaryExtraBenefit benefit = new EmployeeMpoSalaryExtraBenefit();
        benefit.setId(id);
        benefit.setIsApproved(isApproved);
        benefit.setApproverUserId(approverUserId);
        benefit.setApproverNote(approverNote);
        benefit.setApproveDate(approveDate);
        benefit.setPaymentPeriod(paymentPeriod);
        benefit.setReasonForRejection(reasonForRejection);
        benefit.setRecordStatus(ACTIVE);
        return benefit;
    }
}
