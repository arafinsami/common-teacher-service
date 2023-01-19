package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeMpoAffiliationEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeMpoAffiliationEncloserAudit {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private EncloserType encloserType;

    private String encloserUrl;

    public static EmployeeMpoAffiliationEncloserAudit audit(EmployeeMpoAffiliationEncloser encloser) {
        EmployeeMpoAffiliationEncloserAudit audit = new EmployeeMpoAffiliationEncloserAudit();
        audit.setId(encloser.getId());
        audit.setEncloserId(encloser.getEncloserId());
        audit.setEncloserName(encloser.getEncloserName());
        audit.setEncloserNameBn(encloser.getEncloserNameBn());
        audit.setEncloserType(encloser.getEncloserType());
        audit.setEncloserUrl(encloser.getEncloserUrl());
        return audit;
    }
}
