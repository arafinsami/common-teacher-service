package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeeMpoApplicationDto;
import com.eteacher.service.dto.EmployeeMpoApplicationEncloserDto;
import com.eteacher.service.dto.EmployeeMpoApplicationReviewerDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeMpoApplication;
import com.eteacher.service.entity.mpo.EmployeeMpoApplicationReviewer;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeMpoApplicationHelper;
import com.eteacher.service.response.EmployeeMpoApplicationResponse;
import com.eteacher.service.response.EmployeeMpoApplicationReviewerResponse;
import com.eteacher.service.service.EmployeeMpoApplicationService;
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
@Api(tags = "MPO----> Employee Mpo Application's Data")
@RequestMapping(
        "api/v1/employee-mpo-application"
)
public class EmployeeMpoApplicationResource {

    private final EmployeeMpoApplicationService service;

    private final EmployeeMpoApplicationHelper helper;

    private final EmployeeService employeeService;

    @PostMapping("/save")
    @ApiOperation(value = "save employee mpo application", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeMpoApplicationDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addMpoApplications(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_APPLICATION_SAVE).getJson());
    }

    @PostMapping("/save/reviewer")
    @ApiOperation(value = "save employee mpo application reviewer with mpo application Id and employee Id", response = String.class)
    public ResponseEntity<JSONObject> saveReviewer(@Valid @RequestBody EmployeeMpoApplicationReviewerDto dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeMpoApplication application = helper.findApplication(dto.getApplicationId(), employee);

        application.addReviewer(helper.save(dto.getProfile()));

        employee.addMpoApplication(application);

        service.save(employee, dto.getApplicationId());
        return ok(success(EMPLOYEE_APPLICATION_REVIEWER_SAVE).getJson());
    }

    @PostMapping("/encloser/save")
    @ApiOperation(
            value = "save list or single employee mpo encloser with employee Id and application Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveEncloser(@RequestPart(value = "files", required = false) MultipartFile[] files,
                                                   @Valid @RequestPart(value = "dto", required = false) EmployeeMpoApplicationEncloserDto dto,
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

        EmployeeMpoApplication application = helper.findApplication(dto.getApplicationId(), employee);

        application.addFiles(helper.save(files, dto));

        employee.addMpoApplication(application);

        service.save(employee, application);
        return ok(success(EMPLOYEE_LEAVE_RECORD_ENCLOSER_SAVE).getJson());
    }

    @PostMapping("/update")
    @ApiOperation(value = "update employee mpo application", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeMpoApplicationDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addMpoApplications(distinct(helper.update(dto.getProfiles())));

        service.update(employee);
        return ok(success(EMPLOYEE_APPLICATION_UPDATE).getJson());
    }

    @PutMapping("/update/reviewer")
    @ApiOperation(
            value = "update employee mpo application reviewer with mpo affiliation Id and employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> updateReviewer(@Valid @RequestBody EmployeeMpoApplicationReviewerDto dto,
                                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeMpoApplication application = helper.findApplication(dto.getApplicationId(), employee);

        application.addReviewer(helper.update(dto.getProfile()));

        employee.addMpoApplication(application);

        service.update(employee, dto.getApplicationId());
        return ok(success(EMPLOYEE_APPLICATION_REVIEWER_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee mpo application lists by employee id",
            response = EmployeeMpoApplicationResponse.class
    )
    public ResponseEntity<JSONObject> findAllApplicationByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeeMpoApplication> applications = employee.getMpoApplications()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeMpoApplicationResponse> response = applications.stream()
                .map(EmployeeMpoApplicationResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{applicationId}")
    @ApiOperation(
            value = "get single employee mpo application by employee Id and application Id",
            response = EmployeeMpoApplicationResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeAndAffiliationId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                        @Valid @PathVariable @NotNull @Min(1) Long applicationId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoApplication application = helper.findApplication(applicationId, employee);

        return ok(success(EmployeeMpoApplicationResponse.response(application)).getJson());
    }

    @GetMapping("/find/reviewer/{employeeId}/{applicationId}")
    @ApiOperation(
            value = "get mpo application reviewer lists by employee id and mpo application id",
            response = EmployeeMpoApplicationReviewerResponse.class
    )
    public ResponseEntity<JSONObject> findAllReviewerByEmployeeIdAndApplicationId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                  @Valid @PathVariable @NotNull @Min(1) Long applicationId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoApplication application = helper.findApplication(applicationId, employee);

        List<EmployeeMpoApplicationReviewerResponse> response = application.getReviewers()
                .stream()
                .map(EmployeeMpoApplicationReviewerResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/reviewer/{employeeId}/{applicationId}/{reviewerId}")
    @ApiOperation(
            value = "get single reviewer by employee Id, application Id and reviewer Id",
            response = EmployeeMpoApplicationReviewerResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeAndApplicationIdAndReviewerId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                     @Valid @PathVariable @NotNull @Min(1) Long applicationId,
                                                                                     @Valid @PathVariable @NotNull @Min(1) Long reviewerId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoApplication application = helper.findApplication(applicationId, employee);

        EmployeeMpoApplicationReviewer reviewer = helper.findReviewer(reviewerId, application);

        return ok(success(EmployeeMpoApplicationReviewerResponse.response(reviewer)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{applicationId}")
    @ApiOperation(
            value = "delete single employee mpo application by employee Id and employee application Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteApplication(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                        @Valid @PathVariable @NotNull @Min(1) Long applicationId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoApplication application = helper.findApplication(applicationId, employee);

        employee.addMpoApplication(helper.deleteOne(application));

        service.delete(employee, applicationId);
        return ok(success(EMPLOYEE_APPLICATION_DELETE + " with id: " + applicationId).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{applicationId}/{reviewerId}")
    @ApiOperation(
            value = "delete single employee mpo application by employee Id and employee application Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteReviewer(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                     @Valid @PathVariable @NotNull @Min(1) Long applicationId,
                                                     @Valid @PathVariable @NotNull @Min(1) Long reviewerId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoApplication application = helper.findApplication(applicationId, employee);

        EmployeeMpoApplicationReviewer reviewer = helper.findReviewer(reviewerId, application);

        application.addReviewer(helper.deleteOne(reviewer));

        employee.addMpoApplication(application);

        service.delete(employee, applicationId, reviewerId);
        return ok(success(EMPLOYEE_APPLICATION_REVIEWER_DELETE + " with id: " + applicationId).getJson());
    }
}
