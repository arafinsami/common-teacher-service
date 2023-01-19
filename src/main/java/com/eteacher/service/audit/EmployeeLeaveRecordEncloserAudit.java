package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeeLeaveRecordEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class EmployeeLeaveRecordEncloserAudit {

    private Long id;

    private EncloserType encloserType;

    private String memorandumNo;

    private Date issueDate;

    private String encloserName;

    private String encloserNameBn;

    private String encloserUrl;

    public static EmployeeLeaveRecordEncloserAudit audit(EmployeeLeaveRecordEncloser encloser) {
        EmployeeLeaveRecordEncloserAudit audit = new EmployeeLeaveRecordEncloserAudit();
        audit.setId(encloser.getId());
        audit.setEncloserType(encloser.getEncloserType());
        audit.setMemorandumNo(encloser.getMemorandumNo());
        audit.setIssueDate(encloser.getIssueDate());
        audit.setEncloserName(encloser.getEncloserName());
        audit.setEncloserNameBn(encloser.getEncloserNameBn());
        audit.setEncloserUrl(encloser.getEncloserUrl());
        return audit;
    }
}
