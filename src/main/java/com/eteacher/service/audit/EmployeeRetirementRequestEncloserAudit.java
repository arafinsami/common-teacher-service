package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeRetirementRequestEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeRetirementRequestEncloserAudit {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private EncloserType encloserType;

    private String encloserUrl;

    public static EmployeeRetirementRequestEncloserAudit audit(EmployeeRetirementRequestEncloser encloser) {
        EmployeeRetirementRequestEncloserAudit audit = new EmployeeRetirementRequestEncloserAudit();
        audit.setId(encloser.getId());
        audit.setEncloserId(encloser.getEncloserId());
        audit.setEncloserName(encloser.getEncloserNameBn());
        audit.setEncloserNameBn(encloser.getEncloserName());
        audit.setEncloserType(encloser.getEncloserType());
        audit.setEncloserUrl(encloser.getEncloserName());
        return audit;
    }
}
