package com.eteacher.service.dto;

import com.eteacher.service.profile.EmployeeBankAccountProfile;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EmployeeBankAccountDto {

    @NotNull
    private Long employee;

    @NotNull
    private List<EmployeeBankAccountProfile> bankAccounts;
}
