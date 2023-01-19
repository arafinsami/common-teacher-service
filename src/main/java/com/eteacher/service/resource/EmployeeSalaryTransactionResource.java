package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeeSalaryTransactionBreakdownDto;
import com.eteacher.service.dto.EmployeeSalaryTransactionDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeSalaryTransaction;
import com.eteacher.service.entity.mpo.EmployeeSalaryTransactionBreakdown;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeSalaryTransactionHelper;
import com.eteacher.service.response.EmployeeSalaryTransactionBreakdownResponse;
import com.eteacher.service.response.EmployeeSalaryTransactionResponse;
import com.eteacher.service.service.EmployeeSalaryTransactionService;
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
@Api(tags = "MPO---->Employee Salary Transaction's Data")
@RequestMapping(
        "api/v1/employee-salary-transaction"
)
public class EmployeeSalaryTransactionResource {

    private final EmployeeSalaryTransactionService service;

    private final EmployeeSalaryTransactionHelper helper;

    private final EmployeeService employeeService;

    @PostMapping("/save")
    @ApiOperation(value = "save salary transaction by employee Id", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeSalaryTransactionDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addSalaryTransactions(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_SALARY_TRANSACTION_SAVE).getJson());
    }

    @PostMapping("/breakdown/save")
    @ApiOperation(value = "save salary transaction with salary Id and employee Id", response = String.class)
    public ResponseEntity<JSONObject> saveBreakdown(@Valid @RequestBody EmployeeSalaryTransactionBreakdownDto dto,
                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeSalaryTransaction transaction = helper.findTransaction(dto.getTransactionId(), employee);

        transaction.addBreakdown(helper.save(dto.getProfile()));

        employee.addSalaryTransaction(transaction);

        service.save(employee, transaction.getId());
        return ok(success(EMPLOYEE_SALARY_TRANSACTION_BREAKDOWN_SAVE).getJson());
    }

    @PostMapping("/update")
    @ApiOperation(value = "update salary transaction by employee Id", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeSalaryTransactionDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addSalaryTransactions(distinct(helper.update(dto.getProfiles())));

        service.update(employee);
        return ok(success(EMPLOYEE_SALARY_TRANSACTION_UPDATE).getJson());
    }

    @PutMapping("/update/breakdown")
    @ApiOperation(
            value = "update salary transaction breakdown with salary Id and employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> updateBreakdown(@Valid @RequestBody EmployeeSalaryTransactionBreakdownDto dto,
                                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeSalaryTransaction transaction = helper.findTransaction(dto.getTransactionId(), employee);

        transaction.addBreakdown(helper.update(dto.getProfile()));

        employee.addSalaryTransaction(transaction);

        service.update(employee, transaction.getId(), dto.getProfile().getId());
        return ok(success(EMPLOYEE_SALARY_TRANSACTION_BREAKDOWN_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(value = "get salary transaction lists by employee id", response = EmployeeSalaryTransactionResponse.class)
    public ResponseEntity<JSONObject> findAllTransactionByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeeSalaryTransaction> applications = employee.getSalaryTransactions()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeSalaryTransactionResponse> response = applications.stream()
                .map(EmployeeSalaryTransactionResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{transactionId}")
    @ApiOperation(value = "get single transaction by employee Id and transaction Id",
            response = EmployeeSalaryTransactionResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeAndTransactionId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                        @Valid @PathVariable @NotNull @Min(1) Long transactionId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeSalaryTransaction application = helper.findTransaction(transactionId, employee);

        return ok(success(EmployeeSalaryTransactionResponse.response(application)).getJson());
    }

    @GetMapping("/find/breakdown/{employeeId}/{transactionId}")
    @ApiOperation(
            value = "get salary breakdown lists by employee id and mpo transaction id",
            response = EmployeeSalaryTransactionBreakdownResponse.class
    )
    public ResponseEntity<JSONObject> findAllBreakdownByEmployeeIdAndTransactionId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                   @Valid @PathVariable @NotNull @Min(1) Long transactionId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeSalaryTransaction application = helper.findTransaction(transactionId, employee);

        List<EmployeeSalaryTransactionBreakdownResponse> response = application.getBreakdowns()
                .stream()
                .map(EmployeeSalaryTransactionBreakdownResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/breakdown/{employeeId}/{transactionId}/{breakdownId}")
    @ApiOperation(value = "get single salary breakdown by employee Id, application Id and reviewer Id",
            response = EmployeeSalaryTransactionBreakdownResponse.class)
    public ResponseEntity<JSONObject> findOneBreakdownByEmployeeAndTransactionIdAndBreakdownId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                               @Valid @PathVariable @NotNull @Min(1) Long transactionId,
                                                                                               @Valid @PathVariable @NotNull @Min(1) Long breakdownId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeSalaryTransaction transaction = helper.findTransaction(transactionId, employee);

        EmployeeSalaryTransactionBreakdown breakdown = helper.findBreakdown(breakdownId, transaction);

        return ok(success(EmployeeSalaryTransactionBreakdownResponse.response(breakdown)).getJson());
    }

    @DeleteMapping("/delete/transaction/{employeeId}/{transactionId}")
    @ApiOperation(value = "delete single salary by employee Id and employee salary Id",
            response = String.class)
    public ResponseEntity<JSONObject> deleteTransaction(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                        @Valid @PathVariable @NotNull @Min(1) Long transactionId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeSalaryTransaction transaction = helper.findTransaction(transactionId, employee);

        employee.addSalaryTransaction(helper.deleteOne(transaction));

        service.delete(employee, transaction);
        return ok(success(EMPLOYEE_SALARY_TRANSACTION_DELETE + " with id: " + transactionId).getJson());
    }

    @DeleteMapping("/delete/breakdown/{employeeId}/{transactionId}/{breakdownId}")
    @ApiOperation(value = "delete single salary by employee Id and employee salary Id",
            response = String.class)
    public ResponseEntity<JSONObject> deleteBreakdown(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                      @Valid @PathVariable @NotNull @Min(1) Long transactionId,
                                                      @Valid @PathVariable @NotNull @Min(1) Long breakdownId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeSalaryTransaction transaction = helper.findTransaction(transactionId, employee);

        EmployeeSalaryTransactionBreakdown breakdown = helper.findBreakdown(breakdownId, transaction);

        transaction.addBreakdown(helper.deleteOne(breakdown));

        employee.addSalaryTransaction(transaction);

        service.delete(employee, transactionId, breakdownId);
        return ok(success(EMPLOYEE_SALARY_TRANSACTION_BREAKDOWN_DELETE + " with id: " + breakdownId).getJson());
    }
}
