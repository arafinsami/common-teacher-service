package com.eteacher.service.profile;


import com.eteacher.service.entity.mpo.EmployeeTransferRecordPreferredInstitute;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeTransferRecordPreferredInstituteProfile {

    private Long id;

    private Integer preferenceOrderIndex;

    public EmployeeTransferRecordPreferredInstitute to() {
        EmployeeTransferRecordPreferredInstitute institute = new EmployeeTransferRecordPreferredInstitute();
        institute.setPreferenceOrderIndex(preferenceOrderIndex);
        institute.setRecordStatus(DRAFT);
        return institute;
    }

    public EmployeeTransferRecordPreferredInstitute update() {
        EmployeeTransferRecordPreferredInstitute institute = new EmployeeTransferRecordPreferredInstitute();
        institute.setId(id);
        institute.setPreferenceOrderIndex(preferenceOrderIndex);
        institute.setRecordStatus(ACTIVE);
        return institute;
    }
}
