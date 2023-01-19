package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeAttritionRecord;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.enums.Status;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
public class EmployeeAttritionRecordProfile {

    private Long id;

    @NotNull
    private Status status;

    @NotNull
    private Long approverUserId;

    @NotNull
    private String approverNote;

    @NotNull
    private Date approveDate;

    @NotNull
    private Long reasonForRejection;

    public EmployeeAttritionRecord to() {
        EmployeeAttritionRecord record = new EmployeeAttritionRecord();
        record.setStatus(status);
        record.setApproverUserId(approverUserId);
        record.setApproverNote(approverNote);
        record.setApproveDate(approveDate);
        record.setReasonForRejection(reasonForRejection);
        record.setRecordStatus(DRAFT);
        return record;
    }

    public EmployeeAttritionRecord update() {
        EmployeeAttritionRecord record = new EmployeeAttritionRecord();
        record.setId(id);
        record.setStatus(status);
        record.setApproverUserId(approverUserId);
        record.setApproverNote(approverNote);
        record.setApproveDate(approveDate);
        record.setReasonForRejection(reasonForRejection);
        record.setRecordStatus(ACTIVE);
        return record;
    }
}
