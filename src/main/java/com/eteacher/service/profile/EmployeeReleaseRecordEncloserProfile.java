package com.eteacher.service.profile;

import com.eteacher.service.entity.mpo.EmployeeReleaseRecordEncloser;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeReleaseRecordEncloserProfile {

    private Long id;

    @NotNull
    private Long encloserId;

    @NotBlank
    private String encloserName;

    @NotBlank
    private String encloserNameBn;

    @NotNull
    private EncloserType encloserType;

    private String encloserUrl;

    public EmployeeReleaseRecordEncloser to() {
        EmployeeReleaseRecordEncloser encloser = new EmployeeReleaseRecordEncloser();
        encloser.setEncloserId(encloserId);
        encloser.setEncloserName(encloserName);
        encloser.setEncloserNameBn(encloserNameBn);
        encloser.setEncloserType(encloserType);
        encloser.setEncloserUrl(encloserUrl);
        encloser.setRecordStatus(DRAFT);
        return encloser;
    }
}
