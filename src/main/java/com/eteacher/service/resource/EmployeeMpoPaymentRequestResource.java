package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeeMpoPaymentReqDto;
import com.eteacher.service.dto.EmployeeMpoPaymentRequestBreakdownDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeMpoPaymentRequest;
import com.eteacher.service.entity.mpo.EmployeeMpoPaymentRequestBreakdown;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeMpoPaymentRequestHelper;
import com.eteacher.service.response.EmployeeMpoApplicationResponse;
import com.eteacher.service.response.EmployeeMpoPaymentRequestBreakdownResponse;
import com.eteacher.service.response.EmployeeMpoPaymentRequestResponse;
import com.eteacher.service.service.EmployeeMpoPaymentRequestService;
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
@Api(tags = "Employee Mpo Payment Request's Data")
@RequestMapping(
        "api/v1/employee-mpo-payment-request"
)
public class EmployeeMpoPaymentRequestResource {

    private final EmployeeMpoPaymentRequestService service;

    private final EmployeeMpoPaymentRequestHelper helper;

    private final EmployeeService employeeService;

    @PostMapping("/save")
    @ApiOperation(value = "save employee mpo payment request", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeMpoPaymentReqDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addMpoPaymentRequests(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_PAYMENT_REQUEST_SAVE).getJson());
    }

    @PostMapping("/breakdown/save")
    @ApiOperation(value = "save employee mpo payment request with mpo payment Id and employee Id", response = String.class)
    public ResponseEntity<JSONObject> saveReviewer(@Valid @RequestBody EmployeeMpoPaymentRequestBreakdownDto dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeMpoPaymentRequest paymentRequest = helper.findPayment(dto.getPaymentId(), employee);

        paymentRequest.addBreakDown(helper.save(dto.getSalaryBreakdown()));

        employee.addMpoPaymentRequest(paymentRequest);

        service.save(employee, dto.getPaymentId());
        return ok(success(EMPLOYEE_PAYMENT_REQUEST_BREAKDOWN_SAVE).getJson());
    }

    @PostMapping("/update")
    @ApiOperation(value = "update employee mpo payment request", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeMpoPaymentReqDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addMpoPaymentRequests(distinct(helper.update(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_PAYMENT_REQUEST_UPDATE).getJson());
    }

    @PutMapping("/breakdown/update")
    @ApiOperation(
            value = "save employee mpo payment request with mpo payment Id and employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> updateReviewer(@Valid @RequestBody EmployeeMpoPaymentRequestBreakdownDto dto,
                                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeMpoPaymentRequest paymentRequest = helper.findPayment(dto.getPaymentId(), employee);

        paymentRequest.addBreakDown(helper.save(dto.getSalaryBreakdown()));

        employee.addMpoPaymentRequest(paymentRequest);

        service.update(employee, dto.getPaymentId());
        return ok(success(EMPLOYEE_PAYMENT_REQUEST_BREAKDOWN_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(value = "get payment request lists by employee id", response = EmployeeMpoPaymentRequestResponse.class)
    public ResponseEntity<JSONObject> findAllByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeeMpoPaymentRequest> paymentRequests = employee.getMpoPaymentRequests()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeMpoPaymentRequestResponse> response = paymentRequests
                .stream()
                .map(EmployeeMpoPaymentRequestResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/payment/{employeeId}/{paymentRequestId}")
    @ApiOperation(value = "get single payment request by employee Id and payment request Id",
            response = EmployeeMpoApplicationResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeAndPaymentRequestId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                           @Valid @PathVariable @NotNull @Min(1) Long paymentRequestId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoPaymentRequest paymentRequest = helper.findPayment(paymentRequestId, employee);

        return ok(success(EmployeeMpoPaymentRequestResponse.response(paymentRequest)).getJson());
    }

    @GetMapping("/find/breakdown/{employeeId}/{paymentRequestId}")
    @ApiOperation(
            value = "get mpo payment request breakdown lists by employee id and mpo payment request id",
            response = EmployeeMpoPaymentRequestBreakdownResponse.class
    )
    public ResponseEntity<JSONObject> findAllByEmployeeIdAndPaymentRequestIdAndBreakDownId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                           @Valid @PathVariable @NotNull @Min(1) Long paymentRequestId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoPaymentRequest paymentRequest = helper.findPayment(paymentRequestId, employee);

        List<EmployeeMpoPaymentRequestBreakdownResponse> response = paymentRequest.getRequestBreakdowns()
                .stream()
                .map(EmployeeMpoPaymentRequestBreakdownResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/breakdown/{employeeId}/{paymentRequestId}/{breakDownId}")
    @ApiOperation(value = "get single payment request breakdown by employee Id, payment request Id and breakdown Id",
            response = EmployeeMpoPaymentRequestBreakdownResponse.class)
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndPaymentRequestIdAndBreakDownId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                           @Valid @PathVariable @NotNull @Min(1) Long paymentRequestId,
                                                                                           @Valid @PathVariable @NotNull @Min(1) Long breakDownId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoPaymentRequest paymentRequest = helper.findPayment(paymentRequestId, employee);

        EmployeeMpoPaymentRequestBreakdown breakdown = helper.findBreakdown(breakDownId, paymentRequest);

        return ok(success(EmployeeMpoPaymentRequestBreakdownResponse.response(breakdown)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{paymentRequestId}")
    @ApiOperation(value = "delete single payment request by employee Id and employee payment request Id",
            response = String.class)
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long paymentRequestId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoPaymentRequest paymentRequest = helper.findPayment(paymentRequestId, employee);

        employee.addMpoPaymentRequest(helper.deleteOne(paymentRequest));

        service.delete(employee, paymentRequestId);
        return ok(success(EMPLOYEE_PAYMENT_REQUEST_DELETE + " with id: " + paymentRequestId).getJson());
    }

    @DeleteMapping("/delete/breakdown/{employeeId}/{paymentRequestId}/{breakDownId}")
    @ApiOperation(value = "delete single payment request by employee Id and employee payment request Id",
            response = String.class)
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long paymentRequestId,
                                             @Valid @PathVariable @NotNull @Min(1) Long breakDownId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoPaymentRequest paymentRequest = helper.findPayment(paymentRequestId, employee);

        EmployeeMpoPaymentRequestBreakdown breakdown = helper.findBreakdown(breakDownId, paymentRequest);

        paymentRequest.addBreakDown(breakdown);

        employee.addMpoPaymentRequest(helper.deleteOne(paymentRequest));

        service.delete(employee, paymentRequestId, breakDownId);
        return ok(success(EMPLOYEE_PAYMENT_REQUEST_BREAKDOWN_DELETE + " with id: " + breakDownId).getJson());
    }
}
