package com.eteacher.service.response;


import com.eteacher.service.entity.mpo.EmployeeAttritionRecordEncloser;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeAttritionRecordEncloserResponse {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private Integer encloserType;

    private String encloserUrl;

    public static EmployeeAttritionRecordEncloserResponse response(EmployeeAttritionRecordEncloser record) {
        EmployeeAttritionRecordEncloserResponse response = new EmployeeAttritionRecordEncloserResponse();
        response.setId(record.getId());
        response.setEncloserId(record.getEncloserId());
        response.setEncloserName(record.getEncloserName());
        response.setEncloserNameBn(record.getEncloserNameBn());
        response.setEncloserType(record.getEncloserType());
        response.setEncloserUrl(record.getEncloserUrl());
        return response;
    }
}
