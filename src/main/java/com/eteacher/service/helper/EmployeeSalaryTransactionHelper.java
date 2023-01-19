package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeSalaryTransaction;
import com.eteacher.service.entity.mpo.EmployeeSalaryTransactionBreakdown;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeSalaryTransactionBreakdownProfile;
import com.eteacher.service.profile.EmployeeSalaryTransactionProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_TRANSFER_RECORD;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeSalaryTransactionHelper {

    @Resource
    private ActiveUserContext context;

    public Function<EmployeeSalaryTransactionProfile, EmployeeSalaryTransaction> saveSalary = r -> {
        EmployeeSalaryTransaction transaction = r.to();
        transaction.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return transaction;
    };

    public Function<EmployeeSalaryTransactionBreakdownProfile, EmployeeSalaryTransactionBreakdown> saveBreakDownProfile = r -> {
        EmployeeSalaryTransactionBreakdown breakdown = r.to();
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return breakdown;
    };

    public Function<EmployeeSalaryTransactionProfile, EmployeeSalaryTransaction> updateSalary = r -> {
        EmployeeSalaryTransaction transaction = r.update();
        transaction.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return transaction;
    };

    public Function<EmployeeSalaryTransactionBreakdownProfile, EmployeeSalaryTransactionBreakdown> updateBreakDownProfile = r -> {
        EmployeeSalaryTransactionBreakdown breakdown = r.update();
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return breakdown;
    };

    public List<EmployeeSalaryTransaction> save(List<EmployeeSalaryTransactionProfile> profiles) {
        List<EmployeeSalaryTransaction> transactions = profiles
                .stream()
                .map(saveSalary)
                .collect(Collectors.toList());
        return transactions;
    }

    public EmployeeSalaryTransactionBreakdown save(EmployeeSalaryTransactionBreakdownProfile profile) {
        EmployeeSalaryTransactionBreakdown breakdown = saveBreakDownProfile.apply(profile);
        return breakdown;
    }

    public List<EmployeeSalaryTransaction> update(List<EmployeeSalaryTransactionProfile> profiles) {
        List<EmployeeSalaryTransaction> transactions = profiles
                .stream()
                .map(updateSalary)
                .collect(Collectors.toList());
        return transactions;
    }

    public EmployeeSalaryTransactionBreakdown update(EmployeeSalaryTransactionBreakdownProfile profile) {
        EmployeeSalaryTransactionBreakdown breakdown = updateBreakDownProfile.apply(profile);
        return breakdown;
    }

    private EmployeeSalaryTransaction findByTransactionId(Long transactionId, List<EmployeeSalaryTransaction> lists) {
        for (EmployeeSalaryTransaction transaction : lists) {
            if (transaction.getId().equals(transactionId)) {
                return transaction;
            }
        }
        return null;
    }

    private EmployeeSalaryTransactionBreakdown findByBreakdownId(Long breakdownId, List<EmployeeSalaryTransactionBreakdown> lists) {
        for (EmployeeSalaryTransactionBreakdown breakdown : lists) {
            if (breakdown.getId().equals(breakdownId)) {
                return breakdown;
            }
        }
        return null;
    }

    public EmployeeSalaryTransaction findTransaction(Long transactionId, Employee employee) {
        return Optional.ofNullable(findByTransactionId(transactionId, employee.getSalaryTransactions()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_TRANSFER_RECORD + transactionId)
                );
    }

    public EmployeeSalaryTransactionBreakdown findBreakdown(Long breakdownId, EmployeeSalaryTransaction transaction) {
        return Optional.ofNullable(findByBreakdownId(breakdownId, transaction.getBreakdowns()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_TRANSFER_RECORD + breakdownId)
                );
    }

    public EmployeeSalaryTransaction deleteOne(EmployeeSalaryTransaction transaction) {
        transaction.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        transaction.setRecordStatus(DELETED);
        return transaction;
    }

    public EmployeeSalaryTransactionBreakdown deleteOne(EmployeeSalaryTransactionBreakdown breakdown) {
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        breakdown.setRecordStatus(DELETED);
        return breakdown;
    }
}
