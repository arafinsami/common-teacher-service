package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeAttritionRecordEncloser;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeAttritionRecordEncloserAudit {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private Integer encloserType;

    private String encloserUrl;

    public static EmployeeAttritionRecordEncloserAudit audit(EmployeeAttritionRecordEncloser record) {
        EmployeeAttritionRecordEncloserAudit audit = new EmployeeAttritionRecordEncloserAudit();
        audit.setId(record.getId());
        audit.setEncloserId(record.getEncloserId());
        audit.setEncloserName(record.getEncloserName());
        audit.setEncloserNameBn(record.getEncloserNameBn());
        audit.setEncloserType(record.getEncloserType());
        audit.setEncloserUrl(record.getEncloserUrl());
        return audit;
    }
}
