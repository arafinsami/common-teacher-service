package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeRetirementRequestEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeRetirementRequestEncloserProfile {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private EncloserType encloserType;

    private String encloserUrl;

    public EmployeeRetirementRequestEncloser to() {
        EmployeeRetirementRequestEncloser encloser = new EmployeeRetirementRequestEncloser();
        encloser.setEncloserId(encloserId);
        encloser.setEncloserName(encloserName);
        encloser.setEncloserNameBn(encloserNameBn);
        encloser.setEncloserType(encloserType);
        encloser.setEncloserUrl(encloserUrl);
        encloser.setRecordStatus(DRAFT);
        return encloser;
    }
}
