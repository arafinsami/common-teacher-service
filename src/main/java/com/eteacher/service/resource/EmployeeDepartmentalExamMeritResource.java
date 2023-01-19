package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeDepartmentalExamMeritDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.ntrca.EmployeeDepartmentalExamMerit;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeDepartmentExaminationMeritHelper;
import com.eteacher.service.response.EmployeeDepartmentalExamMeritResponse;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.service.EmployeeDepartmentalExamMeritService;
import com.eteacher.service.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE;
import static com.eteacher.service.enums.RecordStatus.*;
import static com.eteacher.service.utils.CollectionUtils.distinct;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "NTRCA---> Employee department examination merit Data")
@RequestMapping(
        "api/v1/departmental-exam-merit"
)
public class EmployeeDepartmentalExamMeritResource {

    private final EmployeeService employeeService;

    private final EmployeeDepartmentalExamMeritService service;

    private final EmployeeDepartmentExaminationMeritHelper helper;

    private final ActiveUserContext context;

    @PostMapping("/save")
    @ApiOperation(value = "save employee departmental exam merit", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeDepartmentalExamMeritDto dto) {

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployee())
        );

        employee.addDepartmentalExamMerits(distinct(helper.save(dto.getDepartmentalExamMerits())));

        distinct(employee.getDepartmentalExamMerits()).forEach(f -> {
            f.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
            f.setRecordStatus(DRAFT);
        });

        service.save(employee);
        return ok(success(EMPLOYEE_EXAM_MERIT_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee departmental exam merit", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeDepartmentalExamMeritDto dto) {

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployee())
        );

        employee.addDepartmentalExamMerits(distinct(helper.update(dto.getDepartmentalExamMerits())));

        distinct(employee.getDepartmentalExamMerits()).forEach(f -> {
            f.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
            f.setRecordStatus(ACTIVE);
        });

        service.update(employee);
        return ok(success(EMPLOYEE_EXAM_MERIT_UPDATE).getJson());
    }

    @GetMapping("/find/all/{employeeId}")
    @ApiOperation(
            value = "get employee departmental exam merit list by employee Id",
            response = EmployeeDepartmentalExamMeritResponse.class
    )
    public ResponseEntity<JSONObject> findAllByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeeDepartmentalExamMeritResponse> responses = employee.getDepartmentalExamMerits()
                .stream()
                .filter(f -> f.getRecordStatus() != DELETED)
                .map(EmployeeDepartmentalExamMeritResponse::response)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }

    @GetMapping("/find/{employeeId}/{meritId}")
    @ApiOperation(
            value = "get single employee departmental exam merit by employee Id and merit Id",
            response = EmployeeDepartmentalExamMeritResponse.class
    )
    public ResponseEntity<JSONObject> findByEmployeeIdAndDepartmentId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                      @Valid @PathVariable @NotNull @Min(1) Long meritId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeDepartmentalExamMerit merit = helper.findExamMerit(meritId, employee);

        return ok(success(EmployeeDepartmentalExamMeritResponse.response(merit)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{meritId}")
    @ApiOperation(value = "delete employee departmental exam merit by employee Id and merit Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long meritId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeDepartmentalExamMerit examMerit = helper.findExamMerit(meritId, employee);

        employee.addDepartmentalExamMerits(helper.delete(examMerit));

        service.delete(employee);
        return ok(success(EMPLOYEE_EXAM_MERIT_DELETE + " with id: " + meritId).getJson());
    }
}
