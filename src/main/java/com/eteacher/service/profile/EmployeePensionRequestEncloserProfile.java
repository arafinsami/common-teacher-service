package com.eteacher.service.profile;

import com.eteacher.service.entity.govtteacher.EmployeePensionRequestEncloser;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EmployeePensionRequestEncloserProfile {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private Integer encloserType;

    private String encloserUrl;

    public EmployeePensionRequestEncloser to(){
        EmployeePensionRequestEncloser encloser = new EmployeePensionRequestEncloser();
        encloser.setId(id);
        encloser.setEncloserId(encloserId);
        encloser.setEncloserName(encloserName);
        encloser.setEncloserNameBn(encloserNameBn);
        encloser.setEncloserType(encloserType);
        encloser.setEncloserUrl(encloserUrl);
        return encloser;
    }
}
