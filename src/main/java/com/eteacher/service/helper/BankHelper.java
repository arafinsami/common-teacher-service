package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.Bank;
import com.eteacher.service.entity.commonteacher.BankBranch;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.profile.BankBranchProfile;
import com.eteacher.service.security.ActiveUserContext;
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
public class BankHelper {

    @Resource
    private ActiveUserContext context;

    public void getSaveData(Bank bank) {
        bank.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        bank.setRecordStatus(RecordStatus.DRAFT);
    }

    public void getUpdateData(Bank bank, RecordStatus status) {
        bank.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        bank.setRecordStatus(status);
    }

    public Function<BankBranchProfile, BankBranch> saveBranch = r -> {
        BankBranch branch = r.to();
        branch.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        branch.setRecordStatus(DRAFT);
        return branch;
    };

    public Function<BankBranchProfile, BankBranch> updateBranch = r -> {
        BankBranch branch = r.to();
        branch.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        branch.setRecordStatus(ACTIVE);
        return branch;
    };

    public List<BankBranch> saveBranches(List<BankBranchProfile> profiles) {
        List<BankBranch> applications = profiles.stream()
                .map(saveBranch)
                .collect(Collectors.toList());
        return applications;
    }

    public List<BankBranch> updateBranches(List<BankBranchProfile> profiles) {
        List<BankBranch> applications = profiles.stream()
                .map(updateBranch)
                .collect(Collectors.toList());
        return applications;
    }

    public List<BankBranch> deleteBranch(BankBranch branch) {
        branch.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        branch.setRecordStatus(DELETED);
        return Arrays.asList(branch);
    }

    public BankBranch findByBranchId(Long id, List<BankBranch> branches) {
        for (BankBranch branch : branches) {
            if (branch.getId().equals(id)) {
                return branch;
            }
        }
        return null;
    }
}
