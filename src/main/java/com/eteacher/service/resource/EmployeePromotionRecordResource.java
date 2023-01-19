package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeePromotionRecordDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeePromotionRecord;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeePromotionRecordHelper;
import com.eteacher.service.response.EmployeePromotionRecordResponse;
import com.eteacher.service.service.EmployeePromotionRecordService;
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
@Api(tags = "COMMON TEACHER---->Employee Promotion Record's  Data")
@RequestMapping(
        "api/v1/employee-promotion-record"
)
public class EmployeePromotionRecordResource {

    private final EmployeeService employeeService;

    private final EmployeePromotionRecordService service;

    private final EmployeePromotionRecordHelper helper;

    @PostMapping("/save")
    @ApiOperation(value = "save employee promotion record", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeePromotionRecordDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        employee.addPromotionRecords(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(null, EMPLOYEE_PROMOTION_RECORD_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee promotion record", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeePromotionRecordDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        employee.addPromotionRecords(distinct(helper.update(dto.getProfiles())));

        service.update(employee);
        return ok(success(null, EMPLOYEE_PROMOTION_RECORD_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}/{recordId}")
    @ApiOperation(value = "get single employee promotion record by employee Id and record Id",
            response = EmployeePromotionRecordResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndRecordId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                     @Valid @PathVariable @NotNull @Min(1) Long recordId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeePromotionRecord record = helper.findByRecordId(recordId, employee.getPromotionRecords());

        return ok(success(EmployeePromotionRecordResponse.response(record)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{recordId}")
    @ApiOperation(value = "delete employee promotion record by employee Id and record Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long recordId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeePromotionRecord record = helper.findByRecordId(recordId, employee.getPromotionRecords());

        employee.addPromotionRecords(helper.deleteOne(record));

        service.delete(employee);
        return ok(success(EMPLOYEE_PROMOTION_RECORD_DELETE + " with id: " + recordId).getJson());
    }

}
