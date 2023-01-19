package com.eteacher.service.validation;

import com.eteacher.service.dto.EmployeeTypeDto;
import com.eteacher.service.entity.commonteacher.EmployeeType;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.EmployeeTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.StringUtils.isNull;
import static com.eteacher.service.utils.StringUtils.nonNull;

@Component
@RequiredArgsConstructor
public class EmployeeTypeValidator implements Validator {

    private final EmployeeTypeService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeTypeDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        EmployeeTypeDto request = (EmployeeTypeDto) target;

        if (isNull(request.getId())) {
            Optional<EmployeeType> employeeTypeName = service
                    .findByEmployeeTypeNameAndRecordStatusNot(request.getEmployeeTypeName());
            if (employeeTypeName.isPresent()) {
                error.rejectValue("employeeTypeName", null, ALREADY_EXIST);
            }
        }

        if (nonNull(request.getId())) {
            EmployeeType employeeType = service.findById(request.getId()).orElseThrow(ResourceNotFoundException::new);
            if (!employeeType.getEmployeeTypeName().equals(request.getEmployeeTypeName())) {
                Optional<EmployeeType> employeeTypeName = service
                        .findByEmployeeTypeNameAndRecordStatusNot(request.getEmployeeTypeName());
                if (employeeTypeName.isPresent()) {
                    error.rejectValue("employeeTypeName", null, ALREADY_EXIST);
                }
            }
        }
    }
}
