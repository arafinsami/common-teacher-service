package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeEducationalQualificationDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeEducationalQualification;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeEducationalQualificationHelper;
import com.eteacher.service.response.EmployeeEducationalQualificationResponse;
import com.eteacher.service.service.EmployeeEducationalQualificationService;
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
import static com.eteacher.service.utils.CollectionUtils.distinct;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Employee Educational Qualification Data")
@RequestMapping(
        "api/v1/employee-educational-qualification"
)
public class EmployeeEducationalQualificationResource {

    private final EmployeeService employeeService;

    private final EmployeeEducationalQualificationService service;

    private final EmployeeEducationalQualificationHelper helper;

    @PostMapping("/save")
    @ApiOperation(value = "save employee educational qualification", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeEducationalQualificationDto dto,
                                           BindingResult bindingResult) {

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        employee.addEducationalQualifications(distinct(helper.save(dto.getQualifications())));

        service.save(employee);
        return ok(success(EMPLOYEE_EDUCATION_QUALIFICATION_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee educational qualification", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeEducationalQualificationDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        employee.addEducationalQualifications(distinct(helper.update(dto.getQualifications())));

        service.update(employee);
        return ok(success(EMPLOYEE_EDUCATION_QUALIFICATION_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee educational qualification lists by employee Id",
            response = EmployeeEducationalQualificationResponse.class
    )
    public ResponseEntity<JSONObject> findAllQualificationByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        List<EmployeeEducationalQualification> contacts = employee.getEducationalQualifications()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeEducationalQualificationResponse> response = contacts
                .stream()
                .map(EmployeeEducationalQualificationResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{qualificationId}")
    @ApiOperation(
            value = "get single employee educational qualification by employeeId and qualification Id",
            response = EmployeeEducationalQualificationResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndQualificationId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                            @Valid @PathVariable @NotNull @Min(1) Long qualificationId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeEducationalQualification qualification = helper.findByQualificationId(
                qualificationId,
                employee.getEducationalQualifications()
        );

        return ok(success(EmployeeEducationalQualificationResponse.response(qualification)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{qualificationId}")
    @ApiOperation(
            value = "delete employee educational qualification by employee and qualification id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long qualificationId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeEducationalQualification qualification = helper.findByQualificationId(
                qualificationId,
                employee.getEducationalQualifications()
        );

        employee.addEducationalQualifications(helper.deleteOne(qualification));

        service.delete(employee);
        return ok(success(EMPLOYEE_EDUCATION_QUALIFICATION_DELETE + " " + qualificationId).getJson());
    }
}
