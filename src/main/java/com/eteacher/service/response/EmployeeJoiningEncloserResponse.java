package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeeJoiningEncloser;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeJoiningEncloserResponse {

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private Integer encloserType;

    private String encloserUrl;

    public static EmployeeJoiningEncloserResponse response(EmployeeJoiningEncloser encloser) {
        EmployeeJoiningEncloserResponse response = new EmployeeJoiningEncloserResponse();
        response.setEncloserId(encloser.getId());
        response.setEncloserName(encloser.getEncloserName());
        response.setEncloserNameBn(encloser.getEncloserNameBn());
        response.setEncloserType(encloser.getEncloserType());
        response.setEncloserUrl(encloser.getEncloserUrl());
        return response;
    }
}
