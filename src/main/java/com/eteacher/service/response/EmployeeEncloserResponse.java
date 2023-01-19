package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeeEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeEncloserResponse {

    private Long id;

    private EncloserType encloserType;

    private String memorandumNo;

    private Date issueDate;

    private String encloserName;

    private String encloserNameBn;

    private String encloserUrl;

    public static EmployeeEncloserResponse response(EmployeeEncloser encloser) {
        EmployeeEncloserResponse response = new EmployeeEncloserResponse();
        response.setId(encloser.getId());
        response.setEncloserType(encloser.getEncloserType());
        response.setMemorandumNo(encloser.getMemorandumNo());
        response.setIssueDate(encloser.getIssueDate());
        response.setEncloserName(encloser.getEncloserName());
        response.setEncloserNameBn(encloser.getEncloserNameBn());
        response.setEncloserUrl(encloser.getEncloserUrl());
        return response;
    }
}
