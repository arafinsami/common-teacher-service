package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeeReleaseRecordDto;
import com.eteacher.service.dto.EmployeeReleaseRecordEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeReleaseRecord;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeReleaseRecordHelper;
import com.eteacher.service.response.EmployeeReleaseRecordResponse;
import com.eteacher.service.service.EmployeeReleaseRecordService;
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
@Api(tags = "MPO----> Employee Release Record's Data")
@RequestMapping(
        "api/v1/employee-release-record"
)
public class EmployeeReleaseRecordResource {

    private final EmployeeReleaseRecordService service;

    private final EmployeeReleaseRecordHelper helper;

    private final EmployeeService employeeService;

    @PostMapping("/save")
    @ApiOperation(value = "save employee release record", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeReleaseRecordDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addReleaseRecords(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_RELEASE_RECORD_SAVE).getJson());
    }

    @PostMapping("/encloser/save")
    @ApiOperation(
            value = "save list or single employee release record encloser with employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveEncloser(@RequestPart(value = "files", required = false) MultipartFile[] files,
                                                   @Valid @RequestPart(value = "dto", required = false) EmployeeReleaseRecordEncloserDto dto,
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

        EmployeeReleaseRecord record = helper.findRecord(dto.getRecordId(), employee);

        record.addFiles(helper.save(files, dto));

        employee.addReleaseRecord(record);

        service.save(employee, record);
        return ok(success(EMPLOYEE_RELEASE_RECORD_ENCLOSER_SAVE).getJson());
    }

    @PostMapping("/update")
    @ApiOperation(value = "update employee release record", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeReleaseRecordDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addReleaseRecords(distinct(helper.update(dto.getProfiles())));

        service.update(employee);
        return ok(success(EMPLOYEE_RELEASE_RECORD_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee release record lists by employee id",
            response = EmployeeReleaseRecordResponse.class
    )
    public ResponseEntity<JSONObject> findAllRecordByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeeReleaseRecord> records = employee.getReleaseRecords()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeReleaseRecordResponse> response = records
                .stream()
                .map(EmployeeReleaseRecordResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{recordId}")
    @ApiOperation(value = "get single employee release record by record Id and application Id",
            response = EmployeeReleaseRecordResponse.class
    )
    public ResponseEntity<JSONObject> findOneByRecordAndEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                   @Valid @PathVariable @NotNull @Min(1) Long recordId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeReleaseRecord record = helper.findRecord(recordId, employee);

        return ok(success(EmployeeReleaseRecordResponse.response(record)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{recordId}")
    @ApiOperation(value = "delete single employee release record by employee Id and record Id",
            response = String.class)
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long recordId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeReleaseRecord record = helper.findRecord(recordId, employee);

        employee.addReleaseRecord(helper.delete(record));

        service.delete(employee, record);
        return ok(success(EMPLOYEE_RELEASE_RECORD_DELETE + " with id: " + recordId).getJson());
    }
}
