package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeeJobExperienceDetailDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeJobExperienceDetail;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.response.EmployeeJobExperienceDetailResponse;
import com.eteacher.service.service.EmployeeJobExperienceDetailService;
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
import static com.eteacher.service.enums.RecordStatus.DELETED;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Employee job experience detail Data")
@RequestMapping(
        "api/v1/employee-job-experience-detail"
)
public class EmployeeJobExperienceDetailResource {

    private final EmployeeService employeeService;

    private final EmployeeJobExperienceDetailService service;

    @PostMapping("/save")
    @ApiOperation(value = "save employee job experience details", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeJobExperienceDetailDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        service.save(employee, dto);
        return ok(success(null, EMPLOYEE_TRAINING_DETAILS_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee job experience details", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeJobExperienceDetailDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        service.update(employee, dto, RecordStatus.ACTIVE);
        return ok(success(null, EMPLOYEE_JOB_EXPERIENCE_DETAILS_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee job experience details by employee Id",
            response = EmployeeJobExperienceDetailResponse.class
    )
    public ResponseEntity<JSONObject> findAllByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        List<EmployeeJobExperienceDetail> lists = employee.getJobExperiences()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeJobExperienceDetailResponse> response = lists
                .stream()
                .map(EmployeeJobExperienceDetailResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{experienceId}")
    @ApiOperation(
            value = "get single employee joining by employee Id and experience Id",
            response = EmployeeJobExperienceDetailResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndExperienceId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                         @Valid @PathVariable @NotNull @Min(1) Long experienceId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeJobExperienceDetail experienceDetail = findByExperienceId(experienceId, employee.getJobExperiences());

        return ok(success(EmployeeJobExperienceDetailResponse.response(experienceDetail)).getJson());
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "delete employee job experience details", response = String.class)
    public ResponseEntity<JSONObject> delete(@Valid @RequestBody EmployeeJobExperienceDetailDto request) {

        Employee employee = employeeService.findById(request.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        service.update(employee, request, RecordStatus.DELETED);
        return ok(success(null, EMPLOYEE_JOB_EXPERIENCE_DETAILS_DELETE).getJson());
    }

    public EmployeeJobExperienceDetail findByExperienceId(Long experienceId, List<EmployeeJobExperienceDetail> details) {
        for (EmployeeJobExperienceDetail detail : details) {
            if (detail.getId().equals(experienceId)) {
                return detail;
            }
        }
        return null;
    }

}
