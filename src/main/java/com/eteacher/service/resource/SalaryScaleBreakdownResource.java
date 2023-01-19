package com.eteacher.service.resource;

import com.eteacher.service.dto.SalaryScaleBreakdownDto;
import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.entity.commonteacher.SalaryScaleBreakdown;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.SalaryScaleBreakdownHelper;
import com.eteacher.service.response.SalaryScaleBreakdownResponse;
import com.eteacher.service.service.SalaryScaleBreakdownService;
import com.eteacher.service.validation.SalaryScaleBreakdownValidator;
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
@Api(tags = "COMMON TEACHER---->salary scale breakdown's data")
@RequestMapping(
        "api/v1/salary-scale-breakdown"
)
public class SalaryScaleBreakdownResource {

    private final SalaryScaleBreakdownValidator validator;

    private final SalaryScaleBreakdownService service;

    private final SalaryScaleBreakdownHelper helper;

    @PostMapping("/save")
    @ApiOperation(value = "save salary scale breakdown", response = SalaryScaleBreakdownResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody SalaryScaleBreakdownDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        SalaryScaleBreakdown salaryScaleBreakdown = dto.to();

        SalaryScaleBreakdown savedData = service.save(dto, salaryScaleBreakdown);
        return ok(success(SalaryScaleBreakdownResponse.from(savedData), SALARY_SCALE_BREAKDOWN_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update salary scale breakdown", response = SalaryScaleBreakdownResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody SalaryScaleBreakdownDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        SalaryScale salaryScale = helper.getSalaryScale(dto.getSalaryScaleId());

        SalaryBreakdown salaryBreakdown = helper.getSalaryBreakdown(dto.getSalaryBreakDownId());

        SalaryScaleBreakdown salaryScaleBreakdown = service
                .findBySalaryBreakdownAndSalaryScaleAndRecordStatusNot(salaryBreakdown, salaryScale)
                .orElseThrow(ResourceNotFoundException::new);

        dto.update(salaryScaleBreakdown);

        service.update(salaryScaleBreakdown);
        return ok(success(SalaryScaleBreakdownResponse.from(salaryScaleBreakdown), SALARY_SCALE_BREAKDOWN_UPDATE).getJson());
    }

    @GetMapping("/find/{breakdown}/{scale}")
    @ApiOperation(value = "Get Salary Scale Breakdown by id", response = SalaryScaleBreakdownResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long breakdown, @PathVariable Long scale) {

        SalaryScale salaryScale = helper.getSalaryScale(scale);

        SalaryBreakdown salaryBreakdown = helper.getSalaryBreakdown(breakdown);

        SalaryScaleBreakdownResponse response = service
                .findBySalaryBreakdownAndSalaryScaleAndRecordStatusNot(salaryBreakdown, salaryScale)
                .map(SalaryScaleBreakdownResponse::from)
                .orElseThrow(ResourceNotFoundException::new);

        return ok(success(response).getJson());
    }

    @DeleteMapping("/delete/{breakdown}/{scale}")
    @ApiOperation(value = "delete salary scale breakdown by id")
    public ResponseEntity<JSONObject> delete(@PathVariable Long breakdown, @PathVariable Long scale) {

        SalaryScale salaryScale = helper.getSalaryScale(scale);

        SalaryBreakdown salaryBreakdown = helper.getSalaryBreakdown(breakdown);

        SalaryScaleBreakdown salaryScaleBreakdown = service
                .findBySalaryBreakdownAndSalaryScaleAndRecordStatusNot(salaryBreakdown, salaryScale)
                .orElseThrow(ResourceNotFoundException::new);

        service.delete(salaryScaleBreakdown);
        return ok(success(null, SALARY_SCALE_BREAKDOWN_DELETE).getJson());
    }
}
