package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.EmployeeBankAccount;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeBankAccountProfile {

    private Long id;

    @NotBlank
    private String bankAccountNumber;

    @NotBlank
    private Boolean isActiveForSalaryDisbursement;

    @NotBlank
    private Boolean isOperative;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date lastDisbursementDate;

    @NotNull
    private Long bank;

    @NotNull
    private Long bankBranch;

    public EmployeeBankAccount to() {
        EmployeeBankAccount bankAccount = new EmployeeBankAccount();
        bankAccount.setId(id);
        bankAccount.setBankAccountNumber(bankAccountNumber);
        bankAccount.setIsActiveForSalaryDisbursement(isActiveForSalaryDisbursement);
        bankAccount.setIsOperative(isOperative);
        bankAccount.setLastDisbursementDate(lastDisbursementDate);
        return bankAccount;
    }
}
