package com.eteacher.service.resource;

import com.eteacher.service.client.CommonRestClient;
import com.eteacher.service.dto.EmployeeDto;
import com.eteacher.service.dto.EmployeeEncloserDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeEncloserHelper;
import com.eteacher.service.helper.GetStringToJsonHelper;
import com.eteacher.service.response.DesignationResponse;
import com.eteacher.service.response.EmployeeResponse;
import com.eteacher.service.response.SubjectResponse;
import com.eteacher.service.service.EmployeeEncloserService;
import com.eteacher.service.service.EmployeeService;
import com.eteacher.service.utils.CommonDataHelper;
import com.eteacher.service.utils.PaginatedResponse;
import com.eteacher.service.validation.EmployeeValidator;
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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.ResponseBuilder.*;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Employee  Data")
@RequestMapping(
        "api/v1/employee"
)
public class EmployeeResource {

    private final EmployeeValidator validator;

    private final EmployeeService service;

    private final EmployeeEncloserHelper helper;

    private final EmployeeEncloserService employeeEncloserService;

    private final CommonDataHelper commonHelper;

    private final String[] sortable = {"id", "employeeName", "employeeCode", "indexNo"};

    private final CommonRestClient commonRestClient;

    @PostMapping("/save")
    @ApiOperation(value = "save employee", response = EmployeeResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeDto dto,
                                           BindingResult bindingResult) {

//        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = dto.to();

        service.save(employee, dto);
        return ok(success(EmployeeResponse.response(employee), EMPLOYEE_SAVE).getJson());
    }

    @PostMapping("/encloser/save")
    @ApiOperation(
            value = "save employee encloser",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveEncloser(@RequestPart(value = "file", required = false) MultipartFile file,
                                                   @Valid @RequestPart(value = "dto", required = false) EmployeeEncloserDto dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        employeeEncloserService.save(helper.save(file, dto));
        return ok(success(EMPLOYEE_ENCLOSER_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update employee", response = EmployeeResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeDto dto,
                                             BindingResult bindingResult) {

        //ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);

        dto.update(employee);

        service.update(dto, employee);
        return ok(success(EmployeeResponse.response(employee), EMPLOYEE_UPDATE).getJson());
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all employee type", response = EmployeeResponse.class)
    public ResponseEntity<JSONObject> findAll(
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy
    ) {
        List<EmployeeResponse> responses = service.findAll(sortable, sortBy)
                .stream()
                .map(employee -> {
                    if (Objects.nonNull(employee.getSubjectId()) && Objects.nonNull(employee.getDesignationId())) {
                        SubjectResponse subjectResponse = new GetStringToJsonHelper<>(SubjectResponse.class)
                                .parse(commonRestClient.getSubjectById(employee.getSubjectId()));
                        DesignationResponse designation = new GetStringToJsonHelper<>(DesignationResponse.class)
                                .parse(commonRestClient.getDesignationById(employee.getDesignationId()));

                        return EmployeeResponse.response(employee, subjectResponse.getSubjectName(), designation.getDesignationName());
                    }
                    return EmployeeResponse.response(employee);

                })
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(value = "get employee by id", response = EmployeeResponse.class)
    public ResponseEntity<JSONObject> findById(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        EmployeeResponse response = service.findById(employeeId)
                .map(EmployeeResponse::response)
                .orElseThrow(ResourceNotFoundException::new);
        String country = commonRestClient.getSubjectById(1L);
        return ok(success(response).getJson());
    }

    @DeleteMapping("/delete/{employeeId}")
    @ApiOperation(value = "delete employee by id", response = EmployeeResponse.class)
    public ResponseEntity<JSONObject> delete(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = service.findById(employeeId).orElseThrow(ResourceNotFoundException::new);

        service.delete(employee);
        return ok(success(EMPLOYEE_DELETE + employeeId).getJson());
    }

    @GetMapping("/list")
    @ApiOperation(value = "get employee list", response = EmployeeResponse.class)
    public ResponseEntity<JSONObject> getList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "instituteId", defaultValue = "") Long institute,
            @RequestParam(value = "departmentId", defaultValue = "") Long department
    ) {

        PaginatedResponse response = new PaginatedResponse();

        Map<String, Object> employeeMap;

        if (!Objects.isNull(institute) && !Objects.isNull(department))
            employeeMap = service.getList(institute, department, page, size, sortBy, search);
        else
            employeeMap = service.getList(institute, page, size, sortBy, search);

        List<Employee> employees = (List<Employee>) employeeMap.get("lists");

        List<EmployeeResponse> responses = employees
                .stream()
                .map(EmployeeResponse::response)
                .collect(Collectors.toList());

        commonHelper.getCommonData(page, size, employeeMap, response, responses);

        return ok(paginatedSuccess(response).getJson());
    }

}
