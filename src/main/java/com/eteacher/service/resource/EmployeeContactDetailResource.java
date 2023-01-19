package com.eteacher.service.resource;

import com.eteacher.service.dto.EmployeeContactDetailDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.EmployeeContactDetail;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeContactDetailHelper;
import com.eteacher.service.response.EmployeeContactDetailResponse;
import com.eteacher.service.service.EmployeeContactDetailService;
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
import static com.eteacher.service.enums.RecordStatus.DELETED;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.CollectionUtils.distinct;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Employee Contact Detail Data")
@RequestMapping(
        "api/v1/employee-contact-detail"
)
public class EmployeeContactDetailResource {

    private final EmployeeService employeeService;

    private final EmployeeContactDetailService service;

    private final EmployeeContactDetailHelper helper;

    @PostMapping("/save")
    @ApiOperation(value = "save employee contact detail", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeContactDetailDto dto,
                                           BindingResult bindingResult) {

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        employee.addContacts(distinct(helper.save(dto.getContactDetails())));

        service.save(employee);
        return ok(success(EMPLOYEE_CONTACT_DETAIL_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee contact detail", response = String.class)
    public ResponseEntity<JSONObject> updateContact(@Valid @RequestBody EmployeeContactDetailDto dto,
                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployee()).orElseThrow(ResourceNotFoundException::new);

        employee.addContacts(distinct(helper.update(dto.getContactDetails())));

        service.update(employee);
        return ok(success(EMPLOYEE_CONTACT_DETAIL_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee contact detail list by employee Id",
            response = EmployeeContactDetailResponse.class
    )
    public ResponseEntity<JSONObject> findAllContactByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        List<EmployeeContactDetail> contacts = employee.getContacts()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeContactDetailResponse> response = contacts
                .stream()
                .map(EmployeeContactDetailResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{contactId}")
    @ApiOperation(
            value = "get single employee contact detail by employee Id and contact Id",
            response = EmployeeContactDetailResponse.class
    )
    public ResponseEntity<JSONObject> findOneByEmployeeIdAndContactId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                      @Valid @PathVariable @NotNull @Min(1) Long contactId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeContactDetail contact = helper.findByContactId(contactId, employee.getContacts());

        return ok(success(EmployeeContactDetailResponse.response(contact)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{contactId}")
    @ApiOperation(
            value = "delete employee contact detail by employee Id and contact Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                             @Valid @PathVariable @NotNull @Min(1) Long contactId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        EmployeeContactDetail contact = helper.findByContactId(contactId, employee.getContacts());

        employee.addContacts(helper.deleteOne(contact));

        service.delete(employee);
        return ok(success(EMPLOYEE_CONTACT_DETAIL_DELETE + " " + contactId).getJson());
    }
}
