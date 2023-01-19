package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeeAttritionRecordDto;
import com.eteacher.service.dto.EmployeeAttritionRecordEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeAttritionRecord;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeAttritionRecordHelper;
import com.eteacher.service.response.EmployeeAttritionRecordResponse;
import com.eteacher.service.service.EmployeeAttritionRecordService;
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
@Api(tags = "MPO---> Employee Attrition Record's Data")
@RequestMapping(
        "api/v1/employee-attrition-record"
)
public class EmployeeAttritionRecordResource {

    private final EmployeeAttritionRecordService service;

    private final EmployeeAttritionRecordHelper helper;

    private final EmployeeService employeeService;

    @PostMapping("/save")
    @ApiOperation(value = "save employee attrition record", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeAttritionRecordDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addAttritionRecords(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_ATTRITION_SAVE).getJson());
    }

    @PostMapping("/encloser/save")
    @ApiOperation(
            value = "save list/single encloser with employee Id and record Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveEncloser(@RequestPart(value = "files", required = false) MultipartFile[] files,
                                                   @Valid @RequestPart(value = "dto", required = false) EmployeeAttritionRecordEncloserDto dto,
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

        EmployeeAttritionRecord record = helper.findRecord(dto.getRecordId(), employee);

        record.addFiles(helper.save(files, dto));

        employee.addAttritionRecord(record);

        service.save(employee, record);
        return ok(success(EMPLOYEE_ATTRITION_ENCLOSER_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee attrition record", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeAttritionRecordDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addAttritionRecords(distinct(helper.update(dto.getProfiles())));

        service.update(employee);
        return ok(success(EMPLOYEE_ATTRITION_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}/{recordId}")
    @ApiOperation(
            value = "get single attrition record by employee Id and record Id",
            response = EmployeeAttritionRecordResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndJoiningId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                      @Valid @PathVariable @NotNull @Min(1) Long recordId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeAttritionRecord record = helper.findRecord(recordId, employee);

        return ok(success(EmployeeAttritionRecordResponse.response(record)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{recordId}")
    @ApiOperation(
            value = "delete employee attrition record by employee and record id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long recordId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeAttritionRecord record = helper.findRecord(recordId, employee);

        employee.addAttritionRecords(helper.deleteOne(record));

        service.delete(employee, record);
        return ok(success(EMPLOYEE_JOINING_DELETE + " " + recordId).getJson());
    }
}
