package com.eteacher.service.audit;

import com.eteacher.service.entity.job.JobApplicantContact;
import com.eteacher.service.enums.ContactType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobApplicantContactAudit {

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

    public static JobApplicantContactAudit audit(JobApplicantContact contact) {
        JobApplicantContactAudit audit = new JobApplicantContactAudit();
        audit.setId(contact.getId());
        audit.setContactSl(contact.getContactSl());
        audit.setContactType(contact.getContactType());
        audit.setAddressLine1(contact.getAddressLine1());
        audit.setAddressLine2(contact.getAddressLine2());
        audit.setAddressLine1Bn(contact.getAddressLine1Bn());
        audit.setAddressLine2Bn(contact.getAddressLine2Bn());
        audit.setPhoneWork(contact.getPhoneWork());
        audit.setPhoneHome(contact.getPhoneHome());
        audit.setPhoneMobile(contact.getPhoneMobile());
        audit.setEmailWork(contact.getPhoneWork());
        audit.setEmailPersonal(contact.getEmailPersonal());
        audit.setCountryId(contact.getCountryId());
        audit.setDistrictId(contact.getDistrictId());
        audit.setDivisionId(contact.getDivision());
        return audit;
    }
}
