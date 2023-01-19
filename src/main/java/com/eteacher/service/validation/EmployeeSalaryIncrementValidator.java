package com.eteacher.service.validation;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeSalaryIncrement;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeSalaryIncrementProfile;
import com.eteacher.service.dto.EmployeeSalaryIncrementDto;
import com.eteacher.service.service.EmployeeSalaryIncrementService;
import com.eteacher.service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.constant.ValidatorConstants.COMPARE_FROM_TO;
import static com.eteacher.service.utils.DateUtils.format;
import static com.eteacher.service.utils.StringUtils.isNotEmpty;
import static com.eteacher.service.utils.StringUtils.nonNull;

@Component
@RequiredArgsConstructor
public class EmployeeSalaryIncrementValidator implements Validator {

    @Resource
    private EmployeeSalaryIncrementService service;

    @Resource
    private EmployeeService employeeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeSalaryIncrementDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        EmployeeSalaryIncrementDto request = (EmployeeSalaryIncrementDto) target;

        if (request.getSalaryIncrements().size() > 0 && Objects.nonNull(request.getSalaryIncrements())) {

            List<EmployeeSalaryIncrementProfile> profiles = request.getSalaryIncrements();

            for (int i = 0; i < profiles.size(); i++) {

                if (profiles.get(i).getSalaryScaleFrom().equals(profiles.get(i).getSalaryScale())) {
                    error.rejectValue("salaryIncrements[" + i + "].salaryScaleFrom", null, COMPARE_FROM_TO);
                    return;
                }

                if (isNotEmpty(profiles.get(i).getEffectiveDate())) {

                    Employee emp = employeeService.findByIdAndRecordStatusNot(request.getEmployee())
                            .orElseThrow(ResourceNotFoundException::new);

                    EmployeeSalaryIncrement increment = service
                            .findByEffectiveDate(emp, profiles.get(i).getEffectiveDate());

                    Date date = new Date(profiles.get(i).getEffectiveDate().getTime());

                    String requestDate = format(date, "dd/MM/yyyy");

                    if (nonNull(increment)) {
                        String dbDate = format(increment.getEffectiveDate(), "dd/MM/yyyy");
                        if (requestDate.equals(dbDate)) {
                            error.rejectValue("salaryIncrements[" + i + "].effectiveDate", null, ALREADY_EXIST);
                            return;
                        }
                    }
                }
            }
        }
    }

}

