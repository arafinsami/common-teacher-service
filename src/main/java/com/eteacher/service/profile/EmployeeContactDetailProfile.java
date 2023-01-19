package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.EmployeeContactDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeContactDetailProfile {

    private Long id;

    @NotNull
    private Long contactSl;

    @NotNull
    private Integer contactType;

    @NotNull
    private String addressLine1;

    private String addressLine2;

    @NotNull
    private String addressLine1Bn;

    private String addressLine2Bn;

    private String phoneWork;

    private String phoneHome;

    @NotNull
    private String phoneMobile;

    @NotNull
    private String emailWork;

    private String emailPersonal;

    @NotNull
    private Long countryId;

    @NotNull
    private Long districtId;

    @NotNull
    private Long divisionId;

    public EmployeeContactDetail to() {
        EmployeeContactDetail detail = new EmployeeContactDetail();
        detail.setId(id);
        detail.setContactSl(contactSl);
        detail.setContactType(contactType);
        detail.setAddressLine1(addressLine1);
        detail.setAddressLine2(addressLine2);
        detail.setAddressLine1Bn(addressLine1Bn);
        detail.setAddressLine2Bn(addressLine2Bn);
        detail.setPhoneWork(phoneWork);
        detail.setPhoneHome(phoneHome);
        detail.setPhoneMobile(phoneMobile);
        detail.setEmailWork(emailWork);
        detail.setEmailPersonal(emailPersonal);
        detail.setCountryId(countryId);
        detail.setDistrictId(districtId);
        detail.setDivisionId(divisionId);
        detail.setRecordStatus(DRAFT);
        return detail;
    }

    public EmployeeContactDetail update() {
        EmployeeContactDetail detail = new EmployeeContactDetail();
        detail.setId(id);
        detail.setContactSl(contactSl);
        detail.setContactType(contactType);
        detail.setAddressLine1(addressLine1);
        detail.setAddressLine2(addressLine2);
        detail.setAddressLine1Bn(addressLine1Bn);
        detail.setAddressLine2Bn(addressLine2Bn);
        detail.setPhoneWork(phoneWork);
        detail.setPhoneHome(phoneHome);
        detail.setPhoneMobile(phoneMobile);
        detail.setEmailWork(emailWork);
        detail.setEmailPersonal(emailPersonal);
        detail.setCountryId(countryId);
        detail.setDistrictId(districtId);
        detail.setDivisionId(divisionId);
        detail.setRecordStatus(ACTIVE);
        return detail;
    }
}
