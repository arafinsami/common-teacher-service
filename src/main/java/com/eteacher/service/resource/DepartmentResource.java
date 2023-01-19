package com.eteacher.service.resource;

import com.eteacher.service.dto.DepartmentDto;
import com.eteacher.service.dto.DepartmentalExaminationDto;
import com.eteacher.service.entity.ntrca.Department;
import com.eteacher.service.entity.ntrca.DepartmentalExamination;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.DepartmentHelper;
import com.eteacher.service.response.DepartmentResponse;
import com.eteacher.service.response.DepartmentalExaminationResponse;
import com.eteacher.service.response.EmployeeTypeResponse;
import com.eteacher.service.service.DepartmentService;
import com.eteacher.service.utils.CommonDataHelper;
import com.eteacher.service.utils.PaginatedResponse;
import com.eteacher.service.validation.DepartmentExamValidator;
import com.eteacher.service.validation.DepartmentValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.constant.NotFoundConstant.DEPARTMENT;
import static com.eteacher.service.enums.RecordStatus.DELETED;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.CollectionUtils.distinct;
import static com.eteacher.service.utils.ResponseBuilder.*;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/department")
@Api(tags = "NTRCA---> Department Data")
public class DepartmentResource {

    private final DepartmentService service;

    private final DepartmentValidator validator;

    private final DepartmentExamValidator departmentExamValidator;

    private final DepartmentHelper helper;

    private final CommonDataHelper commonDataHelper;

    private final String[] sortable = {"id", "departmentName"};

    @PostMapping("/save")
    @ApiOperation(value = "save department", response = DepartmentResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody DepartmentDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Department department = dto.to();

        service.save(department);
        return ok(success(DepartmentResponse.from(department), DEPARTMENT_SAVE).getJson());
    }

    @PostMapping("/exam/save")
    @ApiOperation(value = "save department exam by department Id", response = String.class)
    public ResponseEntity<JSONObject> saveExam(@Valid @RequestBody DepartmentalExaminationDto dto,
                                               BindingResult bindingResult) {

        //ValidationUtils.invokeValidator(departmentExamValidator, request, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Department department = service.findById(dto.getDepartmentId()).orElseThrow(
                () -> new ResourceNotFoundException(DEPARTMENT + dto.getDepartmentId())
        );

        department.addDeptExams(distinct(helper.save(dto.getProfiles())));

        service.saveExam(department);
        return ok(success(DEPARTMENT_EXANM_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update department", response = DepartmentResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody DepartmentDto dto,
                                             BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Department department = service.findById(dto.getId()).orElseThrow(
                () -> new ResourceNotFoundException(DEPARTMENT + dto.getId())
        );

        dto.update(department);

        service.update(department);
        return ok(success(DepartmentResponse.from(department), DEPARTMENT_UPDATE).getJson());
    }

    @PutMapping("/exam/update")
    @ApiOperation(value = "update department exam by department Id", response = String.class)
    public ResponseEntity<JSONObject> updateExam(@Valid @RequestBody DepartmentalExaminationDto dto,
                                                 BindingResult bindingResult) {

        //ValidationUtils.invokeValidator(departmentExamValidator, request, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Department department = service.findById(dto.getDepartmentId()).orElseThrow(
                () -> new ResourceNotFoundException(DEPARTMENT + dto.getDepartmentId())
        );

        department.addDeptExams(distinct(helper.update(dto.getProfiles())));

        service.updateExam(department);
        return ok(success(DEPARTMENT_EXAM_UPDATE).getJson());
    }

    @GetMapping("/find/{departmentId}")
    @ApiOperation(value = "get department by Id", response = DepartmentResponse.class)
    public ResponseEntity<JSONObject> findDepartmentById(@PathVariable Long departmentId) {

        DepartmentResponse response = service.findById(departmentId)
                .map(DepartmentResponse::from)
                .orElseThrow(
                        () -> new ResourceNotFoundException(DEPARTMENT + departmentId)
                );

        return ok(success(response).getJson());
    }

    @GetMapping("/find/exam/{departmentId}")
    @ApiOperation(value = "get examinations by department Id",
            response = DepartmentalExaminationResponse.class
    )
    public ResponseEntity<JSONObject> findAllExamByDepartmentId(@Valid @PathVariable @NotNull @Min(1) Long departmentId) {

        Department department = service.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException(DEPARTMENT + departmentId)
        );

        List<DepartmentalExaminationResponse> responses = department.getExaminations()
                .stream()
                .filter(f -> f.getRecordStatus() != DELETED)
                .map(DepartmentalExaminationResponse::response)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }

    @GetMapping("/find/exam/{departmentId}/{examId}")
    @ApiOperation(value = "get single examination by department Id and exam Id",
            response = DepartmentalExaminationResponse.class
    )
    public ResponseEntity<JSONObject> findExamByDepartmentIdAndExamId(@Valid @PathVariable @NotNull @Min(1) Long departmentId,
                                                                      @Valid @PathVariable @NotNull @Min(1) Long examId) {

        Department department = service.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException(DEPARTMENT + departmentId)
        );

        DepartmentalExamination examination = helper.findExam(examId, department);

        return ok(success(DepartmentalExaminationResponse.response(examination)).getJson());
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete department", response = String.class)
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        Department department = service.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(DEPARTMENT + id)
        );

        service.delete(department);
        return ok(success(DEPARTMENT_DELETE + id).getJson());
    }

    @DeleteMapping("/delete/exam/{departmentId}/{examId}")
    @ApiOperation(value = "delete single department exam by department Id and exam Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteExam(@Valid @PathVariable @NotNull @Min(1) Long departmentId,
                                                 @Valid @PathVariable @NotNull @Min(1) Long examId) {

        Department department = service.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException(DEPARTMENT + departmentId)
        );

        DepartmentalExamination examination = helper.findExam(examId, department);

        department.addDeptExams(helper.delete(examination));

        service.deleteExam(department);
        return ok(success(DEPARTMENT_EXAM_DELETE + " with id: " + examId).getJson());
    }

    @GetMapping("/list")
    @ApiOperation(value = "Get All student Attendance", response = DepartmentResponse.class)
    public ResponseEntity<JSONObject> getAttendanceList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
            @RequestParam(value = "departmentName", defaultValue = "") String departmentName
    ) {
        Map<String, Object> studentsMap = service.searchDepartment(departmentName, page, size, sortBy);

        PaginatedResponse response = new PaginatedResponse();

        List<Department> responses = (List<Department>) studentsMap.get("lists");

        List<DepartmentResponse> customResponses = responses.stream()
                .map(DepartmentResponse::from)
                .collect(Collectors.toList());
        commonDataHelper.getCommonData(page, size, studentsMap, response, customResponses);

        return ok(paginatedSuccess(response).getJson());
    }

    @GetMapping("/all")
    @ApiOperation(value = "get all department", response = DepartmentResponse.class)
    public ResponseEntity<JSONObject> findAll(
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy
    ) {

        List<DepartmentResponse> responses = service.findAll(sortable, sortBy)
                .stream()
                .map(DepartmentResponse::from)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }
}
