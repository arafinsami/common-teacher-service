package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeRetirementPaymentEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeRetirementPaymentEncloserAudit {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private EncloserType encloserType;

    private String encloserUrl;

    public static EmployeeRetirementPaymentEncloserAudit audit(EmployeeRetirementPaymentEncloser encloser) {
        EmployeeRetirementPaymentEncloserAudit audit = new EmployeeRetirementPaymentEncloserAudit();
        audit.setId(encloser.getId());
        audit.setEncloserId(encloser.getEncloserId());
        audit.setEncloserType(encloser.getEncloserType());
        audit.setEncloserName(encloser.getEncloserName());
        audit.setEncloserNameBn(encloser.getEncloserNameBn());
        audit.setEncloserUrl(encloser.getEncloserUrl());
        return audit;
    }
}
