package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeeMpoSalaryExtraBenefitBreakdownDto;
import com.eteacher.service.dto.EmployeeMpoSalaryExtraBenefitDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeMpoSalaryExtraBenefit;
import com.eteacher.service.entity.mpo.EmployeeMpoSalaryExtraBenefitBreakdown;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeMpoSalaryExtraBenefitHelper;
import com.eteacher.service.response.EmployeeMpoSalaryExtraBenefitBreakdownResponse;
import com.eteacher.service.response.EmployeeMpoSalaryExtraBenefitResponse;
import com.eteacher.service.service.EmployeeMpoSalaryExtraBenefitService;
import com.eteacher.service.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@Api(tags = "MPO----> Employee Mpo Salary Extra Benefit's Data")
@RequestMapping(
        "api/v1/employee-mpo-salary-extra-benefit"
)
public class EmployeeMpoSalaryExtraBenefitResource {

    private final EmployeeMpoSalaryExtraBenefitService service;

    private final EmployeeMpoSalaryExtraBenefitHelper helper;

    private final EmployeeService employeeService;

    @PostMapping("/save")
    @ApiOperation(value = "save employee mpo salary extra benefit", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeMpoSalaryExtraBenefitDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addMpoSalaryExtraBenefits(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_SALARY_EXTRA_BENEFIT_SAVE).getJson());
    }

    @PostMapping("/breakdown/save")
    @ApiOperation(
            value = "save employee mpo salary extra benefit breakdown with employee Id and salary Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveBreakDown(@Valid @RequestBody EmployeeMpoSalaryExtraBenefitBreakdownDto dto,
                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeMpoSalaryExtraBenefit salaryExtraBenefit = helper.findSalary(dto.getSalaryId(), employee);

        salaryExtraBenefit.addBreakdown(helper.save(dto.getProfile()));

        employee.addMpoSalaryExtraBenefit(salaryExtraBenefit);

        service.save(employee, dto.getSalaryId());
        return ok(success(EMPLOYEE_SALARY_EXTRA_BENEFIT_BREAKDOWN_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee mpo salary extra benefit", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeMpoSalaryExtraBenefitDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addMpoSalaryExtraBenefits(distinct(helper.update(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_SALARY_EXTRA_BENEFIT_UPDATE).getJson());
    }

    @PutMapping("/breakdown/update")
    @ApiOperation(
            value = "update employee mpo salary extra benefit breakdown with employee Id and salary Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> updateBreakDown(@Valid @RequestBody EmployeeMpoSalaryExtraBenefitBreakdownDto dto,
                                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeMpoSalaryExtraBenefit salaryExtraBenefit = helper.findSalary(dto.getSalaryId(), employee);

        salaryExtraBenefit.addBreakdown(helper.update(dto.getProfile()));

        employee.addMpoSalaryExtraBenefit(salaryExtraBenefit);

        service.update(employee, dto.getSalaryId());
        return ok(success(EMPLOYEE_SALARY_EXTRA_BENEFIT_BREAKDOWN_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee mpo salary extra benefit lists by employee id",
            response = EmployeeMpoSalaryExtraBenefitResponse.class
    )
    public ResponseEntity<JSONObject> findAllSalariesByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeeMpoSalaryExtraBenefit> salaryExtraBenefits = employee.getMpoSalaryExtraBenefits()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeMpoSalaryExtraBenefitResponse> response = salaryExtraBenefits.stream()
                .map(EmployeeMpoSalaryExtraBenefitResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{salaryId}")
    @ApiOperation(
            value = "get single employee mpo salary extra benefit by employee Id and salary Id",
            response = EmployeeMpoSalaryExtraBenefitResponse.class
    )
    public ResponseEntity<JSONObject> findSalaryByEmployeeAndASalaryId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                       @Valid @PathVariable @NotNull @Min(1) Long salaryId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoSalaryExtraBenefit salary = helper.findSalary(salaryId, employee);

        return ok(success(EmployeeMpoSalaryExtraBenefitResponse.response(salary)).getJson());
    }

    @GetMapping("/find/breakdown/{employeeId}/{salaryId}")
    @ApiOperation(
            value = "get mpo salary extra benefit breakdown lists by employee id and mpo application id",
            response = EmployeeMpoSalaryExtraBenefitBreakdownResponse.class
    )
    public ResponseEntity<JSONObject> findAllBreakDownByEmployeeIdAndSalaryId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                              @Valid @PathVariable @NotNull @Min(1) Long salaryId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoSalaryExtraBenefit salary = helper.findSalary(salaryId, employee);

        List<EmployeeMpoSalaryExtraBenefitBreakdownResponse> response = salary.getBreakdowns()
                .stream()
                .map(EmployeeMpoSalaryExtraBenefitBreakdownResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/breakdown/{employeeId}/{salaryId}/{breakdownId}")
    @ApiOperation(
            value = "get single salary extra benefit breakdown by employee Id, salary Id and breakdown Id",
            response = EmployeeMpoSalaryExtraBenefitBreakdownResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndSalaryIdAndBreakDownId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                   @Valid @PathVariable @NotNull @Min(1) Long salaryId,
                                                                                   @Valid @PathVariable @NotNull @Min(1) Long breakdownId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoSalaryExtraBenefit salary = helper.findSalary(salaryId, employee);

        EmployeeMpoSalaryExtraBenefitBreakdown breakdown = helper.findBreakdown(breakdownId, salary);

        return ok(success(EmployeeMpoSalaryExtraBenefitBreakdownResponse.response(breakdown)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{salaryId}")
    @ApiOperation(
            value = "delete single salary by employee Id and salary Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteSingle(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                   @Valid @PathVariable @NotNull @Min(1) Long salaryId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoSalaryExtraBenefit salary = helper.findSalary(salaryId, employee);

        employee.addMpoSalaryExtraBenefit(helper.deleteOne(salary));

        service.delete(employee, salaryId);
        return ok(success(EMPLOYEE_SALARY_EXTRA_BENEFIT_DELETE + " with id: " + salaryId).getJson());
    }

    @DeleteMapping("/delete/breakdown/{employeeId}/{salaryId}/{breakdownId}")
    @ApiOperation(
            value = "delete single salary breakdown by employee Id, salary Id and breakdown Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteSingle(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                   @Valid @PathVariable @NotNull @Min(1) Long salaryId,
                                                   @Valid @PathVariable @NotNull @Min(1) Long breakdownId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoSalaryExtraBenefit salary = helper.findSalary(salaryId, employee);

        EmployeeMpoSalaryExtraBenefitBreakdown breakdown = helper.findBreakdown(breakdownId, salary);

        salary.addBreakdown(helper.deleteOne(breakdown));

        employee.addMpoSalaryExtraBenefit(salary);

        service.delete(employee, salaryId, breakdownId);
        return ok(success(EMPLOYEE_SALARY_EXTRA_BENEFIT_BREAKDOWN_DELETE + " with id: " + breakdownId).getJson());
    }
}
