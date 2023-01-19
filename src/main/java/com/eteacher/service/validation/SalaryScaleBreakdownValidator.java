package com.eteacher.service.validation;

import com.eteacher.service.dto.SalaryScaleBreakdownDto;
import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.commonteacher.SalaryScaleBreakdown;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.repository.SalaryBreakdownRepository;
import com.eteacher.service.repository.SalaryScaleRepository;
import com.eteacher.service.service.SalaryScaleBreakdownService;
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
public class SalaryScaleBreakdownValidator implements Validator {

    private final SalaryScaleRepository salaryScaleRepository;

    private final SalaryBreakdownRepository salaryBreakdownRepository;

    private final SalaryScaleBreakdownService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return SalaryScaleBreakdownDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        SalaryScaleBreakdownDto dto = (SalaryScaleBreakdownDto) target;

        if (isNull(dto.getId())) {
            SalaryScale salaryScale = salaryScaleRepository
                    .findByIdAndRecordStatusNot(dto.getSalaryScaleId(), RecordStatus.DELETED)
                    .orElseThrow(ResourceNotFoundException::new);
            SalaryBreakdown salaryBreakdown = salaryBreakdownRepository
                    .findByIdAndRecordStatusNot(dto.getSalaryBreakDownId(), RecordStatus.DELETED)
                    .orElseThrow(ResourceNotFoundException::new);
            Optional<SalaryScaleBreakdown> salaryScaleBreakdown = service
                    .findBySalaryBreakdownAndSalaryScaleAndRecordStatusNot(salaryBreakdown, salaryScale);
            if (salaryScaleBreakdown.isPresent()) {
                error.rejectValue("salaryBreakDownId", null, ALREADY_EXIST);
                error.rejectValue("salaryScaleId", null, ALREADY_EXIST);
            }
        }

        if (nonNull(dto.getSalaryScaleId()) && nonNull(dto.getSalaryBreakDownId())) {
            Optional<SalaryScaleBreakdown> salaryScaleBreakdown = service
                    .findBySalaryBreakdownAndSalaryScale(dto.getSalaryBreakDownId(), dto.getSalaryScaleId());

        }

    }
}
