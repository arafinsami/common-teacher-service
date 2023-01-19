package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeeBankAccount;
import lombok.Data;

import java.util.Date;

import static com.eteacher.service.utils.StringUtils.nonNull;

@Data
public class EmployeeBankAccountAudit {

  private Long id;

  private String bankAccountNumber;

  private Boolean isActiveForSalaryDisbursement;

  private Boolean isOperative;

  private Date lastDisbursementDate;

  private Long bank;

  private Long bankBranch;

  public static EmployeeBankAccountAudit from(EmployeeBankAccount bankAccount) {
    EmployeeBankAccountAudit audit = new EmployeeBankAccountAudit();
    audit.setId(bankAccount.getId());
    audit.setBankAccountNumber(bankAccount.getBankAccountNumber());
    audit.setIsActiveForSalaryDisbursement(bankAccount.getIsActiveForSalaryDisbursement());
    audit.setIsOperative(bankAccount.getIsOperative());
    audit.setLastDisbursementDate(bankAccount.getLastDisbursementDate());
    audit.setBank(nonNull(
            bankAccount.getBank()) ?
            bankAccount.getBank().getId()
            : 0l
    );
    audit.setBankBranch(nonNull(
            bankAccount.getBankBranch()) ?
            bankAccount.getBankBranch().getId()
            : 0l
    );
    return audit;
  }
}
