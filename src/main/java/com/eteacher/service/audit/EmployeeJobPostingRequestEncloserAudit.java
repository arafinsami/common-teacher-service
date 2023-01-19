package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeJobPostingRequestEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeJobPostingRequestEncloserAudit {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private EncloserType encloserType;

    private String encloserUrl;

    public static EmployeeJobPostingRequestEncloserAudit audit(EmployeeJobPostingRequestEncloser encloser) {
        EmployeeJobPostingRequestEncloserAudit audit = new EmployeeJobPostingRequestEncloserAudit();
        audit.setId(encloser.getId());
        audit.setEncloserId(encloser.getEncloserId());
        audit.setEncloserName(encloser.getEncloserName());
        audit.setEncloserNameBn(encloser.getEncloserNameBn());
        audit.setEncloserType(encloser.getEncloserType());
        audit.setEncloserUrl(encloser.getEncloserUrl());
        return audit;
    }
}
