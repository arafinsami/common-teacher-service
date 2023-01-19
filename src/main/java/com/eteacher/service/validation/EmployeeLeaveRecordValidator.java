package com.eteacher.service.validation;

import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.profile.EmployeeLeaveRecordProfile;
import com.eteacher.service.dto.EmployeeLeaveRecordDto;
import com.eteacher.service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.DateUtils.format;

@Component
@RequiredArgsConstructor
public class EmployeeLeaveRecordValidator implements Validator {

  @Resource
  private EmployeeService employeeService;


  @Override
  public boolean supports(Class<?> clazz) {
    return EmployeeLeaveRecordDto.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors error) {

    EmployeeLeaveRecordDto request = (EmployeeLeaveRecordDto) target;

    if (request.getLeaveRecords().size() > 0 && Objects.nonNull(request.getLeaveRecords())) {

      List<EmployeeLeaveRecordProfile> profiles = request.getLeaveRecords();

      Employee emp = employeeService.findByIdAndRecordStatusNot(request.getEmployee())
              .orElseThrow(ResourceNotFoundException::new);

      int[] iarr = {0};
      profiles.forEach((profile) -> {

        emp.getLeaveRecords().forEach((e) -> {
          Date date = new Date(profile.getLeaveStartDate().getTime());
          String requestDate = format(date, "dd/MM/yyyy");
          String dbDate = format(e.getLeaveStartDate(), "dd/MM/yyyy");
          if (requestDate.equals(dbDate)) {
            error.rejectValue("leaveRecords[" + iarr[0] + "].leaveStartDate", null, ALREADY_EXIST);
            return;
          }
        });

        emp.getLeaveRecords().forEach((e) -> {
          Date date = new Date(profile.getLeaveEndDate().getTime());
          String requestDate = format(date, "dd/MM/yyyy");
          String dbDate = format(e.getLeaveEndDate(), "dd/MM/yyyy");
          if (requestDate.equals(dbDate)) {
            error.rejectValue("leaveRecords[" + iarr[0] + "].leaveEndDate", null, ALREADY_EXIST);
            return;
          }
        });
        iarr[0]++;
      });
    }
  }
}
