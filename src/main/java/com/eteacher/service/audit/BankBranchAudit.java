package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.BankBranch;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankBranchAudit {

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

    public static BankBranchAudit audit(BankBranch branch) {
        BankBranchAudit audit = new BankBranchAudit();
        audit.setId(branch.getId());
        audit.setBankBranchName(branch.getBankBranchName());
        audit.setBankBranchNameBn(branch.getBankBranchNameBn());
        audit.setBankBranchRoutingNumber(branch.getBankBranchRoutingNumber());
        audit.setAddressLine1(branch.getAddressLine1());
        audit.setAddressLine2(branch.getAddressLine2());
        audit.setAddressLine1Bn(branch.getAddressLine1Bn());
        audit.setAddressLine2Bn(branch.getAddressLine2Bn());
        audit.setPhone(branch.getPhone());
        audit.setMobile(branch.getMobile());
        audit.setEmail(branch.getEmail());
        audit.setUrl(branch.getUrl());
        audit.setCountryId(branch.getCountryId());
        audit.setDivisionId(branch.getDivisionId());
        audit.setDistrictId(branch.getDistrictId());
        return audit;
    }
}
