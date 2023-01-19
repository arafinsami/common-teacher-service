package com.eteacher.service.validation;

import com.eteacher.service.entity.ntrca.Department;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.dto.DepartmentDto;
import com.eteacher.service.service.DepartmentService;
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
public class DepartmentValidator implements Validator {

    private final DepartmentService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return DepartmentDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        DepartmentDto request = (DepartmentDto) target;

        if (isNull(request.getId())) {
            Optional<Department> department = service.findByDepartmentName(request.getDepartmentName());
            if (department.isPresent()) {
                error.rejectValue("departmentName", null, ALREADY_EXIST);
            }
        }

        if (nonNull(request.getId())) {
            Department department = service.findById(request.getId()).orElseThrow(ResourceNotFoundException::new);
            if (!department.getDepartmentName().equals(request.getDepartmentName())) {
                Optional<Department> bankName = service.findByDepartmentName(request.getDepartmentName());
                if (bankName.isPresent()) {
                    error.rejectValue("departmentName", null, ALREADY_EXIST);
                }
            }
        }
    }
}
