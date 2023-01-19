package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeeJoiningEncloser;
import lombok.Data;

@Data
public class EmployeeJoiningEncloserAudit {

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private Integer encloserType;

    private String encloserUrl;

    public static EmployeeJoiningEncloserAudit audit(EmployeeJoiningEncloser encloser) {
        EmployeeJoiningEncloserAudit audit = new EmployeeJoiningEncloserAudit();
        audit.setEncloserId(encloser.getId());
        audit.setEncloserName(encloser.getEncloserName());
        audit.setEncloserNameBn(encloser.getEncloserNameBn());
        audit.setEncloserType(encloser.getEncloserType());
        audit.setEncloserUrl(encloser.getEncloserUrl());
        return audit;
    }
}
