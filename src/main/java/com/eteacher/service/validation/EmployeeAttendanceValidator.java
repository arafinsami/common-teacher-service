package com.eteacher.service.validation;

import com.eteacher.service.profile.EmployeeAttendanceProfile;
import com.eteacher.service.dto.EmployeeAttendanceDto;
import com.eteacher.service.utils.CollectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.eteacher.service.constant.ValidatorConstants.DATE_DIFF_EMP_ATTENDANCE;
import static com.eteacher.service.utils.StringUtils.isNotEmpty;

@Component
@RequiredArgsConstructor
public class EmployeeAttendanceValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return EmployeeAttendanceDto.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors error) {

    EmployeeAttendanceDto dto = (EmployeeAttendanceDto) target;
  }

}
