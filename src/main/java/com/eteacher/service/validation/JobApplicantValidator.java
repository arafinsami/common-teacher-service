package com.eteacher.service.validation;

import com.eteacher.service.entity.job.JobApplicant;
import com.eteacher.service.dto.JobApplicantDto;
import com.eteacher.service.service.JobApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.StringUtils.isNotEmpty;

@Component
@RequiredArgsConstructor
public class JobApplicantValidator implements Validator {

    private final JobApplicantService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return JobApplicantDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        JobApplicantDto request = (JobApplicantDto) target;

        if (isNotEmpty(request.getPassportNo())) {
            Optional<JobApplicant> applicant = service.findByPassportNo(request.getPassportNo());
            if (applicant.isPresent()) {
                error.rejectValue("passportNo", null, ALREADY_EXIST);
            }
        }
    }
}
