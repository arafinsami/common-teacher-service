package com.eteacher.service.validation;

import com.eteacher.service.dto.SalaryScaleDto;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.SalaryScaleService;
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
public class SalaryScaleValidator implements Validator {

    private final SalaryScaleService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return SalaryScaleDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        SalaryScaleDto dto = (SalaryScaleDto) target;

        if (isNull(dto.getId())) {
            Optional<SalaryScale> salaryScaleName = service.findBySalaryScaleName(dto.getScaleName());
            if (salaryScaleName.isPresent()) {
                error.rejectValue("scaleName", null, ALREADY_EXIST);
            }
        }

        if (nonNull(dto.getId())) {
            SalaryScale salaryScale = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);
            if (!salaryScale.getSalaryScaleName().equals(dto.getScaleName())) {
                Optional<SalaryScale> salaryScaleName = service.findBySalaryScaleName(dto.getScaleName());
                if (salaryScaleName.isPresent()) {
                    error.rejectValue("scaleName", null, ALREADY_EXIST);
                }
            }
        }
    }
}
