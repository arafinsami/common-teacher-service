package com.eteacher.service.response;

import com.eteacher.service.entity.job.JobApplicantContact;
import com.eteacher.service.enums.ContactType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobApplicantContactResponse {

    private Long id;

    private Long contactSl;

    private ContactType contactType;

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

    public static JobApplicantContactResponse response(JobApplicantContact contact) {
        JobApplicantContactResponse response = new JobApplicantContactResponse();
        response.setId(contact.getId());
        response.setContactSl(contact.getContactSl());
        response.setContactType(contact.getContactType());
        response.setAddressLine1(contact.getAddressLine1());
        response.setAddressLine2(contact.getAddressLine2());
        response.setAddressLine1Bn(contact.getAddressLine1Bn());
        response.setAddressLine2Bn(contact.getAddressLine2Bn());
        response.setPhoneWork(contact.getPhoneWork());
        response.setPhoneHome(contact.getPhoneHome());
        response.setPhoneMobile(contact.getPhoneMobile());
        response.setEmailWork(contact.getPhoneWork());
        response.setEmailPersonal(contact.getEmailPersonal());
        response.setCountryId(contact.getCountryId());
        response.setDistrictId(contact.getDistrictId());
        response.setDivisionId(contact.getDivision());
        return response;
    }
}
