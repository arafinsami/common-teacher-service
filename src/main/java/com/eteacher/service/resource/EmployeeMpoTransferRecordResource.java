package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeeTransferRecordDto;
import com.eteacher.service.dto.EmployeeTransferRecordEncloserDto;
import com.eteacher.service.dto.EmployeeTransferRecordPreferredInstituteDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeTransferRecord;
import com.eteacher.service.entity.mpo.EmployeeTransferRecordPreferredInstitute;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeMpoTransferRecordHelper;
import com.eteacher.service.response.EmployeeTransferRecordPreferredInstituteResponse;
import com.eteacher.service.response.EmployeeTransferRecordResponse;
import com.eteacher.service.service.EmployeeMpoTransferRecordService;
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
@Api(tags = "MPO---> Employee Mpo Transfer Record's Data")
@RequestMapping(
        "api/v1/employee-mpo-transfer-record"
)
public class EmployeeMpoTransferRecordResource {

    private final EmployeeMpoTransferRecordService service;

    private final EmployeeMpoTransferRecordHelper helper;

    private final EmployeeService employeeService;

    @PostMapping("/save")
    @ApiOperation(value = "save employee mpo transfer record", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeTransferRecordDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addTransferRecords(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_TRANSFER_RECORD_SAVE).getJson());
    }

    @PostMapping("/preferred-institute/save")
    @ApiOperation(
            value = "save employee mpo transfer record preferred institute with mpo employee Id and record Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveInstitute(@Valid @RequestBody EmployeeTransferRecordPreferredInstituteDto dto,
                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeTransferRecord record = helper.findByRecord(dto.getRecordId(), employee);

        record.addPreferredInstitute(helper.save(dto.getProfile()));

        employee.addTransferRecord(record);

        service.save(employee, dto.getRecordId());
        return ok(success(EMPLOYEE_TRANSFER_RECORD_INSTITUTE_SAVE).getJson());
    }

    @PostMapping("/encloser/save")
    @ApiOperation(
            value = "save list of encloser or single encloser mpo employee Id and record Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveEncloser(@RequestPart(value = "files", required = false) MultipartFile[] files,
                                                   @Valid @RequestPart(value = "dto", required = false) EmployeeTransferRecordEncloserDto dto,
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

        EmployeeTransferRecord record = helper.findByRecord(dto.getRecordId(), employee);

        record.addFiles(helper.save(files, dto));

        employee.addTransferRecord(record);

        service.saveFiles(employee, dto.getRecordId());
        return ok(success(EMPLOYEE_TRANSFER_RECORD_FILE_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee mpo transfer record", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeTransferRecordDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addTransferRecords(distinct(helper.update(dto.getProfiles())));

        service.update(employee);
        return ok(success(EMPLOYEE_TRANSFER_RECORD_UPDATE).getJson());
    }

    @PutMapping("/preferred-institute/update")
    @ApiOperation(
            value = "update employee mpo transfer record preferred institute with mpo employee Id and record Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> updateInstitute(@Valid @RequestBody EmployeeTransferRecordPreferredInstituteDto dto,
                                                      BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeTransferRecord record = helper.findByRecord(dto.getRecordId(), employee);

        record.addPreferredInstitute(helper.update(dto.getProfile()));

        employee.addTransferRecord(record);

        service.update(employee, dto.getRecordId());
        return ok(success(EMPLOYEE_TRANSFER_RECORD_INSTITUTE_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee mpo transfer record lists by employee id",
            response = EmployeeTransferRecordResponse.class
    )
    public ResponseEntity<JSONObject> findAllApplicationByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeeTransferRecord> records = employee.getTransferRecords()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeTransferRecordResponse> response = records
                .stream()
                .map(EmployeeTransferRecordResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{recordId}")
    @ApiOperation(value = "get single employee transfer record by employee Id and record Id",
            response = EmployeeTransferRecordResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeAndRecordId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                   @Valid @PathVariable @NotNull @Min(1) Long recordId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeTransferRecord record = helper.findByRecord(recordId, employee);

        return ok(success(EmployeeTransferRecordResponse.response(record)).getJson());
    }

    @GetMapping("/find/preferred-institute/{employeeId}/{recordId}")
    @ApiOperation(
            value = "get preferred institute lists by employee id and record id",
            response = EmployeeTransferRecordPreferredInstituteResponse.class
    )
    public ResponseEntity<JSONObject> findAllInstituteByEmployeeAndRecordId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                            @Valid @PathVariable @NotNull @Min(1) Long recordId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeTransferRecord record = helper.findByRecord(recordId, employee);

        List<EmployeeTransferRecordPreferredInstituteResponse> response = record.getPreferredInstitutes()
                .stream()
                .map(EmployeeTransferRecordPreferredInstituteResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/preferred-institute/{employeeId}/{recordId}/{instituteId}")
    @ApiOperation(
            value = "get single preferred institute lists by employee id, record id and institute Id",
            response = EmployeeTransferRecordPreferredInstituteResponse.class
    )
    public ResponseEntity<JSONObject> findAllInstituteByEmployeeAndRecordIdAndInstituteId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                          @Valid @PathVariable @NotNull @Min(1) Long recordId,
                                                                                          @Valid @PathVariable @NotNull @Min(1) Long instituteId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeTransferRecord record = helper.findByRecord(recordId, employee);

        EmployeeTransferRecordPreferredInstitute institute = helper.findByInstitute(instituteId, record);

        return ok(success(EmployeeTransferRecordPreferredInstituteResponse.response(institute)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{recordId}")
    @ApiOperation(
            value = "delete single transfer record by employee Id and record Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long recordId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeTransferRecord record = helper.findByRecord(recordId, employee);

        employee.addTransferRecord(helper.deleteOne(record));

        service.delete(employee, record);
        return ok(success(EMPLOYEE_TRANSFER_RECORD_DELETE + " with id: " + recordId).getJson());
    }

    @DeleteMapping("/delete/preferred-institute/{employeeId}/{recordId}/{instituteId}")
    @ApiOperation(
            value = "delete single transfer record by employee Id and record Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteInstitute(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                      @Valid @PathVariable @NotNull @Min(1) Long recordId,
                                                      @Valid @PathVariable @NotNull @Min(1) Long instituteId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeTransferRecord record = helper.findByRecord(recordId, employee);

        EmployeeTransferRecordPreferredInstitute institute = helper.findByInstitute(instituteId, record);

        record.addPreferredInstitute(institute);

        employee.addTransferRecord(helper.deleteOne(record));

        service.delete(employee, recordId, instituteId);
        return ok(success(EMPLOYEE_TRANSFER_RECORD_INSTITUTE_DELETE + " with id: " + recordId).getJson());
    }
}
