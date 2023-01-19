package com.eteacher.service.validation;

import com.eteacher.service.dto.StudentTypeDto;
import com.eteacher.service.entity.commonteacher.StudentType;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.StudentTypeService;
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
public class StudentTypeValidator implements Validator {

    private final StudentTypeService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return StudentTypeDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        StudentTypeDto dto = (StudentTypeDto) target;

        if (isNull(dto.getId())) {
            Optional<StudentType> studentTypeName = service.findByStudentTypeName(dto.getStudentTypeName());
            if (studentTypeName.isPresent()) {
                error.rejectValue("studentTypeName", null, ALREADY_EXIST);
            }
        }

        if (nonNull(dto.getId())) {
            StudentType studentType = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);
            if (!studentType.getStudentTypeName().equals(dto.getStudentTypeName())) {
                Optional<StudentType> studentTypeName = service.findByStudentTypeName(dto.getStudentTypeName());
                if (studentTypeName.isPresent()) {
                    error.rejectValue("studentTypeName", null, ALREADY_EXIST);
                }
            }
        }
    }
}
