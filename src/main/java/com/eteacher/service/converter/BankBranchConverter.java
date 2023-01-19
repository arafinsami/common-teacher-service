package com.eteacher.service.converter;

import lombok.Data;

@Data
public class BankBranchConverter {

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

    private long bankId;

    private Long countryId;

    private Long districtId;

    private Long divisionId;
}
