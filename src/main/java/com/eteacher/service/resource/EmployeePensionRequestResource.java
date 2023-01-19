package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeePensionRequestDto;
import com.eteacher.service.dto.EmployeePensionRequestEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.govtteacher.EmployeePensionRequest;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeePensionRequestHelper;
import com.eteacher.service.response.EmployeePensionRequestResponse;
import com.eteacher.service.service.EmployeePensionRequestService;
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
@Api(tags = "GOV TEACHER--->Employee Pension Request's Data")
@RequestMapping(
        "api/v1/employee-pension-request"
)
public class EmployeePensionRequestResource {

    private final EmployeePensionRequestService service;

    private final EmployeePensionRequestHelper helper;

    private final EmployeeService employeeService;

    @PostMapping("/save")
    @ApiOperation(value = "save employee pension request", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeePensionRequestDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addPensionRequests(distinct(helper.savePensions(dto.getProfiles())));

        service.save(employee);
        return ok(success(RECRUITMENT_PENSION_REQUEST_SAVE).getJson());
    }

    @PostMapping("/encloser/save")
    @ApiOperation(value = "save employee pension request", response = String.class)
    public ResponseEntity<JSONObject> saveEncloser(@RequestPart(value = "files", required = false) MultipartFile[] files,
                                                   @Valid @RequestPart(value = "dto", required = false) EmployeePensionRequestEncloserDto dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeePensionRequest pensionRequest = helper.findPension(dto.getPensionRequestId(), employee);

        pensionRequest.addFiles(helper.saveEncloser(files, dto));

        employee.addPensionRequest(pensionRequest);

        service.saveEncloser(employee, pensionRequest);
        return ok(success(RECRUITMENT_PENSION_REQUEST_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee pension request", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeePensionRequestDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addPensionRequests(distinct(helper.updatePensions(dto.getProfiles())));

        service.update(employee);
        return ok(success(RECRUITMENT_PENSION_REQUEST_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee pension request lists by employee id",
            response = EmployeePensionRequestResponse.class
    )
    public ResponseEntity<JSONObject> findAllPensionByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeePensionRequest> pensionRequests = employee.getPensionRequests()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeePensionRequestResponse> response = pensionRequests
                .stream()
                .map(EmployeePensionRequestResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{pensionRequestId}")
    @ApiOperation(
            value = "get single employee pension request by employee Id and pension request Id",
            response = EmployeePensionRequestResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeAndPensionId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                    @Valid @PathVariable @NotNull @Min(1) Long pensionRequestId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeePensionRequest pensionRequest = helper.findPension(pensionRequestId, employee);

        return ok(success(EmployeePensionRequestResponse.response(pensionRequest)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{pensionRequestId}")
    @ApiOperation(
            value = "delete single employee pension request by employee Id and pension request Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long pensionRequestId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeePensionRequest pensionRequest = helper.findPension(pensionRequestId, employee);

        employee.addPensionRequests(helper.delete(pensionRequest));

        service.delete(employee);
        return ok(success(RECRUITMENT_PENSION_REQUEST_DELETE + " with id: " + pensionRequestId).getJson());
    }
}
