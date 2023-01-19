package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeLeaveRecordDto;
import com.eteacher.service.dto.EmployeeLeaveRecordEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeLeaveRecord;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeLeaveRecordHelper;
import com.eteacher.service.response.EmployeeLeaveRecordResponse;
import com.eteacher.service.service.EmployeeLeaveRecordService;
import com.eteacher.service.service.EmployeeService;
import com.eteacher.service.validation.EmployeeLeaveRecordValidator;
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

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.CollectionUtils.distinct;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Employee Leave Record Data")
@RequestMapping(
        "api/v1/employee-leave-record"
)
public class EmployeeLeaveRecordResource {

    private final EmployeeService employeeService;

    private final EmployeeLeaveRecordService service;

    private final EmployeeLeaveRecordHelper helper;

    private final EmployeeLeaveRecordValidator leaveValidator;

    @PostMapping("/save")
    @ApiOperation(value = "save employee leave  record", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeLeaveRecordDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(leaveValidator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployee())
        );

        employee.addLeaveRecords(distinct(helper.save(dto.getLeaveRecords())));

        service.save(employee);
        return ok(success(EMPLOYEE_LEAVE_RECORD_SAVE).getJson());
    }

    @PostMapping("/encloser/save")
    @ApiOperation(
            value = "save list or single employee leave record encloser with employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveEncloser(@RequestPart(value = "files", required = false) MultipartFile[] files,
                                                   @Valid @RequestPart(value = "dto", required = false) EmployeeLeaveRecordEncloserDto dto,
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

        employee.addLeaveRecordEnclosers(helper.save(files, dto));

        service.saveFiles(employee);
        return ok(success(EMPLOYEE_LEAVE_RECORD_ENCLOSER_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee leave record", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeLeaveRecordDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployee())
        );

        employee.addLeaveRecords(distinct(helper.update(dto.getLeaveRecords())));

        service.update(employee);
        return ok(success(EMPLOYEE_LEAVE_RECORD_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}/{recordId}")
    @ApiOperation(value = "get single employee leave Record by id", response = EmployeeLeaveRecordResponse.class)
    public ResponseEntity<JSONObject> findOneByEmployeeIdRecordId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                  @Valid @PathVariable @NotNull @Min(1) Long recordId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeLeaveRecord record = helper.findLeaveRecord(recordId, employee);

        return ok(success(EmployeeLeaveRecordResponse.from(record)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{recordId}")
    @ApiOperation(
            value = "delete employee leave record by employee Id and record Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long recordId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeLeaveRecord record = helper.findLeaveRecord(recordId, employee);

        employee.addLeaveRecords(helper.deleteOne(record));

        service.delete(employee);
        return ok(success(EMPLOYEE_LEAVE_RECORD_DELETE + " " + recordId).getJson());
    }
}
