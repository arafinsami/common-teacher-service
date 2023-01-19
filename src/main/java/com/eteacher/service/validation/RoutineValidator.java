package com.eteacher.service.validation;

import com.eteacher.service.dto.RoutineDto;
import com.eteacher.service.entity.commonteacher.Routine;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.RoutineService;
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
public class RoutineValidator implements Validator {

    private final RoutineService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return RoutineDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        RoutineDto dto = (RoutineDto) target;

        if (isNull(dto.getId())) {
            Optional<Routine> routineName = service.findByRoutineName(dto.getRoutineName(), RecordStatus.DELETED);
            if (routineName.isPresent()) {
                error.rejectValue("routineName", null, ALREADY_EXIST);
            }
        }

        if (nonNull(dto.getId())) {
            Routine routine = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);
            if (!routine.getRoutineName().equals(dto.getRoutineName())) {
                Optional<Routine> routineName = service.findByRoutineName(dto.getRoutineName(), RecordStatus.DELETED);
                if (routineName.isPresent()) {
                    error.rejectValue("routineName", null, ALREADY_EXIST);
                }
            }
        }
    }
}
