package com.eteacher.service.validation;

import com.eteacher.service.entity.ntrca.Department;
import com.eteacher.service.entity.ntrca.DepartmentalExamination;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.dto.DepartmentalExaminationDto;
import com.eteacher.service.service.DepartmentService;
import com.eteacher.service.utils.CollectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;
import java.util.stream.IntStream;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.StringUtils.*;

@Component
@RequiredArgsConstructor
public class DepartmentExamValidator implements Validator {

    private final DepartmentService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return DepartmentalExaminationDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        DepartmentalExaminationDto request = (DepartmentalExaminationDto) target;

        if (CollectionUtils.isNotEmpty(request.getProfiles())) {
            IntStream.range(0, request.getProfiles().size()).forEach(f -> {
                save(error, request, f);
                update(error, request, f);
            });
        }
    }

    private void save(Errors error, DepartmentalExaminationDto request, int f) {
        if (isNull(request.getProfiles().get(f).getId())) {
            if (isNotEmpty(request.getProfiles().get(f).getYear())) {
                Optional<DepartmentalExamination> examination = service.findByExamYear(request.getProfiles().get(f).getYear());
                if (examination.isPresent()) {
                    error.rejectValue("profiles[" + f + "].year", null, ALREADY_EXIST);
                }
            }
        }
    }

    private void update(Errors error, DepartmentalExaminationDto request, int f) {
        if (nonNull(request.getProfiles().get(f).getId())) {
            if (isNotEmpty(request.getProfiles().get(f).getYear())) {
                Department department = service.findById(request.getDepartmentId()).orElseThrow(ResourceNotFoundException::new);
                if (!department.getExaminations().get(f).getYear().equals(request.getProfiles().get(f).getYear())) {
                    Optional<DepartmentalExamination> examination = service.findByExamYear(request.getProfiles().get(f).getYear());
                    if (examination.isPresent()) {
                        error.rejectValue("profiles[" + f + "].year", null, ALREADY_EXIST);
                    }
                }
            }
        }
    }
}
