package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeeContactDetail;
import lombok.Data;

@Data
public class EmployeeContactDetailAudit {

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

  public static EmployeeContactDetailAudit from(EmployeeContactDetail contactDetail) {
    EmployeeContactDetailAudit audit = new EmployeeContactDetailAudit();
    audit.setId(contactDetail.getId());
    audit.setContactSl(contactDetail.getContactSl());
    audit.setContactType(contactDetail.getContactType());
    audit.setAddressLine1(contactDetail.getAddressLine1());
    audit.setAddressLine2(contactDetail.getAddressLine2());
    audit.setAddressLine1Bn(contactDetail.getAddressLine1Bn());
    audit.setAddressLine2Bn(contactDetail.getAddressLine2Bn());
    audit.setPhoneWork(contactDetail.getPhoneWork());
    audit.setPhoneHome(contactDetail.getPhoneHome());
    audit.setPhoneMobile(contactDetail.getPhoneMobile());
    audit.setEmailWork(contactDetail.getEmailWork());
    audit.setEmailPersonal(contactDetail.getEmailPersonal());
    audit.setCountryId(contactDetail.getCountryId());
    audit.setDistrictId(contactDetail.getDistrictId());
    audit.setDivisionId(contactDetail.getDivisionId());
    return audit;
  }
}
