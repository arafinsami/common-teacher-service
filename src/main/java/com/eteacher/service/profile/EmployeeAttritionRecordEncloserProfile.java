package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeAttritionRecordEncloser;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.eteacher.service.enums.RecordStatus.DRAFT;


@Data
@NoArgsConstructor
public class EmployeeAttritionRecordEncloserProfile {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private Integer encloserType;

    private String encloserUrl;

    public EmployeeAttritionRecordEncloser to() {
        EmployeeAttritionRecordEncloser encloser = new EmployeeAttritionRecordEncloser();
        encloser.setId(id);
        encloser.setEncloserId(encloserId);
        encloser.setEncloserName(encloserName);
        encloser.setEncloserNameBn(encloserNameBn);
        encloser.setEncloserType(encloserType);
        encloser.setEncloserUrl(encloserUrl);
        encloser.setRecordStatus(DRAFT);
        return encloser;
    }
}
