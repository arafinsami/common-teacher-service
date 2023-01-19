package com.eteacher.service.validation;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeBankAccountProfile;
import com.eteacher.service.dto.EmployeeBankAccountDto;
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
public class EmployeeBankAccountValidator implements Validator {

  @Resource
  private EmployeeService employeeService;

  @Override
  public boolean supports(Class<?> clazz) {
    return EmployeeBankAccountDto.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors error) {

    EmployeeBankAccountDto request = (EmployeeBankAccountDto) target;

    if (request.getBankAccounts().size() > 0 && Objects.nonNull(request.getBankAccounts())) {

      List<EmployeeBankAccountProfile> profiles = request.getBankAccounts();

      Employee emp = employeeService.findByIdAndRecordStatusNot(request.getEmployee())
              .orElseThrow(ResourceNotFoundException::new);

      int[] iarr = {0};
      profiles.forEach((profile) -> {

        emp.getBankAccounts().forEach((e) -> {
          if (e.getBankAccountNumber().equals(profile.getBankAccountNumber())) {
            error.rejectValue("bankAccounts[" + iarr[0] + "].bankAccountNumber",
                    null, ALREADY_EXIST);
            return;
          }
        });
        iarr[0]++;
      });
    }
  }
}
