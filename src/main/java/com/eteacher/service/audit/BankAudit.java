package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.Bank;
import lombok.Data;

@Data
public class BankAudit {

    private Long id;

    private String bankName;

    private String bankNameBn;

    private String swiftCode;

    public static BankAudit from(Bank bank) {
        BankAudit audit = new BankAudit();
        audit.setId(bank.getId());
        audit.setBankName(bank.getBankName());
        audit.setBankNameBn(bank.getBankNameBn());
        audit.setSwiftCode(bank.getSwiftCode());
        return audit;
    }

}
