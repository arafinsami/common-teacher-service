package com.eteacher.service.resource;

import com.eteacher.service.dto.SalaryScaleDto;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.response.SalaryScaleResponse;
import com.eteacher.service.response.StudentTypeResponse;
import com.eteacher.service.service.SalaryScaleService;
import com.eteacher.service.validation.SalaryScaleValidator;
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
@Api(tags = "COMMON TEACHER---->Salary Scale Data")
@RequestMapping(
        "api/v1/salary-scale"
)
public class SalaryScaleResource {

    private final SalaryScaleValidator validator;

    private final SalaryScaleService service;

    private final String[] sortable = {"id", "studentTypeName"};

    @PostMapping("/save")
    @ApiOperation(value = "save salary scale", response = SalaryScaleResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody SalaryScaleDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        SalaryScale salaryScale = dto.to();

        service.save(salaryScale);
        return ok(success(SalaryScaleResponse.response(salaryScale), SALARY_SCALE_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update salary scale", response = SalaryScaleResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody SalaryScaleDto dto,
                                             BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        SalaryScale salaryScale = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);

        dto.update(salaryScale);

        service.update(salaryScale);
        return ok(success(SalaryScaleResponse.response(salaryScale), SALARY_SCALE_UPDATE).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "get salary scale by id", response = SalaryScaleResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        SalaryScaleResponse response = service.findById(id)
                .map(SalaryScaleResponse::response)
                .orElseThrow(ResourceNotFoundException::new);

        return ok(success(response).getJson());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete salary scale by id", response = SalaryScaleResponse.class)
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        SalaryScale salaryScale = service.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        service.delete(salaryScale);
        return ok(success(SALARY_SCALE_DELETE + id).getJson());
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all employee type", response = SalaryScaleResponse.class)
    public ResponseEntity<JSONObject> findAll(
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy
    ) {

        List<SalaryScaleResponse> responses = service.findAll(sortable, sortBy)
                .stream()
                .map(SalaryScaleResponse::response)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }
}
