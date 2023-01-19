package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeMpoAffiliationEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeMpoAffiliationEncloserResponse {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private EncloserType encloserType;

    private String encloserUrl;

    public EmployeeMpoAffiliationEncloserResponse response(EmployeeMpoAffiliationEncloser encloser) {
        EmployeeMpoAffiliationEncloserResponse response = new EmployeeMpoAffiliationEncloserResponse();
        response.setId(encloser.getId());
        response.setEncloserId(encloser.getEncloserId());
        response.setEncloserName(encloser.getEncloserName());
        response.setEncloserNameBn(encloser.getEncloserNameBn());
        response.setEncloserType(encloser.getEncloserType());
        response.setEncloserUrl(encloser.getEncloserUrl());
        return response;
    }
}
