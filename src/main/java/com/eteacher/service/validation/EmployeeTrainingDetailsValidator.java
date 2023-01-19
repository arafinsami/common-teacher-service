package com.eteacher.service.validation;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeTrainingDetailsProfile;
import com.eteacher.service.dto.EmployeeTrainingDetailsDto;
import com.eteacher.service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;

@Component
@RequiredArgsConstructor
public class EmployeeTrainingDetailsValidator implements Validator {

    @Resource
    private EmployeeService employeeService;

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeTrainingDetailsDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        EmployeeTrainingDetailsDto request = (EmployeeTrainingDetailsDto) target;

        if (request.getProfiles().size() > 0 && Objects.nonNull(request.getProfiles())) {

            List<EmployeeTrainingDetailsProfile> profiles = request.getProfiles();

            Employee employee = employeeService.findByIdAndRecordStatusNot(request.getEmployee())
                    .orElseThrow(ResourceNotFoundException::new);

            int[] arr = {0};
            profiles.forEach((profile) -> {

                employee.getTrainings().forEach((e) -> {
                    if (e.getEmployeeTrainingSl().equals(profile.getEmployeeTrainingSl())) {
                        error.rejectValue("training details[" + arr[0] + "]", null, ALREADY_EXIST);
                        return;
                    }
                });
                arr[0]++;
            });
        }
    }
}
