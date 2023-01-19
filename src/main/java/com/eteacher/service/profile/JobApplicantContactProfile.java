package com.eteacher.service.profile;

import com.eteacher.service.entity.job.JobApplicantContact;
import com.eteacher.service.enums.ContactType;
import com.eteacher.service.enums.RecordStatus;
import lombok.Data;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
public class JobApplicantContactProfile {

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

    public JobApplicantContact to(){
        JobApplicantContact contact = new JobApplicantContact();
        contact.setContactSl(contactSl);
        contact.setContactType(contactType);
        contact.setAddressLine1(addressLine1);
        contact.setAddressLine2(addressLine2);
        contact.setAddressLine1Bn(addressLine1Bn);
        contact.setAddressLine2Bn(addressLine2Bn);
        contact.setPhoneWork(phoneWork);
        contact.setPhoneHome(phoneHome);
        contact.setPhoneMobile(phoneMobile);
        contact.setEmailWork(emailWork);
        contact.setEmailPersonal(emailPersonal);
        contact.setCountryId(countryId);
        contact.setDistrictId(districtId);
        contact.setDivision(divisionId);
        contact.setRecordStatus(DRAFT);
        return contact;
    }

    public JobApplicantContact update(){
        JobApplicantContact contact = new JobApplicantContact();
        contact.setId(id);
        contact.setContactSl(contactSl);
        contact.setContactType(contactType);
        contact.setAddressLine1(addressLine1);
        contact.setAddressLine2(addressLine2);
        contact.setAddressLine1Bn(addressLine1Bn);
        contact.setAddressLine2Bn(addressLine2Bn);
        contact.setPhoneWork(phoneWork);
        contact.setPhoneHome(phoneHome);
        contact.setPhoneMobile(phoneMobile);
        contact.setEmailWork(emailWork);
        contact.setEmailPersonal(emailPersonal);
        contact.setCountryId(countryId);
        contact.setDistrictId(districtId);
        contact.setDivision(divisionId);
        contact.setRecordStatus(ACTIVE);
        return contact;
    }
}
