package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.mpo.EmployeeSalaryIncrement;
import com.eteacher.service.entity.mpo.EmployeeSalaryIncrementBreakdown;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeSalaryIncrementBreakdownProfile;
import com.eteacher.service.profile.EmployeeSalaryIncrementProfile;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.service.SalaryScaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_SALARY_BREAKDOWN;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_SALARY_INCREMENT;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeSalaryIncrementHelper {
    @Resource
    private SalaryScaleService salaryScaleService;

    @Resource
    private ActiveUserContext context;

    public Function<EmployeeSalaryIncrementProfile, EmployeeSalaryIncrement> saveIncrementProfile = converter -> {
        EmployeeSalaryIncrement increment = converter.to();
        SalaryScale salaryScaleFrom = salaryScaleService.findById(converter.getSalaryScaleFrom())
                .orElseThrow(ResourceNotFoundException::new);
        SalaryScale salaryScale = salaryScaleService.findById(converter.getSalaryScale())
                .orElseThrow(ResourceNotFoundException::new);
        increment.setSalaryScale(salaryScaleFrom);
        increment.setSalaryScal1(salaryScale);
        increment.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return increment;
    };

    public Function<EmployeeSalaryIncrementBreakdownProfile, EmployeeSalaryIncrementBreakdown> saveBreakdownProfile = r -> {
        EmployeeSalaryIncrementBreakdown breakdown = r.to();
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return breakdown;
    };

    public Function<EmployeeSalaryIncrementProfile, EmployeeSalaryIncrement> updateIncrementProfile = converter -> {
        EmployeeSalaryIncrement increment = converter.update();
        SalaryScale salaryScaleFrom = salaryScaleService.findById(converter.getSalaryScaleFrom())
                .orElseThrow(ResourceNotFoundException::new);
        SalaryScale salaryScale = salaryScaleService.findById(converter.getSalaryScale())
                .orElseThrow(ResourceNotFoundException::new);
        increment.setSalaryScale(salaryScaleFrom);
        increment.setSalaryScal1(salaryScale);
        increment.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        return increment;
    };

    public Function<EmployeeSalaryIncrementBreakdownProfile, EmployeeSalaryIncrementBreakdown> updateBreakdownProfile = r -> {
        EmployeeSalaryIncrementBreakdown breakdown = r.update();
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return breakdown;
    };

    public List<EmployeeSalaryIncrement> save(List<EmployeeSalaryIncrementProfile> profiles) {
        List<EmployeeSalaryIncrement> lists = profiles.stream()
                .map(saveIncrementProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public EmployeeSalaryIncrementBreakdown save(EmployeeSalaryIncrementBreakdownProfile profile) {
        EmployeeSalaryIncrementBreakdown reviewer = saveBreakdownProfile.apply(profile);
        return reviewer;
    }

    public List<EmployeeSalaryIncrement> update(List<EmployeeSalaryIncrementProfile> profiles) {
        List<EmployeeSalaryIncrement> lists = profiles.stream()
                .map(updateIncrementProfile)
                .collect(Collectors.toList());
        return lists;
    }

    public EmployeeSalaryIncrementBreakdown update(EmployeeSalaryIncrementBreakdownProfile profile) {
        EmployeeSalaryIncrementBreakdown reviewer = updateBreakdownProfile.apply(profile);
        return reviewer;
    }

    private EmployeeSalaryIncrement findByIncrementId(Long incrementId, List<EmployeeSalaryIncrement> lists) {
        for (EmployeeSalaryIncrement increment : lists) {
            if (increment.getId().equals(incrementId)) {
                return increment;
            }
        }
        return null;
    }

    private EmployeeSalaryIncrementBreakdown findByBreakdownId(Long breakdownId, List<EmployeeSalaryIncrementBreakdown> lists) {
        for (EmployeeSalaryIncrementBreakdown breakdown : lists) {
            if (breakdown.getId().equals(breakdownId)) {
                return breakdown;
            }
        }
        return null;
    }

    public EmployeeSalaryIncrement findIncrement(Long incrementId, Employee employee) {
        return Optional.ofNullable(findByIncrementId(incrementId, employee.getSalaryIncrements()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_SALARY_INCREMENT + incrementId)
                );
    }

    public EmployeeSalaryIncrementBreakdown findBreakdown(Long breakdownId, EmployeeSalaryIncrement increment) {
        return Optional.ofNullable(findByBreakdownId(breakdownId, increment.getBreakdowns()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_SALARY_BREAKDOWN + breakdownId)
                );
    }

    public EmployeeSalaryIncrement delete(EmployeeSalaryIncrement increment) {
        increment.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        increment.setRecordStatus(DELETED);
        return increment;
    }

    public EmployeeSalaryIncrementBreakdown delete(EmployeeSalaryIncrementBreakdown breakdown) {
        breakdown.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
        breakdown.setRecordStatus(DELETED);
        return breakdown;
    }
}
