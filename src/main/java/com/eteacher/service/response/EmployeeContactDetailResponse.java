package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeeContactDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeContactDetailResponse {

    private Long id;

    private Long contactSl;

    private Integer contactType;

    private String addressLine1;

    private String addressLine2;

    private String addressLine1Bn;

    private String addressLine2Bn;

    private String phoneWork;

    private String phoneHome;

    private String phoneMobile;

    private String emailWork;

    private String emailPersonal;

    private Long countryId;

    private Long districtId;

    private Long divisionId;

    public static EmployeeContactDetailResponse response(EmployeeContactDetail contactDetail) {
        EmployeeContactDetailResponse response = new EmployeeContactDetailResponse();
        response.setId(contactDetail.getId());
        response.setContactSl(contactDetail.getContactSl());
        response.setContactType(contactDetail.getContactType());
        response.setAddressLine1(contactDetail.getAddressLine1());
        response.setAddressLine2(contactDetail.getAddressLine2());
        response.setAddressLine1Bn(contactDetail.getAddressLine1Bn());
        response.setAddressLine2Bn(contactDetail.getAddressLine2Bn());
        response.setPhoneWork(contactDetail.getPhoneWork());
        response.setPhoneHome(contactDetail.getPhoneHome());
        response.setPhoneMobile(contactDetail.getPhoneMobile());
        response.setEmailWork(contactDetail.getEmailWork());
        response.setEmailPersonal(contactDetail.getEmailPersonal());
        response.setCountryId(contactDetail.getCountryId());
        response.setDistrictId(contactDetail.getDistrictId());
        response.setDivisionId(contactDetail.getDivisionId());
        return response;
    }
}
