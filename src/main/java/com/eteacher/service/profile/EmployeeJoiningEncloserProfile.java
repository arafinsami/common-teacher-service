package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.EmployeeJoiningEncloser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeJoiningEncloserProfile {

    private Long id;

    @NotBlank
    private String encloserName;

    @NotBlank
    private String encloserNameBn;

    @NotNull
    private Integer encloserType;

    private String encloserUrl;

    public EmployeeJoiningEncloser to() {
        EmployeeJoiningEncloser encloser = new EmployeeJoiningEncloser();
        encloser.setEncloserName(encloserName);
        encloser.setEncloserNameBn(encloserNameBn);
        encloser.setEncloserType(encloserType);
        encloser.setEncloserUrl(encloserUrl);
        encloser.setRecordStatus(DRAFT);
        return encloser;
    }
}
