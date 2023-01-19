package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.Bank;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class BankDto {

    private Long id;

    @NotBlank
    private String bankName;

    @NotBlank
    private String bankNameBn;

    @NotBlank
    private String swiftCode;

    public Bank to() {
        Bank bank = new Bank();
        bank.setBankName(bankName);
        bank.setBankNameBn(bankNameBn);
        bank.setSwiftCode(swiftCode);
        return bank;
    }

    public void update(Bank bank) {
        bank.setBankName(bankName);
        bank.setBankNameBn(bankNameBn);
        bank.setSwiftCode(swiftCode);
    }
}
