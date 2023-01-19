package com.eteacher.service.audit;

import com.eteacher.service.entity.govtteacher.EmployeePensionRequestEncloser;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeePensionRequestEncloserAudit {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private Integer encloserType;

    private String encloserUrl;

    public static EmployeePensionRequestEncloserAudit audit(EmployeePensionRequestEncloser encloser) {
        EmployeePensionRequestEncloserAudit audit = new EmployeePensionRequestEncloserAudit();
        audit.setId(encloser.getId());
        audit.setEncloserId(encloser.getEncloserId());
        audit.setEncloserName(encloser.getEncloserName());
        audit.setEncloserNameBn(encloser.getEncloserNameBn());
        audit.setEncloserType(encloser.getEncloserType());
        audit.setEncloserUrl(encloser.getEncloserUrl());
        return audit;
    }
}
