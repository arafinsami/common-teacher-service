package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeePaymentArrear;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeePaymentArrearprofile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_PAYMENT_ARREAR;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeePaymentarrearHelper {

    @Resource
    private ActiveUserContext context;

    public Function<EmployeePaymentArrearprofile, EmployeePaymentArrear> saveProfile = e -> {
        EmployeePaymentArrear arrear = e.to();
        arrear.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return arrear;
    };

    public Function<EmployeePaymentArrearprofile, EmployeePaymentArrear> updateProfile = e -> {
        EmployeePaymentArrear arrear = e.update();
        arrear.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return arrear;
    };

    public List<EmployeePaymentArrear> save(List<EmployeePaymentArrearprofile> requests) {
        List<EmployeePaymentArrear> lists = requests
                .stream()
                .map(saveProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public List<EmployeePaymentArrear> update(List<EmployeePaymentArrearprofile> requests) {
        List<EmployeePaymentArrear> lists = requests
                .stream()
                .map(updateProfile)
                .collect(Collectors.toList());
        return lists;
    }

    private EmployeePaymentArrear findByArrearId(Long arrearId, List<EmployeePaymentArrear> lists) {
        for (EmployeePaymentArrear breakdown : lists) {
            if (breakdown.getId().equals(arrearId)) {
                return breakdown;
            }
        }
        return null;
    }

    public EmployeePaymentArrear findArrear(Long arrearId, Employee employee) {
        return Optional.ofNullable(findByArrearId(arrearId, employee.getPaymentArrears()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_PAYMENT_ARREAR + arrearId)
                );
    }

    public EmployeePaymentArrear delete(EmployeePaymentArrear arrear) {
        arrear.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        arrear.setRecordStatus(DELETED);
        return arrear;
    }
}
