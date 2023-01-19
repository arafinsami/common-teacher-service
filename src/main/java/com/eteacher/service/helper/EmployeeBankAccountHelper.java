package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.Bank;
import com.eteacher.service.entity.commonteacher.BankBranch;
import com.eteacher.service.entity.commonteacher.EmployeeBankAccount;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeBankAccountProfile;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.enums.RecordStatus.*;

@Component
@RequiredArgsConstructor
public class EmployeeBankAccountHelper {

    @Resource
    private ActiveUserContext context;

    @Resource
    private BankService bankService;

    public Function<EmployeeBankAccountProfile, EmployeeBankAccount> saveProfile = converter -> {
        EmployeeBankAccount bankAccount = converter.to();
        Bank bank = bankService.findById(converter.getBank())
                .orElseThrow(ResourceNotFoundException::new);
        BankBranch branch = bank.getBankBranches().stream()
                .filter(f -> converter.getBankBranch().equals(f.getId()))
                .findAny()
                .orElseThrow(ResourceNotFoundException::new);
        bankAccount.setBank(bank);
        bankAccount.setBankBranch(branch);
        bankAccount.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        bankAccount.setRecordStatus(DRAFT);
        return bankAccount;
    };

    public Function<EmployeeBankAccountProfile, EmployeeBankAccount> updateProfile = converter -> {
        EmployeeBankAccount bankAccount = converter.to();
        Bank bank = bankService.findById(converter.getBank())
                .orElseThrow(ResourceNotFoundException::new);
        BankBranch branch = bank.getBankBranches().stream()
                .filter(f -> converter.getBankBranch().equals(f.getId()))
                .findAny()
                .orElseThrow(ResourceNotFoundException::new);
        bankAccount.setBank(bank);
        bankAccount.setBankBranch(branch);
        bankAccount.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        bankAccount.setRecordStatus(ACTIVE);
        return bankAccount;
    };

    public List<EmployeeBankAccount> save(List<EmployeeBankAccountProfile> profiles) {
        List<EmployeeBankAccount> lists = profiles.stream()
                .map(saveProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeBankAccount> update(List<EmployeeBankAccountProfile> profiles) {
        List<EmployeeBankAccount> lists = profiles.stream()
                .map(updateProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeeBankAccount> deleteOne(EmployeeBankAccount bankAccount) {
        Bank bank = bankService.findById(bankAccount.getBank().getId())
                .orElseThrow(ResourceNotFoundException::new);
        BankBranch branch = bank.getBankBranches().stream()
                .filter(f -> bankAccount.getBankBranch().getId().equals(f.getId()))
                .findAny()
                .orElseThrow(ResourceNotFoundException::new);
        bankAccount.setBank(bank);
        bankAccount.setBankBranch(branch);
        bankAccount.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        bankAccount.setRecordStatus(DELETED);
        return Arrays.asList(bankAccount);
    }

    public EmployeeBankAccount findByAccountId(Long accountId, List<EmployeeBankAccount> accounts) {
        for (EmployeeBankAccount account : accounts) {
            if (account.getId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }
}
