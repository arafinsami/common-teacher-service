package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeJoiningDto;
import com.eteacher.service.dto.EmployeeJoiningEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeJoining;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeJoiningHelper;
import com.eteacher.service.response.EmployeeJoiningResponse;
import com.eteacher.service.service.EmployeeJoiningService;
import com.eteacher.service.service.EmployeeService;
import com.eteacher.service.validation.EmployeeJoiningValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
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
@Api(tags = "COMMON TEACHER---->Employee Joining Data")
@RequestMapping(
        "api/v1/employee-joining"
)
public class EmployeeJoiningResource {

    private final EmployeeService employeeService;

    private final EmployeeJoiningService service;

    private final EmployeeJoiningHelper helper;

    private final EmployeeJoiningValidator joiningValidator;

    @PostMapping("/save")
    @ApiOperation(value = "save employee joining", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeJoiningDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(joiningValidator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployee())
        );

        employee.addJoinings(distinct(helper.save(dto.getJoining())));

        service.save(employee);
        return ok(success(EMPLOYEE_JOINING_SAVE).getJson());
    }

    @PostMapping("/encloser/save")
    @ApiOperation(
            value = "save list or single employee joining encloser with employee Id and joining Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveEncloser(@RequestPart(value = "files", required = false) MultipartFile[] files,
                                                   @Valid @RequestPart(value = "dto", required = false) EmployeeJoiningEncloserDto dto,
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

        EmployeeJoining joining = helper.findJoining(dto.getJoiningId(), employee);

        joining.addFiles(helper.save(files, dto));

        employee.addJoining(joining);

        service.saveFiles(employee, joining);
        return ok(success(EMPLOYEE_TRANSFER_RECORD_FILE_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee joining", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeJoiningDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployee())
        );

        employee.addJoinings(distinct(helper.update(dto.getJoining())));

        service.update(employee);
        return ok(success(EMPLOYEE_JOINING_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(value = "get employee joining by id", response = EmployeeJoiningResponse.class)
    public ResponseEntity<JSONObject> findAllByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeeJoining> lists = employee.getEmployeeJoinings()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeJoiningResponse> response = lists
                .stream()
                .map(EmployeeJoiningResponse::from)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{joiningId}")
    @ApiOperation(
            value = "get single employee joining by employee Id and joining Id",
            response = EmployeeJoiningResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndJoiningId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                      @Valid @PathVariable @NotNull @Min(1) Long joiningId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeJoining joining = helper.findJoining(joiningId, employee);

        return ok(success(EmployeeJoiningResponse.from(joining)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{joiningId}")
    @ApiOperation(
            value = "delete employee joining by employee and joining id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long joiningId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeJoining joining = helper.findJoining(joiningId, employee);

        employee.addJoinings(helper.deleteOne(joining));

        service.delete(employee);
        return ok(success(EMPLOYEE_JOINING_DELETE + " " + joiningId).getJson());
    }

}
