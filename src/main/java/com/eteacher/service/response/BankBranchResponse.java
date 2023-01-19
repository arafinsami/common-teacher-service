package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.BankBranch;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankBranchResponse {

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

    public static BankBranchResponse response(BankBranch branch) {
        BankBranchResponse response = new BankBranchResponse();
        response.setId(branch.getId());
        response.setBankBranchName(branch.getBankBranchName());
        response.setBankBranchNameBn(branch.getBankBranchNameBn());
        response.setBankBranchRoutingNumber(branch.getBankBranchRoutingNumber());
        response.setAddressLine1(branch.getAddressLine1());
        response.setAddressLine2(branch.getAddressLine2());
        response.setAddressLine1Bn(branch.getAddressLine1Bn());
        response.setAddressLine2Bn(branch.getAddressLine2Bn());
        response.setPhone(branch.getPhone());
        response.setMobile(branch.getMobile());
        response.setEmail(branch.getEmail());
        response.setUrl(branch.getUrl());
        response.setCountryId(branch.getCountryId());
        response.setDivisionId(branch.getDivisionId());
        response.setDistrictId(branch.getDistrictId());
        return response;
    }
}
