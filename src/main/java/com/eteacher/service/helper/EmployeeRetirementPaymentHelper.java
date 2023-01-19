package com.eteacher.service.helper;

import com.eteacher.service.dto.EmployeeRetirementPaymentEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeRetirementPayment;
import com.eteacher.service.entity.mpo.EmployeeRetirementPaymentBreakdown;
import com.eteacher.service.entity.mpo.EmployeeRetirementPaymentEncloser;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeRetirementPaymentBreakdownProfile;
import com.eteacher.service.profile.EmployeeRetirementPaymentEncloserProfile;
import com.eteacher.service.profile.EmployeeRetirementPaymentProfile;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.utils.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_RETIREMENT_PAYMENT;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_RETIREMENT_PAYMENT_BREAKDOWN;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeRetirementPaymentHelper extends FileUpload {

    @Resource
    private ActiveUserContext context;

    @Resource
    private Environment env;

    public Function<EmployeeRetirementPaymentProfile, EmployeeRetirementPayment> savePayment = r -> {
        EmployeeRetirementPayment payment = r.to();
        payment.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return payment;
    };

    public Function<EmployeeRetirementPaymentBreakdownProfile, EmployeeRetirementPaymentBreakdown> saveBreakdownProfile = r -> {
        EmployeeRetirementPaymentBreakdown breakdown = r.to();
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return breakdown;
    };

    public Function<EmployeeRetirementPaymentEncloserProfile, EmployeeRetirementPaymentEncloser> saveEncloserProfiles = r -> {
        EmployeeRetirementPaymentEncloser encloser = r.to();
        return encloser;
    };

    public Function<EmployeeRetirementPaymentProfile, EmployeeRetirementPayment> updatePayment = r -> {
        EmployeeRetirementPayment payment = r.update();
        payment.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return payment;
    };

    public Function<EmployeeRetirementPaymentBreakdownProfile, EmployeeRetirementPaymentBreakdown> updateBreakdownProfile = r -> {
        EmployeeRetirementPaymentBreakdown breakdown = r.update();
        breakdown.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return breakdown;
    };

    public List<EmployeeRetirementPayment> save(List<EmployeeRetirementPaymentProfile> profiles) {
        List<EmployeeRetirementPayment> payments = profiles
                .stream()
                .map(savePayment)
                .collect(Collectors.toList());
        return payments;
    }

    public EmployeeRetirementPaymentBreakdown save(EmployeeRetirementPaymentBreakdownProfile profile) {
        EmployeeRetirementPaymentBreakdown breakdown = saveBreakdownProfile.apply(profile);
        return breakdown;
    }

    public List<EmployeeRetirementPaymentEncloser> save(MultipartFile[] files, EmployeeRetirementPaymentEncloserDto dto) {
        List<EmployeeRetirementPaymentEncloser> lists = dto.getProfiles()
                .stream()
                .map(saveEncloserProfiles)
                .collect(Collectors.toList());

        for (int index = 0; index < lists.size(); index++) {
            for (EmployeeRetirementPaymentEncloser encloser : lists) {
                if (encloser.getEncloserUrl().equals(files[index].getOriginalFilename())) {
                    encloser.setEncloserUrl(
                            upload(files[index], env.getProperty("employeeMpoRetirementEncloserUploadPath"))
                    );
                }
            }
        }
        return lists;
    }

    public List<EmployeeRetirementPayment> update(List<EmployeeRetirementPaymentProfile> profiles) {
        List<EmployeeRetirementPayment> payments = profiles.stream()
                .map(updatePayment)
                .collect(Collectors.toList());
        return payments;
    }

    public EmployeeRetirementPaymentBreakdown update(EmployeeRetirementPaymentBreakdownProfile profile) {
        EmployeeRetirementPaymentBreakdown breakdown = updateBreakdownProfile.apply(profile);
        return breakdown;
    }

    private EmployeeRetirementPayment findByPaymentId(Long paymentId, List<EmployeeRetirementPayment> lists) {
        for (EmployeeRetirementPayment payment : lists) {
            if (payment.getId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }

    private EmployeeRetirementPaymentBreakdown findByBreakdownId(Long breakdownId, List<EmployeeRetirementPaymentBreakdown> lists) {
        for (EmployeeRetirementPaymentBreakdown breakdown : lists) {
            if (breakdown.getId().equals(breakdownId)) {
                return breakdown;
            }
        }
        return null;
    }

    public EmployeeRetirementPayment findPayment(Long paymentId, Employee employee) {
        return Optional.ofNullable(findByPaymentId(paymentId, employee.getRetirementPayments()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_RETIREMENT_PAYMENT + paymentId)
                );
    }

    public EmployeeRetirementPaymentBreakdown findBreakdown(Long breakdownId, EmployeeRetirementPayment payment) {
        return Optional.ofNullable(findByBreakdownId(breakdownId, payment.getBreakdowns()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_RETIREMENT_PAYMENT_BREAKDOWN + breakdownId)
                );
    }

    public EmployeeRetirementPayment delete(EmployeeRetirementPayment payment) {
        payment.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        payment.setRecordStatus(DELETED);
        return payment;
    }

    public EmployeeRetirementPaymentBreakdown delete(EmployeeRetirementPaymentBreakdown breakdown) {
        breakdown.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        breakdown.setRecordStatus(DELETED);
        return breakdown;
    }
}
