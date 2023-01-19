package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeTransferRecordPreferredInstitute;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeTransferRecordPreferredInstituteAudit {

    private Long id;

    private Integer preferenceOrderIndex;

    public static EmployeeTransferRecordPreferredInstituteAudit audit(EmployeeTransferRecordPreferredInstitute transfer) {
        EmployeeTransferRecordPreferredInstituteAudit audit = new EmployeeTransferRecordPreferredInstituteAudit();
        audit.setId(transfer.getId());
        audit.setPreferenceOrderIndex(transfer.getPreferenceOrderIndex());
        return audit;
    }
}
