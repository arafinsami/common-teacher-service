package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeePaymentArrearDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeePaymentArrear;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeePaymentarrearHelper;
import com.eteacher.service.response.EmployeePaymentArrearResponse;
import com.eteacher.service.service.EmployeePaymentArrearService;
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
@Api(tags = "MPO----> Employee Payment Arrear's Data")
@RequestMapping(
        "api/v1/employee-mpo-payment-arrear"
)
public class EmployeePaymentArrearResource {

    private final EmployeeService employeeService;

    private final EmployeePaymentArrearService service;

    private final EmployeePaymentarrearHelper helper;

    @PostMapping("/save")
    @ApiOperation(value = "save payment arrear by employee id", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeePaymentArrearDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addPaymentArrears(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_ARREAR_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update payment arrear by employee id", response = String.class)
    public ResponseEntity<JSONObject> updateJoining(@Valid @RequestBody EmployeePaymentArrearDto dto,
                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addPaymentArrears(distinct(helper.update(dto.getProfiles())));

        service.update(employee);
        return ok(success(EMPLOYEE_ARREAR_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(value = "get payment arrear lists by employee id", response = EmployeePaymentArrearResponse.class)
    public ResponseEntity<JSONObject> findByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeePaymentArrear> arrears = employee.getPaymentArrears()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeePaymentArrearResponse> response = arrears
                .stream()
                .map(EmployeePaymentArrearResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/findOne/{employeeId}/{arrearId}")
    @ApiOperation(value = "get single payment arrear by employee id", response = EmployeePaymentArrearResponse.class)
    public ResponseEntity<JSONObject> findOneByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                          @Valid @PathVariable @NotNull @Min(1) Long arrearId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeePaymentArrear arrear = helper.findArrear(arrearId, employee);

        return ok(success(EmployeePaymentArrearResponse.response(arrear)).getJson());
    }

    @PutMapping("/delete/{employeeId}/{arrearId}")
    @ApiOperation(value = "delete payment arrear by employee and arrear id",
            response = String.class)
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable("empId") @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable("joiningId") @NotNull @Min(1) Long arrearId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeePaymentArrear arrear = helper.findArrear(arrearId, employee);

        employee.addPaymentArrear(helper.delete(arrear));

        service.delete(employee, arrearId);
        return ok(success(EMPLOYEE_ARREAR_DELETE + " " + arrearId).getJson());
    }

}
