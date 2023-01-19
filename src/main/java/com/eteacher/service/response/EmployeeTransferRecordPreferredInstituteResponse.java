package com.eteacher.service.response;


import com.eteacher.service.entity.mpo.EmployeeTransferRecordPreferredInstitute;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeTransferRecordPreferredInstituteResponse {

    private Long id;

    private Integer preferenceOrderIndex;

    public static EmployeeTransferRecordPreferredInstituteResponse response(EmployeeTransferRecordPreferredInstitute transfer) {
        EmployeeTransferRecordPreferredInstituteResponse response = new EmployeeTransferRecordPreferredInstituteResponse();
        response.setId(transfer.getId());
        response.setPreferenceOrderIndex(transfer.getPreferenceOrderIndex());
        return response;
    }
}
