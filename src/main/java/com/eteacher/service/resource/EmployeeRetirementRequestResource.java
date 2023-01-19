package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeeRetirementRequestDto;
import com.eteacher.service.dto.EmployeeRetirementRequestEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeRetirementRequest;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeRetirementRequestHelper;
import com.eteacher.service.response.EmployeeRetirementRequestResponse;
import com.eteacher.service.service.EmployeeRetirementRequestService;
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
@Api(tags = "MPO----> Employee Retirement Request's Data")
@RequestMapping(
        "api/v1/employee-retirement-request"
)
public class EmployeeRetirementRequestResource {

    private final EmployeeRetirementRequestService service;

    private final EmployeeRetirementRequestHelper helper;

    private final EmployeeService employeeService;

    @PostMapping("/save")
    @ApiOperation(value = "save employee retirement request", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeRetirementRequestDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addRetirementRequests(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_RETIREMENT_REQUEST_SAVE).getJson());
    }

    @PostMapping("/encloser/save")
    @ApiOperation(
            value = "save list or single employee retirement request encloser with employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveEncloser(@RequestPart(value = "files", required = false) MultipartFile[] files,
                                                   @Valid @RequestPart(value = "dto", required = false) EmployeeRetirementRequestEncloserDto dto,
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

        EmployeeRetirementRequest request = helper.findRequest(dto.getRequestId(), employee);

        request.addFiles(helper.save(files, dto));

        employee.addRetirementRequest(request);

        service.save(employee, request);
        return ok(success(EMPLOYEE_RETIREMENT_REQUEST_ENCLOSER_SAVE).getJson());
    }

    @PostMapping("/update")
    @ApiOperation(value = "update employee retirement request", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeRetirementRequestDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addRetirementRequests(distinct(helper.update(dto.getProfiles())));

        service.update(employee);
        return ok(success(EMPLOYEE_RETIREMENT_REQUEST_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee retirement request lists by employee id",
            response = EmployeeRetirementRequestResponse.class
    )
    public ResponseEntity<JSONObject> findAllRequestByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeeRetirementRequest> records = employee.getRetirementRequests()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeRetirementRequestResponse> response = records
                .stream()
                .map(EmployeeRetirementRequestResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{requestId}")
    @ApiOperation(value = "get single employee retirement request by record Id and request Id",
            response = EmployeeRetirementRequestResponse.class
    )
    public ResponseEntity<JSONObject> findOneByRequestAndEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                    @Valid @PathVariable @NotNull @Min(1) Long requestId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeRetirementRequest request = helper.findRequest(requestId, employee);

        return ok(success(EmployeeRetirementRequestResponse.response(request)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{requestId}")
    @ApiOperation(
            value = "delete single employee retirement request by employee Id and request Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long requestId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeRetirementRequest request = helper.findRequest(requestId, employee);

        employee.addRetirementRequest(helper.deleteOne(request));

        service.delete(employee, requestId);
        return ok(success(EMPLOYEE_RETIREMENT_REQUEST_DELETE + " with id: " + requestId).getJson());
    }
}
