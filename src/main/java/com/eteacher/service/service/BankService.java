package com.eteacher.service.service;

import com.eteacher.service.audit.BankAudit;
import com.eteacher.service.audit.BankBranchAudit;
import com.eteacher.service.entity.commonteacher.Bank;
import com.eteacher.service.entity.commonteacher.BankBranch;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.BankHelper;
import com.eteacher.service.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class BankService extends BaseService {

    private final BankRepository repository;

    private final ActionLogService actionLogService;

    private final BankHelper helper;

    public Optional<Bank> findById(Long id) {
        Optional<Bank> bank = repository.findById(id);
        return bank;
    }

    public Optional<BankBranch> findByBankBranchName(String branchName, Bank b) {
        Optional<BankBranch> branches = repository.findByBankBranchName(branchName, b);
        return branches;
    }

    public Optional<BankBranch> findByBankBranchId(Long branchId) {
        Optional<BankBranch> branches = repository.findByBankBranchId(branchId);
        return branches;
    }

    public Optional<Bank> findByBankName(String bankName) {
        Optional<Bank> bank = repository.findByBankName(bankName);
        return bank;
    }

    @Transactional
    public Bank save(Bank bank) {
        helper.getSaveData(bank);
        Bank saveBank = repository.save(bank);
        BankAudit audit = BankAudit.from(saveBank);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                BANK_SAVE,
                objectToJson(audit)
        );
        return saveBank;
    }

    @Transactional
    public Bank saveBranch(Bank bank) {
        Bank b = repository.save(bank);
        List<BankBranchAudit> audit = b.getBankBranches()
                .stream()
                .map(BankBranchAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                b.getId(),
                BANK_BRANCH_SAVE,
                objectToJson(audit)
        );
        return b;
    }

    @Transactional
    public Bank update(Bank bank) {
        helper.getUpdateData(bank, RecordStatus.ACTIVE);
        Bank b = repository.save(bank);
        BankAudit audit = BankAudit.from(b);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                BANK_UPDATE,
                objectToJson(audit)
        );
        return b;
    }

    @Transactional
    public Bank updateBranch(Bank bank) {
        Bank b = repository.save(bank);
        List<BankBranchAudit> audit = b.getBankBranches()
                .stream()
                .map(BankBranchAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                b.getId(),
                BANK_BRANCH_UPDATE,
                objectToJson(audit)
        );
        return b;
    }

    @Transactional
    public void delete(Bank bank) {
        helper.getUpdateData(bank, RecordStatus.DELETED);
        Bank b = repository.save(bank);
        BankAudit audit = BankAudit.from(b);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                BANK_DELETE,
                objectToJson(audit)
        );
    }

    @Transactional
    public void deleteBranch(Bank bank) {
        Bank b = repository.save(bank);
        List<BankBranchAudit> audits = b.getBankBranches().stream()
                .map(BankBranchAudit::audit)
                .collect(Collectors.toList());
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                b.getId(),
                BANK_BRANCH_DELETE,
                objectToJson(audits)
        );
    }
}
