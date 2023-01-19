package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeePublicationDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.ntrca.EmployeePublication;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeePublicationHelper;
import com.eteacher.service.response.EmployeePublicationResponse;
import com.eteacher.service.service.EmployeePublicationService;
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
@Api(tags = "NTRCA---> Employee Publication's Data")
@RequestMapping(
        "api/v1/publication"
)
public class EmployeePublicationResource {

    private final EmployeePublicationService service;

    private final EmployeePublicationHelper helper;

    private final EmployeeService employeeService;

    @PostMapping("/save")
    @ApiOperation(value = "save employee publication", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeePublicationDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addPublications(distinct(helper.save(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_PUBLICATION_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee publication", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeePublicationDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addPublications(distinct(helper.update(dto.getProfiles())));

        service.update(employee);
        return ok(success(EMPLOYEE_PUBLICATION_UPDATE).getJson());
    }

    @GetMapping("/find/publication/{employeeId}")
    @ApiOperation(value = "get employee publication lists by employee id", response = EmployeePublicationResponse.class)
    public ResponseEntity<JSONObject> findAllPublicationByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeePublication> publications = employee.getPublications()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeePublicationResponse> response = publications
                .stream()
                .map(EmployeePublicationResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/publication/{employeeId}/{publicationId}")
    @ApiOperation(value = "get single employee publication by employee Id and publication Id",
            response = EmployeePublicationResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeAndPublicationId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                        @Valid @PathVariable @NotNull @Min(1) Long publicationId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeePublication publication = helper.findPublication(publicationId, employee);

        return ok(success(EmployeePublicationResponse.response(publication)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{publicationId}")
    @ApiOperation(value = "delete single employee publication by employee Id and publication Id",
            response = String.class)
    public ResponseEntity<JSONObject> deleteSingle(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                   @Valid @PathVariable @NotNull @Min(1) Long publicationId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeePublication publication = helper.findPublication(publicationId, employee);

        employee.addPublications(helper.delete(publication));

        service.delete(employee);
        return ok(success(EMPLOYEE_PUBLICATION_DELETE + " with id: " + publicationId).getJson());
    }
}
