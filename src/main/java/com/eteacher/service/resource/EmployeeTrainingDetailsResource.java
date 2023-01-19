package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeTrainingDetailsDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeTrainingDetail;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeTrainingDetailsHelper;
import com.eteacher.service.response.EmployeeTrainingDetailResponse;
import com.eteacher.service.service.EmployeeService;
import com.eteacher.service.service.EmployeeTrainingDetailsService;
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
@Api(tags = "COMMON TEACHER---->Employee Training Details  Data")
@RequestMapping(
        "api/v1/employee-training-details"
)
public class EmployeeTrainingDetailsResource {

    private final EmployeeService employeeService;

    private final EmployeeTrainingDetailsHelper helper;

    private final EmployeeTrainingDetailsService service;

    @PostMapping("/save")
    @ApiOperation(value = "save employee training details", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeTrainingDetailsDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        employee.addTrainings(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(null, EMPLOYEE_TRAINING_DETAILS_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee training details record", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeTrainingDetailsDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        employee.addTrainings(distinct(helper.update(dto.getProfiles())));

        service.update(employee);
        return ok(success(null, EMPLOYEE_TRAINING_DETAILS_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}/{trainingId}")
    @ApiOperation(
            value = "get single employee training details by employee Id and training Id",
            response = EmployeeTrainingDetailResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndTrainingId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                       @Valid @PathVariable @NotNull @Min(1) Long trainingId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeTrainingDetail detail = helper.findByTrainingId(trainingId, employee.getTrainings());

        return ok(success(EmployeeTrainingDetailResponse.response(detail)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{trainingId}")
    @ApiOperation(value = "delete employee training details by employee Id and training Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long trainingId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeTrainingDetail detail = helper.findByTrainingId(trainingId, employee.getTrainings());

        employee.addTrainings(helper.deleteOne(detail));

        service.delete(employee);
        return ok(success(EMPLOYEE_TRAINING_DETAILS_DELETE + " with id: " + trainingId).getJson());
    }
}
