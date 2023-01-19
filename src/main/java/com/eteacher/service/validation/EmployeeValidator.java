package com.eteacher.service.validation;

import com.eteacher.service.dto.EmployeeDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.StringUtils.*;

@Component
@RequiredArgsConstructor
public class EmployeeValidator implements Validator {

    private final EmployeeService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        EmployeeDto request = (EmployeeDto) target;

        if (isNull(request.getId())) {
            if (isNotEmpty(request.getNid())) {
                Optional<Employee> employeeNid = service
                        .findByNidAndRecordStatusNot(request.getNid());
                if (employeeNid.isPresent()) {
                    error.rejectValue("nid", null, ALREADY_EXIST);
                }
            }

            if (isNotEmpty(request.getBirthRegNo())) {
                Optional<Employee> employeeBirthReg = service
                        .findByBirthRegNoAndRecordStatusNot(request.getBirthRegNo());
                if (employeeBirthReg.isPresent()) {
                    error.rejectValue("birthRegNo", null, ALREADY_EXIST);
                }
            }

            if (isNotEmpty(request.getPassportNo())) {
                Optional<Employee> employeePassport = service
                        .findByPassportNoAndRecordStatusNot(request.getPassportNo());
                if (employeePassport.isPresent()) {
                    error.rejectValue("passportNo", null, ALREADY_EXIST);
                }
            }
        }

        if (nonNull(request.getId())) {
            Employee employee = service.findById(request.getId()).orElseThrow(ResourceNotFoundException::new);
            if (!employee.getNid().equals(request.getNid())) {
                Optional<Employee> employeeNid = service
                        .findByNidAndRecordStatusNot(request.getNid());
                if (employeeNid.isPresent()) {
                    error.rejectValue("nid", null, ALREADY_EXIST);
                }
            }

            if (!employee.getBirthRegNo().equals(request.getBirthRegNo())) {
                Optional<Employee> employeeBirthReg = service
                        .findByBirthRegNoAndRecordStatusNot(request.getBirthRegNo());
                if (employeeBirthReg.isPresent()) {
                    error.rejectValue("birthRegNo", null, ALREADY_EXIST);
                }
            }

            if (!employee.getPassportNo().equals(request.getPassportNo())) {
                Optional<Employee> employeePassport = service
                        .findByPassportNoAndRecordStatusNot(request.getPassportNo());
                if (employeePassport.isPresent()) {
                    error.rejectValue("passportNo", null, ALREADY_EXIST);
                }
            }
        }
    }
}
