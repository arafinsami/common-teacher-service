package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeMpoApplicationEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeMpoApplicationEncloserResponse {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private EncloserType encloserType;

    private String encloserUrl;

    public static EmployeeMpoApplicationEncloserResponse response(EmployeeMpoApplicationEncloser encloser) {
        EmployeeMpoApplicationEncloserResponse response = new EmployeeMpoApplicationEncloserResponse();
        response.setId(encloser.getId());
        response.setEncloserId(encloser.getEncloserId());
        response.setEncloserName(encloser.getEncloserName());
        response.setEncloserNameBn(encloser.getEncloserNameBn());
        response.setEncloserType(encloser.getEncloserType());
        response.setEncloserUrl(encloser.getEncloserUrl());
        return response;
    }
}
