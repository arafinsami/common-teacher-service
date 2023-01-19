package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.PaymentMethod;
import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.govtteacher.EmployeePensionTransaction;
import com.eteacher.service.entity.govtteacher.EmployeePensionTransactionBreakdown;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeePensionTransactionBreakdownProfile;
import com.eteacher.service.profile.EmployeePensionTransactionProfile;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.service.PaymentMethodService;
import com.eteacher.service.service.PaymentPeriodService;
import com.eteacher.service.service.SalaryScaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_PENSION_TRANSACTION;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_PENSION_TRANSACTION_BREAKDOWN;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeePensionTransactionHelper {

    @Resource
    private ActiveUserContext context;

    @Resource
    private PaymentMethodService paymentMethodService;

    @Resource
    private PaymentPeriodService paymentPeriodService;

    @Resource
    private SalaryScaleService salaryScaleService;

    public Function<EmployeePensionTransactionProfile, EmployeePensionTransaction> saveTransaction = r -> {
        PaymentMethod paymentMethod = paymentMethodService.findById(r.getPaymentMethod()).orElseThrow(ResourceNotFoundException::new);
        PaymentPeriod paymentPeriod = paymentPeriodService.findById(r.getPaymentPeriod()).orElseThrow(ResourceNotFoundException::new);
        SalaryScale salaryScale = salaryScaleService.findById(r.getSalaryScale()).orElseThrow(ResourceNotFoundException::new);
        EmployeePensionTransaction transaction = r.to();
        transaction.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        transaction.setPaymentMethod(paymentMethod);
        transaction.setPaymentPeriod(paymentPeriod);
        transaction.setSalaryScale(salaryScale);
        return transaction;
    };

    public Function<EmployeePensionTransactionProfile, EmployeePensionTransaction> updateTransaction = r -> {
        PaymentMethod paymentMethod = paymentMethodService.findById(r.getPaymentMethod()).orElseThrow(ResourceNotFoundException::new);
        PaymentPeriod paymentPeriod = paymentPeriodService.findById(r.getPaymentPeriod()).orElseThrow(ResourceNotFoundException::new);
        SalaryScale salaryScale = salaryScaleService.findById(r.getSalaryScale()).orElseThrow(ResourceNotFoundException::new);
        EmployeePensionTransaction transaction = r.update();
        transaction.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        transaction.setPaymentMethod(paymentMethod);
        transaction.setPaymentPeriod(paymentPeriod);
        transaction.setSalaryScale(salaryScale);
        return transaction;
    };

    public Function<EmployeePensionTransactionBreakdownProfile, EmployeePensionTransactionBreakdown> saveBreakDown = r -> {
        EmployeePensionTransactionBreakdown breakdown = r.to();
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return breakdown;
    };

    public Function<EmployeePensionTransactionBreakdownProfile, EmployeePensionTransactionBreakdown> updateBreakDown = r -> {
        EmployeePensionTransactionBreakdown breakdown = r.update();
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return breakdown;
    };

    public List<EmployeePensionTransaction> saveTransactions(List<EmployeePensionTransactionProfile> profiles) {
        List<EmployeePensionTransaction> transactions = profiles.stream()
                .map(saveTransaction)
                .collect(Collectors.toList());
        return transactions;
    }

    public List<EmployeePensionTransaction> updateTransactions(List<EmployeePensionTransactionProfile> profiles) {
        List<EmployeePensionTransaction> applications = profiles.stream()
                .map(updateTransaction)
                .collect(Collectors.toList());
        return applications;
    }

    public EmployeePensionTransactionBreakdown saveBreakDowns(EmployeePensionTransactionBreakdownProfile profile) {
        EmployeePensionTransactionBreakdown breakdown = saveBreakDown.apply(profile);
        return breakdown;
    }

    public EmployeePensionTransactionBreakdown updateBreakDowns(EmployeePensionTransactionBreakdownProfile profile) {
        EmployeePensionTransactionBreakdown breakdown = updateBreakDown.apply(profile);
        return breakdown;
    }

    public List<EmployeePensionTransaction> delete(EmployeePensionTransaction transaction) {
        transaction.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        transaction.setRecordStatus(DELETED);
        return Arrays.asList(transaction);
    }

    public EmployeePensionTransactionBreakdown deleteBreakdown(EmployeePensionTransactionBreakdown breakdown) {
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        breakdown.setRecordStatus(DELETED);
        return breakdown;
    }

    private EmployeePensionTransaction findByTransactionId(Long transactionId, List<EmployeePensionTransaction> lists) {
        for (EmployeePensionTransaction transaction : lists) {
            if (transaction.getId().equals(transactionId)) {
                return transaction;
            }
        }
        return null;
    }

    private EmployeePensionTransactionBreakdown findByBreakdownId(Long breakdownId, List<EmployeePensionTransactionBreakdown> lists) {
        for (EmployeePensionTransactionBreakdown breakdown : lists) {
            if (breakdown.getId().equals(breakdownId)) {
                return breakdown;
            }
        }
        return null;
    }

    public EmployeePensionTransaction findPensionTransaction(Long transactionId, Employee employee) {
        return Optional.ofNullable(findByTransactionId(transactionId, employee.getPensionTransactions()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_PENSION_TRANSACTION + transactionId)
                );
    }

    public EmployeePensionTransactionBreakdown findBreakdown(Long breakdownId, EmployeePensionTransaction transaction) {
        return Optional.ofNullable(findByBreakdownId(breakdownId, transaction.getTransactionBreakdowns()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_PENSION_TRANSACTION_BREAKDOWN + breakdownId)
                );
    }
}
