package com.eteacher.service.resource;

import com.eteacher.service.dto.StudentTypeDto;
import com.eteacher.service.entity.commonteacher.StudentType;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.response.EmployeeTypeResponse;
import com.eteacher.service.response.StudentTypeResponse;
import com.eteacher.service.service.StudentTypeService;
import com.eteacher.service.validation.StudentTypeValidator;
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
@Api(tags = "COMMON TEACHER---->Student Type Data")
@RequestMapping(
        "api/v1/student-type"
)
public class StudentTypeResource {

    private final StudentTypeValidator validator;

    private final StudentTypeService service;

    private final String[] sortable = {"id", "studentTypeName"};

    @PostMapping("/save")
    @ApiOperation(value = "save student type", response = StudentTypeResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody StudentTypeDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        StudentType studentType = dto.to();

        service.save(studentType);
        return ok(success(StudentTypeResponse.response(studentType), STUDENT_TYPE_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "Update Student Type", response = StudentTypeResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody StudentTypeDto dto,
                                             BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        StudentType studentType = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);

        dto.update(studentType);

        service.update(studentType);
        return ok(success(StudentTypeResponse.response(studentType), STUDENT_TYPE_UPDATE).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "get student type by id", response = StudentTypeResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        StudentTypeResponse response = service.findById(id)
                .map(StudentTypeResponse::response)
                .orElseThrow(ResourceNotFoundException::new);

        return ok(success(response).getJson());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete student type by id", response = StudentTypeResponse.class)
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        StudentType studentType = service.findById(id).orElseThrow(ResourceNotFoundException::new);

        service.delete(studentType);
        return ok(success(STUDENT_TYPE_DELETE + id).getJson());
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all employee type", response = StudentTypeResponse.class)
    public ResponseEntity<JSONObject> findAll(
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy
    ) {

        List<StudentTypeResponse> responses = service.findAll(sortable, sortBy)
                .stream()
                .map(StudentTypeResponse::response)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }
}
