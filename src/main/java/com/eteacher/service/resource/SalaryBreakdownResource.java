package com.eteacher.service.resource;

import com.eteacher.service.dto.SalaryBreakdownDto;
import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.response.SalaryBreakdownResponse;
import com.eteacher.service.service.SalaryBreakdownService;
import com.eteacher.service.validation.SalaryBreakdownValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Salary breakdown information")
@RequestMapping(
        "api/v1/salary-breakdown"
)
public class SalaryBreakdownResource {

    private final SalaryBreakdownValidator validator;

    private final SalaryBreakdownService service;

    @PostMapping("/save")
    @ApiOperation(value = "save salary breakdown", response = SalaryBreakdownResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody SalaryBreakdownDto request,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, request, bindingResult);

        if (bindingResult.hasErrors()) {

            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        SalaryBreakdown salaryBreakdown = request.to();

        service.save(salaryBreakdown);
        return ok(success(SalaryBreakdownResponse.response(salaryBreakdown), SALARY_BREAKDOWN_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update salary breakdown", response = SalaryBreakdownResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody SalaryBreakdownDto request, BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, request, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        SalaryBreakdown salaryBreakdown = service.findById(request.getId()).orElseThrow(ResourceNotFoundException::new);

        request.update(salaryBreakdown);

        service.update(salaryBreakdown);
        return ok(success(SalaryBreakdownResponse.response(salaryBreakdown), SALARY_BREAKDOWN_UPDATE).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "find salary breakdown by id", response = SalaryBreakdownResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        SalaryBreakdownResponse response = service.findById(id)
                .map(SalaryBreakdownResponse::response)
                .orElseThrow(ResourceNotFoundException::new);

        return ok(success(response).getJson());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete salary breakdown", response = String.class)
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        SalaryBreakdown salaryBreakdown = service.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        service.delete(salaryBreakdown);
        return ok(success(SALARY_BREAKDOWN_DELETE + id).getJson());
    }

}
