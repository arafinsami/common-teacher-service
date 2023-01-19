package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeDepartmentalExamScoreDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.ntrca.EmployeeDepartmentalExamMeritScore;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeDepartmentalExamScoreHelper;
import com.eteacher.service.response.EmployeeDepartmentalExamMeritScoreResponse;
import com.eteacher.service.security.ActiveUserContext;
import com.eteacher.service.service.EmployeeDepartmentalExamMeritScoreService;
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
@RequestMapping("api/v1/departmental-exam-merit-score")
@Api(tags = "NTRCA---> Employee department exam merit score Data")
public class EmployeeDepartmentalExamMeritScoreResource {

    private final EmployeeService employeeService;

    private final EmployeeDepartmentalExamMeritScoreService service;

    private final EmployeeDepartmentalExamScoreHelper helper;

    private final ActiveUserContext context;

    @PostMapping("/save")
    @ApiOperation(value = "save employee departmental exam merit score", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeDepartmentalExamScoreDto dto) {

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addDepartmentalExamMeritScores(distinct(helper.save(dto.getMeritScores())));

        distinct(employee.getDepartmentalExamMeritScores()).forEach(f -> {
            f.setCreatedBy(UUID.fromString(context.getLoggedInUserId()));
            f.setRecordStatus(DRAFT);
        });

        service.save(employee);
        return ok(success(EMPLOYEE_EXAM_MERIT_SCORE_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee departmental exam merit score", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeDepartmentalExamScoreDto dto) {

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addDepartmentalExamMeritScores(distinct(helper.update(dto.getMeritScores())));

        distinct(employee.getDepartmentalExamMeritScores()).forEach(f -> {
            f.setUpdatedBy(UUID.fromString(context.getLoggedInUserId()));
            f.setRecordStatus(ACTIVE);
        });

        service.update(employee);
        return ok(success(EMPLOYEE_EXAM_MERIT_SCORE_UPDATE).getJson());
    }

    @GetMapping("/find/all/{employeeId}")
    @ApiOperation(
            value = "get employee departmental exam merit score list by employee Id",
            response = EmployeeDepartmentalExamMeritScoreResponse.class
    )
    public ResponseEntity<JSONObject> findAllByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeeDepartmentalExamMeritScoreResponse> responses = employee.getDepartmentalExamMeritScores()
                .stream()
                .filter(f -> f.getRecordStatus() != DELETED)
                .map(EmployeeDepartmentalExamMeritScoreResponse::response)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }

    @GetMapping("/find/{employeeId}/{meritId}")
    @ApiOperation(
            value = "get single employee departmental exam merit score employee Id and merit score Id",
            response = EmployeeDepartmentalExamMeritScoreResponse.class
    )
    public ResponseEntity<JSONObject> findByEmployeeIdAndMeritId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                 @Valid @PathVariable @NotNull @Min(1) Long meritId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeDepartmentalExamMeritScore merit = helper.findExamMeritScore(meritId, employee);

        return ok(success(EmployeeDepartmentalExamMeritScoreResponse.response(merit)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{meritId}")
    @ApiOperation(value = "delete employee departmental exam merit score by employee Id and merit Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long meritId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeDepartmentalExamMeritScore score = helper.findExamMeritScore(meritId, employee);

        employee.addDepartmentalExamMeritScores(helper.delete(score));

        service.delete(employee);
        return ok(success(EMPLOYEE_EXAM_MERIT_SCORE_DELETE + " with id: " + meritId).getJson());
    }
}
