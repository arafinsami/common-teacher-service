package com.eteacher.service.validation;

import com.eteacher.service.dto.SalaryBreakdownDto;
import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.SalaryBreakdownService;
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
public class SalaryBreakdownValidator implements Validator {

    private final SalaryBreakdownService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return SalaryBreakdownDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        SalaryBreakdownDto dto = (SalaryBreakdownDto) target;

        if (isNull(dto.getId())) {
            Optional<SalaryBreakdown> breakdownName = service.findByBreakdownNameAndRecordStatusNot(dto.getBreakdownName(), RecordStatus.DELETED);
            if (breakdownName.isPresent()) {
                error.rejectValue("breakdownName", null, ALREADY_EXIST);
            }
        }

        if (nonNull(dto.getId())) {
            SalaryBreakdown breakdown = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);
            if (!breakdown.getBreakdownName().equals(dto.getBreakdownName())) {
                Optional<SalaryBreakdown> breakdownName = service.findByBreakdownNameAndRecordStatusNot(dto.getBreakdownName(), RecordStatus.DELETED);
                if (breakdownName.isPresent()) {
                    error.rejectValue("breakdownName", null, ALREADY_EXIST);
                }
            }
        }
    }
}
