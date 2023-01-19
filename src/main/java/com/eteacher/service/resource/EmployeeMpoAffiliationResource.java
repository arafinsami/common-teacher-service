package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeMpoAffiliationDto;
import com.eteacher.service.dto.EmployeeMpoAffiliationEncloserDto;
import com.eteacher.service.dto.EmployeeMpoAffiliationReviewerDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.mpo.EmployeeMpoAffiliation;
import com.eteacher.service.entity.mpo.EmployeeMpoAffiliationReviewer;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeMpoAffiliationHelper;
import com.eteacher.service.response.EmployeeMpoAffiliationResponse;
import com.eteacher.service.response.EmployeeMpoAffiliationReviewerResponse;
import com.eteacher.service.service.EmployeeMpoAffiliationService;
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
@Api(tags = "MPO----> Employee Mpo Affiliation's Data")
@RequestMapping(
        "api/v1/employee-mpo-affiliation"
)
public class EmployeeMpoAffiliationResource {

    private final EmployeeMpoAffiliationService service;

    private final EmployeeService employeeService;

    private final EmployeeMpoAffiliationHelper helper;

    @PostMapping("/save")
    @ApiOperation(value = "save affiliation with employee Id", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeMpoAffiliationDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addMpoAffiliations(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_AFFILIATION_SAVE).getJson());
    }

    @PostMapping("/reviewer/save")
    @ApiOperation(
            value = "save affiliation reviewer with affiliation Id and employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveReviewer(@Valid @RequestBody EmployeeMpoAffiliationReviewerDto dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeMpoAffiliation affiliation = helper.findAffiliation(dto.getMpoAffiliationId(), employee);

        affiliation.addReviewer(helper.save(dto.getProfile()));

        employee.addMpoAffiliation(affiliation);

        service.save(employee, dto.getMpoAffiliationId());
        return ok(success(EMPLOYEE_AFFILIATION_REVIEWER_SAVE).getJson());
    }

    @PostMapping("/encloser/save")
    @ApiOperation(
            value = "save list or single employee leave record encloser with employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveEncloser(@RequestPart(value = "files", required = false) MultipartFile[] files,
                                                   @Valid @RequestPart(value = "dto", required = false) EmployeeMpoAffiliationEncloserDto dto,
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

        EmployeeMpoAffiliation affiliation = helper.findAffiliation(dto.getAffiliationId(), employee);

        affiliation.addFiles(helper.save(files, dto));

        service.saveFiles(employee, dto.getAffiliationId());
        return ok(success(EMPLOYEE_AFFILIATION_ENCLOSER_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update affiliation with employee Id", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeMpoAffiliationDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addMpoAffiliations(distinct(helper.update(dto.getProfiles())));

        service.update(employee);
        return ok(success(EMPLOYEE_AFFILIATION_UPDATE).getJson());
    }

    @PutMapping("/reviewer/update")
    @ApiOperation(
            value = "update affiliation reviewer with employee Id and affiliation Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> updateReviewer(@Valid @RequestBody EmployeeMpoAffiliationReviewerDto dto,
                                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeMpoAffiliation affiliation = helper.findAffiliation(dto.getMpoAffiliationId(), employee);

        affiliation.addReviewer(helper.update(dto.getProfile()));

        employee.addMpoAffiliation(affiliation);

        service.update(employee, dto.getMpoAffiliationId());
        return ok(success(EMPLOYEE_AFFILIATION_REVIEWER_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get affiliation lists by employee id",
            response = EmployeeMpoAffiliationResponse.class
    )
    public ResponseEntity<JSONObject> findAllAffiliationByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findByIdAndRecordStatusNot(employeeId)
                .orElseThrow(ResourceNotFoundException::new);

        List<EmployeeMpoAffiliation> affiliations = employee.getMpoAffiliations()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeMpoAffiliationResponse> response = affiliations
                .stream()
                .map(EmployeeMpoAffiliationResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{affiliationId}")
    @ApiOperation(
            value = "get single affiliation by employee Id and affiliation Id",
            response = EmployeeMpoAffiliationResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeAndAffiliationId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                        @Valid @PathVariable @NotNull @Min(1) Long affiliationId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoAffiliation affiliation = helper.findAffiliation(affiliationId, employee);

        return ok(success(EmployeeMpoAffiliationResponse.response(affiliation)).getJson());
    }

    @GetMapping("/find/reviewer/{employeeId}/{affiliationId}")
    @ApiOperation(
            value = "get affiliation reviewer lists by employee Id and affiliation Id",
            response = EmployeeMpoAffiliationReviewerResponse.class
    )
    public ResponseEntity<JSONObject> findAllReviewerByEmployeeIdAndAffiliationId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                  @Valid @PathVariable @NotNull @Min(1) Long affiliationId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoAffiliation affiliation = helper.findAffiliation(affiliationId, employee);

        List<EmployeeMpoAffiliationReviewerResponse> response = affiliation.getReviewers()
                .stream()
                .map(EmployeeMpoAffiliationReviewerResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/reviewer/{employeeId}/{affiliationId}/{reviewerId}")
    @ApiOperation(
            value = "get single reviewer by employee Id, affiliation Id and reviewer Id",
            response = EmployeeMpoAffiliationResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndAffiliationIdAndReviewerId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                       @Valid @PathVariable @NotNull @Min(1) Long affiliationId,
                                                                                       @Valid @PathVariable @NotNull @Min(1) Long reviewerId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoAffiliation affiliation = helper.findAffiliation(affiliationId, employee);

        EmployeeMpoAffiliationReviewer reviewer = helper.findReviewer(reviewerId, affiliation);

        return ok(success(EmployeeMpoAffiliationReviewerResponse.response(reviewer)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{affiliationId}")
    @ApiOperation(
            value = "delete single affiliation by employee Id and affiliation Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteAffiliation(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                        @Valid @PathVariable @NotNull @Min(1) Long affiliationId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoAffiliation affiliation = helper.findAffiliation(affiliationId, employee);

        employee.addMpoAffiliation(helper.deleteOne(affiliation));

        service.delete(employee, affiliationId);
        return ok(success(EMPLOYEE_AFFILIATION_DELETE + " with id: " + affiliationId).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{affiliationId}/{reviewerId}")
    @ApiOperation(
            value = "delete single reviewer by employee Id, affiliation Id and reviewer Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteReviewer(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                     @Valid @PathVariable @NotNull @Min(1) Long affiliationId,
                                                     @Valid @PathVariable @NotNull @Min(1) Long reviewerId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeMpoAffiliation affiliation = helper.findAffiliation(affiliationId, employee);

        EmployeeMpoAffiliationReviewer reviewer = helper.findReviewer(reviewerId, affiliation);

        affiliation.addReviewer(helper.deleteOne(reviewer));

        employee.addMpoAffiliation(affiliation);

        service.delete(employee, affiliationId, reviewerId);
        return ok(success(EMPLOYEE_AFFILIATION_REVIEWER_DELETE + " with id: " + reviewerId).getJson());
    }
}
