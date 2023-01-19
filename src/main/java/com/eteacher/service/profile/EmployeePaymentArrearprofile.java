package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.mpo.ArrearType;
import com.eteacher.service.entity.mpo.EmployeePaymentArrear;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeePaymentArrearprofile {

    private Long id;

    @NotNull
    private Date arrearDateFrom;

    @NotNull
    private Date arrearDateTo;

    @NotNull
    private Boolean isApproved;

    @NotNull
    private Long approverUserId;

    @NotBlank
    private String approverNote;

    @NotNull
    private Date approveDate;

    @NotNull
    private ArrearType arrearType;

    @NotNull
    private PaymentPeriod paymentPeriod;

    private Long reasonForRejection;

    public EmployeePaymentArrear to() {
        EmployeePaymentArrear arrear = new EmployeePaymentArrear();
        arrear.setArrearDateFrom(arrearDateFrom);
        arrear.setArrearDateTo(arrearDateTo);
        arrear.setIsApproved(isApproved);
        arrear.setApproverUserId(approverUserId);
        arrear.setApproverNote(approverNote);
        arrear.setApproveDate(approveDate);
        arrear.setArrearType(arrearType);
        arrear.setPaymentPeriod(paymentPeriod);
        arrear.setReasonForRejection(reasonForRejection);
        arrear.setRecordStatus(DRAFT);
        return arrear;
    }

    public EmployeePaymentArrear update() {
        EmployeePaymentArrear arrear = new EmployeePaymentArrear();
        arrear.setId(id);
        arrear.setArrearDateFrom(arrearDateFrom);
        arrear.setArrearDateTo(arrearDateTo);
        arrear.setIsApproved(isApproved);
        arrear.setApproverUserId(approverUserId);
        arrear.setApproverNote(approverNote);
        arrear.setApproveDate(approveDate);
        arrear.setArrearType(arrearType);
        arrear.setPaymentPeriod(paymentPeriod);
        arrear.setReasonForRejection(reasonForRejection);
        arrear.setRecordStatus(ACTIVE);
        return arrear;
    }
}
