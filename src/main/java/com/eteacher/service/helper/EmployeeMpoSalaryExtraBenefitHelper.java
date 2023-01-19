package com.eteacher.service.helper;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeMpoSalaryExtraBenefit;
import com.eteacher.service.entity.mpo.EmployeeMpoSalaryExtraBenefitBreakdown;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeMpoSalaryExtraBenefitBreakdownProfile;
import com.eteacher.service.profile.EmployeeMpoSalaryExtraBenefitProfile;
import com.eteacher.service.security.ActiveUserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_SALARY_EXTRA_BENEFIT;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE_SALARY_EXTRA_BENEFIT_BREAKDOWN;
import static com.eteacher.service.enums.RecordStatus.DELETED;

@Component
@RequiredArgsConstructor
public class EmployeeMpoSalaryExtraBenefitHelper {

    @Resource
    private ActiveUserContext context;

    public Function<EmployeeMpoSalaryExtraBenefitProfile, EmployeeMpoSalaryExtraBenefit> saveSalaryProfile = r -> {
        EmployeeMpoSalaryExtraBenefit benefit = r.to();
        benefit.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return benefit;
    };

    public Function<EmployeeMpoSalaryExtraBenefitBreakdownProfile, EmployeeMpoSalaryExtraBenefitBreakdown> saveBreakdownProfile = r -> {
        EmployeeMpoSalaryExtraBenefitBreakdown breakdown = r.to();
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return breakdown;
    };

    public Function<EmployeeMpoSalaryExtraBenefitProfile, EmployeeMpoSalaryExtraBenefit> updateSalaryrofile = r -> {
        EmployeeMpoSalaryExtraBenefit benefit = r.update();
        benefit.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return benefit;
    };

    public Function<EmployeeMpoSalaryExtraBenefitBreakdownProfile, EmployeeMpoSalaryExtraBenefitBreakdown> updateBreakdownProfile = r -> {
        EmployeeMpoSalaryExtraBenefitBreakdown breakdown = r.update();
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        return breakdown;
    };

    public List<EmployeeMpoSalaryExtraBenefit> save(List<EmployeeMpoSalaryExtraBenefitProfile> profiles) {
        List<EmployeeMpoSalaryExtraBenefit> applications = profiles
                .stream()
                .map(saveSalaryProfile)
                .collect(Collectors.toList());
        return applications;
    }

    public EmployeeMpoSalaryExtraBenefitBreakdown save(EmployeeMpoSalaryExtraBenefitBreakdownProfile profile) {
        EmployeeMpoSalaryExtraBenefitBreakdown reviewer = saveBreakdownProfile.apply(profile);
        return reviewer;
    }

    public List<EmployeeMpoSalaryExtraBenefit> update(List<EmployeeMpoSalaryExtraBenefitProfile> profiles) {
        List<EmployeeMpoSalaryExtraBenefit> applications = profiles
                .stream()
                .map(updateSalaryrofile)
                .collect(Collectors.toList());
        return applications;
    }

    public EmployeeMpoSalaryExtraBenefitBreakdown update(EmployeeMpoSalaryExtraBenefitBreakdownProfile profile) {
        EmployeeMpoSalaryExtraBenefitBreakdown reviewer = updateBreakdownProfile.apply(profile);
        return reviewer;
    }

    private EmployeeMpoSalaryExtraBenefit findBySalaryId(Long salaryId, List<EmployeeMpoSalaryExtraBenefit> lists) {
        for (EmployeeMpoSalaryExtraBenefit reviewer : lists) {
            if (reviewer.getId().equals(salaryId)) {
                return reviewer;
            }
        }
        return null;
    }

    private EmployeeMpoSalaryExtraBenefitBreakdown findByBreakdownId(Long breakdownId, List<EmployeeMpoSalaryExtraBenefitBreakdown> lists) {
        for (EmployeeMpoSalaryExtraBenefitBreakdown breakdown : lists) {
            if (breakdown.getId().equals(breakdownId)) {
                return breakdown;
            }
        }
        return null;
    }

    public EmployeeMpoSalaryExtraBenefit findSalary(Long salaryId, Employee employee) {
        return Optional.ofNullable(findBySalaryId(salaryId, employee.getMpoSalaryExtraBenefits()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_SALARY_EXTRA_BENEFIT + salaryId)
                );
    }

    public EmployeeMpoSalaryExtraBenefitBreakdown findBreakdown(Long breakdownId, EmployeeMpoSalaryExtraBenefit benefit) {
        return Optional.ofNullable(findByBreakdownId(breakdownId, benefit.getBreakdowns()))
                .orElseThrow(
                        () -> new ResourceNotFoundException(EMPLOYEE_SALARY_EXTRA_BENEFIT_BREAKDOWN + breakdownId)
                );
    }

    public EmployeeMpoSalaryExtraBenefit deleteOne(EmployeeMpoSalaryExtraBenefit salary) {
        salary.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        salary.setRecordStatus(DELETED);
        return salary;
    }

    public EmployeeMpoSalaryExtraBenefitBreakdown deleteOne(EmployeeMpoSalaryExtraBenefitBreakdown breakdown) {
        breakdown.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
        breakdown.setRecordStatus(DELETED);
        return breakdown;
    }
}
