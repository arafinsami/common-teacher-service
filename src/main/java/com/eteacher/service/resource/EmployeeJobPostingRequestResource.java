package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeJobPostingReqDto;
import com.eteacher.service.dto.EmployeeJobPostingRequestEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeJobPostingRequest;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeJobPostingRequestHelper;
import com.eteacher.service.response.EmployeeJobPostingRequestResponse;
import com.eteacher.service.service.EmployeeJobPostingRequestService;
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
@Api(tags = "MPO----> Employee Posting Request's Data")
@RequestMapping(
        "api/v1/employee-job-posting-request"
)
public class EmployeeJobPostingRequestResource {

    private final EmployeeService employeeService;

    private final EmployeeJobPostingRequestService service;

    private final EmployeeJobPostingRequestHelper helper;

    @PostMapping("/save")
    @ApiOperation(value = "save employee job posting request", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeJobPostingReqDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addJobPostingRequests(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_JOB_POSTING_SAVE).getJson());
    }

    @PostMapping("/encloser/save")
    @ApiOperation(
            value = "save list or single employee job posting encloser with employee Id and job posting Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveEncloser(@RequestPart(value = "files", required = false) MultipartFile[] files,
                                                   @Valid @RequestPart(value = "dto", required = false) EmployeeJobPostingRequestEncloserDto dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        if (dto.getProfiles().size() != Arrays.stream(files).count()) {
            return badRequest().body(error("you missing files").getJson());
        }

        Employee employee = employeeService.findById(dto.getJobPostingRequestId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeJobPostingRequest jobPostingRequest = helper.findJobPosting(dto.getJobPostingRequestId(), employee);

        jobPostingRequest.addFiles(helper.save(files, dto));

        employee.addJobPostingRequest(jobPostingRequest);

        service.save(employee, jobPostingRequest);
        return ok(success(EMPLOYEE_JOB_POSTING_ENCLOSER_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee job posting request", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeJobPostingReqDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addJobPostingRequests(distinct(helper.update(dto.getProfiles())));

        service.update(employee);
        return ok(success(EMPLOYEE_JOB_POSTING_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}/{jobPostingId}")
    @ApiOperation(
            value = "get single employee job posting request by employee Id and posting Id",
            response = EmployeeJobPostingRequestResponse.class
    )
    public ResponseEntity<JSONObject> findPostingByEmployeeIdAndPostingId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                  @Valid @PathVariable @NotNull @Min(1) Long jobPostingId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeJobPostingRequest jobPostingRequest = helper.findJobPosting(jobPostingId, employee);

        return ok(success(EmployeeJobPostingRequestResponse.response(jobPostingRequest)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{jobPostingRequestId}")
    @ApiOperation(
            value = "delete employee job posting request by employee Id and posting Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long jobPostingRequestId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeJobPostingRequest jobPostingRequest = helper.findJobPosting(jobPostingRequestId, employee);

        employee.addJobPostingRequest(helper.deleteOne(jobPostingRequest));

        service.delete(employee, jobPostingRequest);
        return ok(success(EMPLOYEE_JOB_POSTING_DELETE + " " + jobPostingRequestId).getJson());
    }
}
