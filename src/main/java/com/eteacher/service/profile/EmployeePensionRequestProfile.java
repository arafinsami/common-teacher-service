package com.eteacher.service.profile;

import com.eteacher.service.entity.govtteacher.EmployeePensionRequest;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeePensionRequestProfile {

    private Long id;

    private Date effectiveDate;

    private String remarks;

    @NotNull
    private Status status;

    @NotNull
    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public EmployeePensionRequest to() {
        EmployeePensionRequest request = new EmployeePensionRequest();
        request.setId(id);
        request.setEffectiveDate(effectiveDate);
        request.setRemarks(remarks);
        request.setStatus(status);
        request.setApproverUserId(approverUserId);
        request.setApproverNote(approverNote);
        request.setApproveDate(approveDate);
        request.setReasonForRejection(reasonForRejection);
        return request;
    }
}
