package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeRetirementPaymentEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeRetirementPaymentEncloserResponse {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private EncloserType encloserType;

    private String encloserUrl;

    public static EmployeeRetirementPaymentEncloserResponse response(EmployeeRetirementPaymentEncloser encloser) {
        EmployeeRetirementPaymentEncloserResponse response = new EmployeeRetirementPaymentEncloserResponse();
        response.setId(encloser.getId());
        response.setEncloserId(encloser.getEncloserId());
        response.setEncloserType(encloser.getEncloserType());
        response.setEncloserName(encloser.getEncloserName());
        response.setEncloserNameBn(encloser.getEncloserNameBn());
        response.setEncloserUrl(encloser.getEncloserUrl());
        return response;
    }
}
