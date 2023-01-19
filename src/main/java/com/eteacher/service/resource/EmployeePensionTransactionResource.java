package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeePensionTransactionBreakdownDto;
import com.eteacher.service.dto.EmployeePensionTransactionDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.govtteacher.EmployeePensionTransaction;
import com.eteacher.service.entity.govtteacher.EmployeePensionTransactionBreakdown;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeePensionTransactionHelper;
import com.eteacher.service.response.EmployeePensionTransactionBreakdownResponse;
import com.eteacher.service.response.EmployeePensionTransactionResponse;
import com.eteacher.service.service.EmployeePensionTransactionService;
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
@Api(tags = "GOV TEACHER--->Employee Pension Transaction's Data")
@RequestMapping(
        "api/v1/employee-pension-transaction"
)
public class EmployeePensionTransactionResource {

    private final EmployeePensionTransactionService service;

    private final EmployeePensionTransactionHelper helper;

    private final EmployeeService employeeService;

    @PostMapping("/save")
    @ApiOperation(value = "save employee pension transaction", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeePensionTransactionDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addPensionTransactions(distinct(helper.saveTransactions(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_PENSION_TRANSACTION_SAVE).getJson());
    }

    @PostMapping("/save/breakdown")
    @ApiOperation(
            value = "save employee pension transaction breakdown with transaction Id and employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveBreakDown(@Valid @RequestBody EmployeePensionTransactionBreakdownDto dto,
                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeePensionTransaction transaction = helper.findPensionTransaction(dto.getTransactionId(), employee);

        transaction.addBreakdown(helper.saveBreakDowns(dto.getProfile()));

        employee.addPensionTransaction(transaction);

        service.saveBreakDown(employee, transaction);
        return ok(success(EMPLOYEE_PENSION_TRANSACTION_BREAKDOWN_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee pension transaction", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeePensionTransactionDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addPensionTransactions(distinct(helper.updateTransactions(dto.getProfiles())));

        service.update(employee);
        return ok(success(EMPLOYEE_PENSION_TRANSACTION_UPDATE).getJson());
    }

    @PutMapping("/update/breakdown")
    @ApiOperation(
            value = "update employee pension transaction breakdown with transaction Id and employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> updateBreakDown(@Valid @RequestBody EmployeePensionTransactionBreakdownDto dto,
                                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeePensionTransaction transaction = helper.findPensionTransaction(dto.getTransactionId(), employee);

        transaction.addBreakdown(helper.updateBreakDowns(dto.getProfile()));

        employee.addPensionTransaction(transaction);

        service.updateBreakDown(employee, transaction);
        return ok(success(EMPLOYEE_PENSION_TRANSACTION_BREAKDOWN_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee transaction lists by employee id",
            response = EmployeePensionTransactionResponse.class
    )
    public ResponseEntity<JSONObject> findAllTransactionByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeePensionTransaction> transactions = employee.getPensionTransactions()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeePensionTransactionResponse> response = transactions
                .stream()
                .map(EmployeePensionTransactionResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{transactionId}")
    @ApiOperation(
            value = "get single employee pension transaction by employee Id and transaction Id",
            response = EmployeePensionTransactionResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeAndTransactionId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                        @Valid @PathVariable @NotNull @Min(1) Long transactionId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeePensionTransaction transaction = helper.findPensionTransaction(transactionId, employee);

        return ok(success(EmployeePensionTransactionResponse.response(transaction)).getJson());
    }

    @GetMapping("/find/breakdown/{employeeId}/{transactionId}")
    @ApiOperation(
            value = "get pension transaction breakdown lists by employee id and transaction id",
            response = EmployeePensionTransactionBreakdownResponse.class
    )
    public ResponseEntity<JSONObject> findAllBreakdownByEmployeeIdAndTransactionId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                   @Valid @PathVariable @NotNull @Min(1) Long transactionId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeePensionTransaction transaction = helper.findPensionTransaction(transactionId, employee);

        List<EmployeePensionTransactionBreakdownResponse> response = transaction.getTransactionBreakdowns()
                .stream()
                .filter(f -> f.getRecordStatus() != DELETED)
                .map(EmployeePensionTransactionBreakdownResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/breakdown/{employeeId}/{transactionId}/{breakdownId}")
    @ApiOperation(
            value = "get single pension transaction breakdown by employee Id, transaction Id and breakdown Id",
            response = EmployeePensionTransactionBreakdownResponse.class
    )
    public ResponseEntity<JSONObject> findOneBreakdownByEmployeeIdAndTransactionId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                   @Valid @PathVariable @NotNull @Min(1) Long transactionId,
                                                                                   @Valid @PathVariable @NotNull @Min(1) Long breakdownId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeePensionTransaction transaction = helper.findPensionTransaction(transactionId, employee);

        EmployeePensionTransactionBreakdown breakdown = helper.findBreakdown(breakdownId, transaction);

        return ok(success(EmployeePensionTransactionBreakdownResponse.response(breakdown)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{transactionId}")
    @ApiOperation(
            value = "delete single pension transaction by employee Id and pension transaction Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteTransaction(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                        @Valid @PathVariable @NotNull @Min(1) Long transactionId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeePensionTransaction transaction = helper.findPensionTransaction(transactionId, employee);

        employee.addPensionTransactions(helper.delete(transaction));

        service.delete(employee);
        return ok(success(EMPLOYEE_PENSION_TRANSACTION_DELETE + " with id: " + transactionId).getJson());
    }

    @DeleteMapping("/delete/breakdown/{employeeId}/{transactionId}/{breakdownId}")
    @ApiOperation(
            value = "delete pension transaction breakdown by employee Id, transaction Id and breakdown Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteBreakdown(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                      @Valid @PathVariable @NotNull @Min(1) Long transactionId,
                                                      @Valid @PathVariable @NotNull @Min(1) Long breakdownId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeePensionTransaction transaction = helper.findPensionTransaction(transactionId, employee);

        EmployeePensionTransactionBreakdown breakdown = helper.findBreakdown(breakdownId, transaction);

        transaction.addBreakdown(helper.deleteBreakdown(breakdown));

        employee.addPensionTransaction(transaction);

        service.deleteBreakdown(employee, transaction);
        return ok(success(EMPLOYEE_PENSION_TRANSACTION_BREAKDOWN_DELETE + " with id: " + breakdownId).getJson());
    }
}
