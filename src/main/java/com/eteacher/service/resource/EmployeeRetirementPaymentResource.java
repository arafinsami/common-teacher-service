package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeeRetirementPaymentBreakdownDto;
import com.eteacher.service.dto.EmployeeRetirementPaymentDto;
import com.eteacher.service.dto.EmployeeRetirementPaymentEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeRetirementPayment;
import com.eteacher.service.entity.mpo.EmployeeRetirementPaymentBreakdown;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeRetirementPaymentHelper;
import com.eteacher.service.response.EmployeeRetirementPaymentBreakdownResponse;
import com.eteacher.service.response.EmployeeRetirementPaymentResponse;
import com.eteacher.service.service.EmployeeRetirementPaymentService;
import com.eteacher.service.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
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
@Api(tags = "MPO----> Employee Retirement Payment's Data")
@RequestMapping(
        "api/v1/employee-retirement-payment"
)
public class EmployeeRetirementPaymentResource {

    private final EmployeeRetirementPaymentService service;

    private final EmployeeRetirementPaymentHelper helper;

    private final EmployeeService employeeService;

    @PostMapping("/save")
    @ApiOperation(value = "save employee retirement payment", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeRetirementPaymentDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addRetirementPayments(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_RETIREMENT_PAYMENT_SAVE).getJson());
    }

    @PostMapping("/breakdown/save")
    @ApiOperation(
            value = "save employee retirement payment breakdown with payment Id and employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveBreakdown(@Valid @RequestBody EmployeeRetirementPaymentBreakdownDto dto,
                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeRetirementPayment payment = helper.findPayment(dto.getPaymentId(), employee);

        payment.addBreakdown(helper.save(dto.getProfile()));

        employee.addRetirementPayment(payment);

        service.save(employee, dto.getPaymentId());
        return ok(success(EMPLOYEE_RETIREMENT_PAYMENT_BREAKDOWN_SAVE).getJson());
    }

    @PostMapping("/encloser/save")
    @ApiOperation(
            value = "save list or single employee retirement payment encloser with employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveEncloser(@RequestPart(value = "files", required = false) MultipartFile[] files,
                                                   @Valid @RequestPart(value = "dto", required = false) EmployeeRetirementPaymentEncloserDto dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        if (dto.getProfiles().size() != Arrays.stream(files).count()) {
            return badRequest().body(error("you missing files").getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeRetirementPayment payment = helper.findPayment(dto.getPaymentId(), employee);

        payment.addFiles(helper.save(files, dto));

        employee.addRetirementPayment(payment);

        service.saveFiles(employee, dto.getPaymentId());
        return ok(success(EMPLOYEE_RETIREMENT_PAYMENT_ENCLOSER_SAVE).getJson());
    }

    @PostMapping("/update")
    @ApiOperation(value = "update employee retirement payment", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeRetirementPaymentDto request,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findByIdAndRecordStatusNot(request.getEmployeeId())
                .orElseThrow(ResourceNotFoundException::new);

        employee.addRetirementPayments(distinct(helper.update(request.getProfiles())));

        service.update(employee);
        return ok(success(EMPLOYEE_RETIREMENT_PAYMENT_UPDATE).getJson());
    }

    @PutMapping("/breakdown/update")
    @ApiOperation(
            value = "update employee retirement payment breakdown with payment Id and employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> updateReviewer(@Valid @RequestBody EmployeeRetirementPaymentBreakdownDto dto,
                                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeRetirementPayment payment = helper.findPayment(dto.getPaymentId(), employee);

        payment.addBreakdown(helper.update(dto.getProfile()));

        employee.addRetirementPayment(payment);

        service.update(employee, dto.getPaymentId(), dto.getProfile().getId());
        return ok(success(EMPLOYEE_RETIREMENT_PAYMENT_BREAKDOWN_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee retirement payment lists by employee id",
            response = EmployeeRetirementPaymentResponse.class
    )
    public ResponseEntity<JSONObject> findAllPaymentByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findByIdAndRecordStatusNot(employeeId)
                .orElseThrow(ResourceNotFoundException::new);

        List<EmployeeRetirementPayment> payments = employee.getRetirementPayments()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeRetirementPaymentResponse> response = payments.stream()
                .map(EmployeeRetirementPaymentResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{paymentId}")
    @ApiOperation(
            value = "get single employee retirement payment by payment Id and employee Id",
            response = EmployeeRetirementPaymentResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeAndPaymentId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                    @Valid @PathVariable @NotNull @Min(1) Long paymentId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeRetirementPayment payment = helper.findPayment(paymentId, employee);

        return ok(success(EmployeeRetirementPaymentResponse.response(payment)).getJson());
    }

    @GetMapping("/find/breakdown/{employeeId}/{paymentId}")
    @ApiOperation(
            value = "get retirement payment breakdown lists by employee id and mpo payment id",
            response = EmployeeRetirementPaymentBreakdownResponse.class
    )
    public ResponseEntity<JSONObject> findAllBreakdownByEmployeeIdAndPaymentId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                               @Valid @PathVariable @NotNull @Min(1) Long paymentId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeRetirementPayment payment = helper.findPayment(paymentId, employee);

        List<EmployeeRetirementPaymentBreakdownResponse> response = payment.getBreakdowns()
                .stream()
                .map(EmployeeRetirementPaymentBreakdownResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/breakdown/{employeeId}/{paymentId}/{breakdownId}")
    @ApiOperation(value = "get single retirement payment breakdown by employee Id, application Id and breakdown Id",
            response = EmployeeRetirementPaymentBreakdownResponse.class)
    public ResponseEntity<JSONObject> findBreakdownByEmployeeIdAndPaymentIdAndBreakdownId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                          @Valid @PathVariable @NotNull @Min(1) Long paymentId,
                                                                                          @Valid @PathVariable @NotNull @Min(1) Long breakdownId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeRetirementPayment payment = helper.findPayment(paymentId, employee);

        EmployeeRetirementPaymentBreakdown breakdown = helper.findBreakdown(breakdownId, payment);

        return ok(success(EmployeeRetirementPaymentBreakdownResponse.response(breakdown)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{paymentId}")
    @ApiOperation(
            value = "delete single employee retirement payment by employee Id and employee payment Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long paymentId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeRetirementPayment payment = helper.findPayment(paymentId, employee);

        employee.addRetirementPayment(helper.delete(payment));

        service.delete(employee, paymentId);
        return ok(success(EMPLOYEE_RETIREMENT_PAYMENT_DELETE + " with id: " + paymentId).getJson());
    }

    @DeleteMapping("/delete/breakdown/{employeeId}/{paymentId}/{breakdownId}")
    @ApiOperation(
            value = "get single retirement payment breakdown by employee Id, application Id and breakdown Id",
            response = EmployeeRetirementPaymentBreakdownResponse.class
    )
    public ResponseEntity<JSONObject> deleteBreakdown(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                      @Valid @PathVariable @NotNull @Min(1) Long paymentId,
                                                      @Valid @PathVariable @NotNull @Min(1) Long breakdownId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeRetirementPayment payment = helper.findPayment(paymentId, employee);

        EmployeeRetirementPaymentBreakdown breakdown = helper.findBreakdown(breakdownId, payment);

        payment.addBreakdown(helper.delete(breakdown));

        employee.addRetirementPayment(payment);

        service.delete(employee, paymentId, breakdownId);

        return ok(success(EMPLOYEE_RETIREMENT_PAYMENT_BREAKDOWN_DELETE + " with id: " + breakdownId).getJson());
    }
}
