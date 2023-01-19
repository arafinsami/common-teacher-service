package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeJobPostingRequestEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeJobPostingRequestEncloserProfile {

    private Long id;

    private Long encloserId;

    private String encloserName;

    private String encloserNameBn;

    private EncloserType encloserType;

    private String encloserUrl;

    public EmployeeJobPostingRequestEncloser to() {
        EmployeeJobPostingRequestEncloser encloser = new EmployeeJobPostingRequestEncloser();
        encloser.setEncloserId(encloserId);
        encloser.setEncloserName(encloserName);
        encloser.setEncloserNameBn(encloserNameBn);
        encloser.setEncloserType(encloserType);
        encloser.setEncloserUrl(encloserUrl);
        return encloser;
    }
}
