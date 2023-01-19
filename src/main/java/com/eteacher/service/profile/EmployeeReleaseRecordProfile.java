package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeReleaseRecord;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;


@Data
@NoArgsConstructor
public class EmployeeReleaseRecordProfile {

    private Long id;

    @NotNull
    private Status status;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long reasonForRejection;

    public EmployeeReleaseRecord to() {
        EmployeeReleaseRecord record = new EmployeeReleaseRecord();
        record.setStatus(status);
        record.setApproverUserId(approverUserId);
        record.setApproverNote(approverNote);
        record.setApproveDate(approveDate);
        record.setReasonForRejection(reasonForRejection);
        record.setRecordStatus(DRAFT);
        return record;
    }

    public EmployeeReleaseRecord update() {
        EmployeeReleaseRecord record = new EmployeeReleaseRecord();
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
