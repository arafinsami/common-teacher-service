package com.eteacher.service.resource;

//import com.eteacher.service.client.EiinRestClient;

import com.eteacher.service.dto.EmployeeTypeDto;
import com.eteacher.service.entity.commonteacher.EmployeeType;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.response.EmployeeTypeResponse;
import com.eteacher.service.service.EmployeeTypeService;
import com.eteacher.service.validation.EmployeeTypeValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Employee Type Data")
@RequestMapping(
        "api/v1/employee-type"
)
public class EmployeeTypeResource {

//  @Autowired
//  private final EiinRestClient restClient;

    private final EmployeeTypeValidator validator;

    private final EmployeeTypeService service;

    private final String[] sortable = {"id", "employeeTypeName"};

    @PostMapping("/save")
    @ApiOperation(value = "save employee type", response = EmployeeTypeResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeTypeDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        EmployeeType employeeType = dto.to();

        service.save(employeeType);
        return ok(success(EmployeeTypeResponse.from(employeeType), EMPLOYEE_TYPE_SAVE).getJson());
    }


    @PutMapping("/update")
    @ApiOperation(value = "update employee type", response = EmployeeTypeResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeTypeDto dto,
                                             BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        EmployeeType employeeType = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);

        dto.update(employeeType);

        service.update(employeeType);
        return ok(success(EmployeeTypeResponse.from(employeeType), EMPLOYEE_TYPE_UPDATE).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "get employee type by id", response = EmployeeTypeResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        EmployeeTypeResponse response = service.findById(id)
                .map(EmployeeTypeResponse::from)
                .orElseThrow(ResourceNotFoundException::new);

        return ok(success(response).getJson());
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all employee type", response = EmployeeTypeResponse.class)
    public ResponseEntity<JSONObject> findAll(
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy
    ) {

        List<EmployeeTypeResponse> responses = service.findAll(sortable, sortBy)
                .stream()
                .map(EmployeeTypeResponse::from)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete employee type by id", response = EmployeeTypeResponse.class)
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        EmployeeType employeeType = service.findById(id).orElseThrow(ResourceNotFoundException::new);

        service.delete(employeeType);
        return ok(success(EMPLOYEE_TYPE_DELETE + id).getJson());
    }
}
