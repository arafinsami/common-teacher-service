package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeeBankAccount;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeBankAccountResponse {

    private Long id;

    private String bankAccountNumber;

    private Boolean isActiveForSalaryDisbursement;

    private Boolean isOperative;

    private Date lastDisbursementDate;

    private String bank;

    private String bankBranch;

    private String employee;

    public static EmployeeBankAccountResponse from(EmployeeBankAccount account) {
        EmployeeBankAccountResponse response = new EmployeeBankAccountResponse();
        response.setId(account.getId());
        response.setBankAccountNumber(account.getBankAccountNumber());
        response.setIsActiveForSalaryDisbursement(account.getIsActiveForSalaryDisbursement());
        response.setIsOperative(account.getIsOperative());
        response.setLastDisbursementDate(account.getLastDisbursementDate());
        response.setBank(account.getBank().getBankName());
        response.setBankBranch(account.getBankBranch().getBankBranchName());
        response.setEmployee(account.getEmployee().getEmployeeName());
        return response;
    }
}
