package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeSalaryIncrementBreakdownDto;
import com.eteacher.service.dto.EmployeeSalaryIncrementDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeSalaryIncrement;
import com.eteacher.service.entity.mpo.EmployeeSalaryIncrementBreakdown;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeSalaryIncrementHelper;
import com.eteacher.service.response.EmployeeResponse;
import com.eteacher.service.response.EmployeeSalaryIncrementBreakdownResponse;
import com.eteacher.service.response.EmployeeSalaryIncrementResponse;
import com.eteacher.service.service.EmployeeSalaryIncrementService;
import com.eteacher.service.service.EmployeeService;
import com.eteacher.service.validation.EmployeeSalaryIncrementValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE;
import static com.eteacher.service.enums.RecordStatus.DELETED;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.CollectionUtils.distinct;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee-salary-increment")
@Api(tags = "Employee Salary Increment Data")
public class EmployeeSalaryIncrementResource {

    private final EmployeeService employeeService;

    private final EmployeeSalaryIncrementService service;

    private final EmployeeSalaryIncrementHelper helper;

    private final EmployeeSalaryIncrementValidator validator;

    @PostMapping("/save")
    @ApiOperation(value = "save employee salary increment", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeSalaryIncrementDto dto, BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployee())
        );

        employee.addSalaryIncrements(distinct(helper.save(dto.getSalaryIncrements())));

        service.save(employee);
        return ok(success(EMPLOYEE_SALARY_INCREMENT_SAVE).getJson());
    }

    @PostMapping("/save/salary/breakdown")
    @ApiOperation(
            value = "save employee salary increment breakdown with mpo salary Id and employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveBreakdown(@Valid @RequestBody EmployeeSalaryIncrementBreakdownDto dto,
                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeSalaryIncrement salary = helper.findIncrement(dto.getSalaryId(), employee);

        salary.addBreakdown(helper.save(dto.getProfile()));

        employee.addSalaryIncrement(salary);

        service.save(employee, dto.getSalaryId());
        return ok(success(EMPLOYEE_SALARY_INCREMENT_BREAKDOWN_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee salary increment", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeSalaryIncrementDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployee())
        );

        employee.addSalaryIncrements(distinct(helper.update(dto.getSalaryIncrements())));

        service.update(employee);
        return ok(success(EMPLOYEE_SALARY_INCREMENT_UPDATE).getJson());
    }

    @PutMapping("/update/salary/breakdown")
    @ApiOperation(
            value = "update employee salary increment breakdown with mpo salary Id and employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> updateBreakdown(@Valid @RequestBody EmployeeSalaryIncrementBreakdownDto dto,
                                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeSalaryIncrement salary = helper.findIncrement(dto.getSalaryId(), employee);

        salary.addBreakdown(helper.update(dto.getProfile()));

        employee.addSalaryIncrement(salary);

        service.update(employee, dto.getSalaryId());
        return ok(success(EMPLOYEE_SALARY_INCREMENT_BREAKDOWN_UPDATE).getJson());
    }

    @GetMapping("/find/salary/{employeeId}")
    @ApiOperation(
            value = "get employee salary increment by employee Id",
            response = EmployeeSalaryIncrementResponse.class
    )
    public ResponseEntity<JSONObject> findAllSalaryByEmployeeId(@Valid @PathVariable("empId") @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findByIdAndRecordStatusNot(employeeId)
                .orElseThrow(ResourceNotFoundException::new);

        List<EmployeeSalaryIncrement> salaries = employee.getSalaryIncrements()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeSalaryIncrementResponse> response = salaries
                .stream()
                .map(EmployeeSalaryIncrementResponse::from)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/salary/{employeeId}/{salaryId}")
    @ApiOperation(
            value = "get single employee salary by salary Id and employee Id",
            response = EmployeeSalaryIncrementResponse.class
    )
    public ResponseEntity<JSONObject> findSalaryByEmployeeIdAndSalaryId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                        @Valid @PathVariable @NotNull @Min(1) Long salaryId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeSalaryIncrement salary = helper.findIncrement(salaryId, employee);

        return ok(success(EmployeeSalaryIncrementResponse.from(salary)).getJson());
    }

    @GetMapping("/find/salary/breakdown/{employeeId}/{salaryId}")
    @ApiOperation(
            value = "get employee salary increment breakdown lists by employee id and mpo salary Id",
            response = EmployeeSalaryIncrementBreakdownResponse.class
    )
    public ResponseEntity<JSONObject> findAllBreakdownByEmployeeIdAndSalaryId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                              @Valid @PathVariable @NotNull @Min(1) Long salaryId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeSalaryIncrement salary = helper.findIncrement(salaryId, employee);

        List<EmployeeSalaryIncrementBreakdownResponse> response = salary.getBreakdowns()
                .stream()
                .map(EmployeeSalaryIncrementBreakdownResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/salary/breakdown/{employeeId}/{salaryId}/{breakdownId}")
    @ApiOperation(value = "get single employee salary increment breakdown by employee Id, salary Id and breakdown Id",
            response = EmployeeSalaryIncrementBreakdownResponse.class)
    public ResponseEntity<JSONObject> findBreakdownByEmployeeIdAndSalaryIdAndBreakdownId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                         @Valid @PathVariable @NotNull @Min(1) Long salaryId,
                                                                                         @Valid @PathVariable @NotNull @Min(1) Long breakdownId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeSalaryIncrement salary = helper.findIncrement(salaryId, employee);

        EmployeeSalaryIncrementBreakdown breakdown = helper.findBreakdown(breakdownId, salary);

        return ok(success(EmployeeSalaryIncrementBreakdownResponse.response(breakdown)).getJson());
    }

    @DeleteMapping("/delete/salary/{employeeId}/{salaryId}")
    @ApiOperation(value = "delete employee salary by employee and salary id",
            response = EmployeeResponse.class)
    public ResponseEntity<JSONObject> deleteSalaryIncrement(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                            @Valid @PathVariable @NotNull @Min(1) Long salaryId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeSalaryIncrement salary = helper.findIncrement(salaryId, employee);

        employee.addSalaryIncrement(helper.delete(salary));

        service.delete(employee, salaryId);
        return ok(success(EMPLOYEE_SALARY_INCREMENT_DELETE + " " + salaryId).getJson());
    }

    @DeleteMapping("/delete/breakdown/{employeeId}/{salaryId}/{breakdownId}")
    @ApiOperation(value = "delete employee salary breakdown by employee Id, salary Id and breakdown Id",
            response = EmployeeResponse.class)
    public ResponseEntity<JSONObject> deleteBreakdown(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                      @Valid @PathVariable @NotNull @Min(1) Long salaryId,
                                                      @Valid @PathVariable @NotNull @Min(1) Long breakdownId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeSalaryIncrement salary = helper.findIncrement(salaryId, employee);

        EmployeeSalaryIncrementBreakdown breakdown = helper.findBreakdown(breakdownId, salary);

        salary.addBreakdown(helper.delete(breakdown));

        employee.addSalaryIncrement(salary);

        service.delete(employee, salaryId, breakdownId);
        return ok(success(EMPLOYEE_SALARY_INCREMENT_BREAKDOWN_DELETE + " " + salaryId).getJson());
    }
}
