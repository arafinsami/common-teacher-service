package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeRetirementRequest;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeRetirementRequestProfile {

    private Long id;

    @NotNull
    private Date effectiveDate;

    private String remarks;

    @NotNull
    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public EmployeeRetirementRequest to() {
        EmployeeRetirementRequest request = new EmployeeRetirementRequest();
        request.setEffectiveDate(effectiveDate);
        request.setRemarks(remarks);
        request.setStatus(status);
        request.setApproverUserId(approverUserId);
        request.setApproverNote(approverNote);
        request.setApproveDate(approveDate);
        request.setReasonForRejection(reasonForRejection);
        request.setRecordStatus(DRAFT);
        return request;
    }

    public EmployeeRetirementRequest update() {
        EmployeeRetirementRequest request = new EmployeeRetirementRequest();
        request.setId(id);
        request.setEffectiveDate(effectiveDate);
        request.setRemarks(remarks);
        request.setStatus(status);
        request.setApproverUserId(approverUserId);
        request.setApproverNote(approverNote);
        request.setApproveDate(approveDate);
        request.setReasonForRejection(reasonForRejection);
        request.setRecordStatus(ACTIVE);
        return request;
    }
}
