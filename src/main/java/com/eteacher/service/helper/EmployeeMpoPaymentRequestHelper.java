package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeMpoPaymentRequest;
import com.eteacher.service.entity.mpo.EmployeeMpoPaymentRequestBreakdown;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeMpoPaymentRequestBreakdownProfile;
import com.eteacher.service.profile.EmployeeMpoPaymentRequestProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_MPO_PAYMENT_REQUEST;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_MPO_PAYMENT_REQUEST_BREAKDOWN;
import static com.eteacher.service.enums.RecordStatus.*;

@Component
@RequiredArgsConstructor
public class EmployeeMpoPaymentRequestHelper {

    @Resource
    private ActiveUserContext context;

    public Function<EmployeeMpoPaymentRequestProfile, EmployeeMpoPaymentRequest> savePaymentRequest = p -> {
        EmployeeMpoPaymentRequest paymentRequest = p.to();
        paymentRequest.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        paymentRequest.setRecordStatus(DRAFT);
        return paymentRequest;
    };

    public Function<EmployeeMpoPaymentRequestProfile, EmployeeMpoPaymentRequest> updatePaymentRequest = p -> {
        EmployeeMpoPaymentRequest paymentRequest = p.to();
        paymentRequest.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        paymentRequest.setRecordStatus(ACTIVE);
        return paymentRequest;
    };

    public Function<EmployeeMpoPaymentRequestBreakdownProfile, EmployeeMpoPaymentRequestBreakdown> saveBreakdownProfile = b -> {
        EmployeeMpoPaymentRequestBreakdown breakdown = b.to();
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        breakdown.setRecordStatus(DRAFT);
        return breakdown;
    };

    public Function<EmployeeMpoPaymentRequestBreakdownProfile, EmployeeMpoPaymentRequestBreakdown> updateBreakdownProfile = b -> {
        EmployeeMpoPaymentRequestBreakdown breakdown = b.to();
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        breakdown.setRecordStatus(ACTIVE);
        return breakdown;
    };

    public List<EmployeeMpoPaymentRequest> save(List<EmployeeMpoPaymentRequestProfile> profiles) {
        List<EmployeeMpoPaymentRequest> applications = profiles.stream()
                .map(savePaymentRequest)
                .collect(Collectors.toList());
        return applications;
    }

    public EmployeeMpoPaymentRequestBreakdown save(EmployeeMpoPaymentRequestBreakdownProfile profile) {
        EmployeeMpoPaymentRequestBreakdown reviewer = saveBreakdownProfile.apply(profile);
        return reviewer;
    }

    public List<EmployeeMpoPaymentRequest> update(List<EmployeeMpoPaymentRequestProfile> profiles) {
        List<EmployeeMpoPaymentRequest> applications = profiles.stream()
                .map(updatePaymentRequest)
                .collect(Collectors.toList());
        return applications;
    }

    public EmployeeMpoPaymentRequestBreakdown update(EmployeeMpoPaymentRequestBreakdownProfile profile) {
        EmployeeMpoPaymentRequestBreakdown reviewer = updateBreakdownProfile.apply(profile);
        return reviewer;
    }

    private EmployeeMpoPaymentRequest findByPaymentId(Long paymentId, List<EmployeeMpoPaymentRequest> lists) {
        for (EmployeeMpoPaymentRequest paymentRequest : lists) {
            if (paymentRequest.getId().equals(paymentId)) {
                return paymentRequest;
            }
        }
        return null;
    }

    private EmployeeMpoPaymentRequestBreakdown findByBreakdownId(Long breakdownId, List<EmployeeMpoPaymentRequestBreakdown> lists) {
        for (EmployeeMpoPaymentRequestBreakdown breakdown : lists) {
            if (breakdown.getId().equals(breakdownId)) {
                return breakdown;
            }
        }
        return null;
    }

    public EmployeeMpoPaymentRequest findPayment(Long breakdownId, Employee employee) {
        return Optional.ofNullable(findByPaymentId(breakdownId, employee.getMpoPaymentRequests()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_MPO_PAYMENT_REQUEST + breakdownId)
                );
    }

    public EmployeeMpoPaymentRequestBreakdown findBreakdown(Long breakdownId, EmployeeMpoPaymentRequest paymentRequest) {
        return Optional.ofNullable(findByBreakdownId(breakdownId, paymentRequest.getRequestBreakdowns()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_MPO_PAYMENT_REQUEST_BREAKDOWN + breakdownId)
                );
    }

    public EmployeeMpoPaymentRequest deleteOne(EmployeeMpoPaymentRequest paymentRequest) {
        paymentRequest.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        paymentRequest.setRecordStatus(DELETED);
        return paymentRequest;
    }

    public EmployeeMpoPaymentRequestBreakdown deleteOne(EmployeeMpoPaymentRequestBreakdown breakdown) {
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        breakdown.setRecordStatus(DELETED);
        return breakdown;
    }
}
