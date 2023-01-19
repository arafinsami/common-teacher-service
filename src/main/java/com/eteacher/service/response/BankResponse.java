package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.Bank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankResponse {

    private Long id;

    private String bankName;

    private String bankNameBn;

    private String swiftCode;

    public static BankResponse from(Bank bank) {
        BankResponse response = new BankResponse();
        response.setId(bank.getId());
        response.setBankName(bank.getBankName());
        response.setBankNameBn(bank.getBankNameBn());
        response.setSwiftCode(bank.getSwiftCode());
        return response;
    }
}
