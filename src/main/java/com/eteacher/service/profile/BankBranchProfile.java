package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.BankBranch;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankBranchProfile {

    private Long id;

    private String bankBranchName;

    private String bankBranchNameBn;

    private Integer bankBranchRoutingNumber;

    private String addressLine1;

    private String addressLine2;

    private String addressLine1Bn;

    private String addressLine2Bn;

    private String phone;

    private String mobile;

    private String email;

    private String url;

    private Long countryId;

    private Long districtId;

    private Long divisionId;

    public BankBranch to() {
        BankBranch bankBranch = new BankBranch();
        bankBranch.setId(id);
        bankBranch.setBankBranchName(bankBranchName);
        bankBranch.setBankBranchNameBn(bankBranchNameBn);
        bankBranch.setBankBranchRoutingNumber(bankBranchRoutingNumber);
        bankBranch.setAddressLine1(addressLine1);
        bankBranch.setAddressLine2(addressLine2);
        bankBranch.setAddressLine1Bn(addressLine1Bn);
        bankBranch.setAddressLine2Bn(addressLine2Bn);
        bankBranch.setPhone(phone);
        bankBranch.setMobile(mobile);
        bankBranch.setEmail(email);
        bankBranch.setUrl(url);
        bankBranch.setCountryId(countryId);
        bankBranch.setDivisionId(divisionId);
        bankBranch.setDistrictId(districtId);
        return bankBranch;
    }
}
