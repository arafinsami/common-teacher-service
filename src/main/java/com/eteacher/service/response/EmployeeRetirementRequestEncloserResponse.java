package com.eteacher.service.response;

import com.eteacher.service.audit.EmployeeRetirementRequestEncloserAudit;
import com.eteacher.service.entity.mpo.EmployeeRetirementRequestEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeRetirementRequestEncloserResponse {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private EncloserType encloserType;

    private String encloserUrl;

    public static EmployeeRetirementRequestEncloserResponse response(EmployeeRetirementRequestEncloser encloser) {
        EmployeeRetirementRequestEncloserResponse response = new EmployeeRetirementRequestEncloserResponse();
        response.setId(encloser.getId());
        response.setEncloserId(encloser.getEncloserId());
        response.setEncloserName(encloser.getEncloserNameBn());
        response.setEncloserNameBn(encloser.getEncloserName());
        response.setEncloserType(encloser.getEncloserType());
        response.setEncloserUrl(encloser.getEncloserName());
        return response;
    }
}
