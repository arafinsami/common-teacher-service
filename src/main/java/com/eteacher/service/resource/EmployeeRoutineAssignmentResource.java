package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeRoutineAssignmentDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeRoutineAssignment;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeRoutineAssignmentHelper;
import com.eteacher.service.response.EmployeeRoutineAssignmentResponse;
import com.eteacher.service.service.EmployeeRoutineAssignmentService;
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

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.CollectionUtils.distinct;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Employee Routine Assignment's  Data")
@RequestMapping(
        "api/v1/employee-routine-assignment"
)
public class EmployeeRoutineAssignmentResource {

    private final EmployeeService employeeService;

    private final EmployeeRoutineAssignmentService service;

    private final EmployeeRoutineAssignmentHelper helper;

    @PostMapping("/save")
    @ApiOperation(value = "save routine assignment", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeRoutineAssignmentDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        employee.addRoutineAssignments(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(null, EMPLOYEE_ROUTINE_ASSIGNMENTS_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee routine assignment", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeRoutineAssignmentDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        employee.addRoutineAssignments(distinct(helper.update(dto.getProfiles())));

        service.update(employee);
        return ok(success(null, EMPLOYEE_ROUTINE_ASSIGNMENTS_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}/{routineId}")
    @ApiOperation(value = "get single employee routine assignment by employee Id and routine Id",
            response = EmployeeRoutineAssignmentResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndRoutineId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                      @Valid @PathVariable @NotNull @Min(1) Long routineId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeRoutineAssignment assignment = helper.findByAssignmentId(routineId, employee.getRoutineAssignments());

        return ok(success(EmployeeRoutineAssignmentResponse.response(assignment)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{routineId}")
    @ApiOperation(value = "delete employee routine assignment by employee Id and routine Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long routineId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeRoutineAssignment assignment = helper.findByAssignmentId(routineId, employee.getRoutineAssignments());

        employee.addRoutineAssignments(helper.deleteOne(assignment));

        service.delete(employee);
        return ok(success(EMPLOYEE_ROUTINE_ASSIGNMENTS_DELETE + " with id: " + routineId).getJson());
    }

}
