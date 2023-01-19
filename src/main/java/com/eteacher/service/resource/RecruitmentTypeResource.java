package com.eteacher.service.resource;

//import com.eteacher.service.client.EiinRestClient;

import com.eteacher.service.dto.RecruitmentTypeDto;
import com.eteacher.service.entity.commonteacher.RecruitmentType;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.response.RecruitmentTypeResponse;
import com.eteacher.service.service.RecruitmentTypeService;
import com.eteacher.service.validation.RecruitmentTypeValidator;
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
@Api(tags = "COMMON TEACHER---->Recruitment Type Data")
@RequestMapping(
        "api/v1/recruitment-type"
)
public class RecruitmentTypeResource {

    private final RecruitmentTypeValidator validator;

    private final RecruitmentTypeService service;

    @PostMapping("/save")
    @ApiOperation(value = "save recruitment type", response = RecruitmentTypeResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody RecruitmentTypeDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        RecruitmentType recruitmentType = dto.to();

        service.save(recruitmentType);
        return ok(success(RecruitmentTypeResponse.response(recruitmentType), RECRUITMENT_TYPE_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update recruitment type", response = RecruitmentTypeResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody RecruitmentTypeDto dto,
                                             BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        RecruitmentType recruitmentType = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);

        dto.update(recruitmentType);

        service.update(recruitmentType);
        return ok(success(RecruitmentTypeResponse.response(recruitmentType), RECRUITMENT_TYPE_UPDATE).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "get recruitment type by id", response = RecruitmentTypeResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        RecruitmentTypeResponse response = service.findById(id)
                .map(RecruitmentTypeResponse::response)
                .orElseThrow(ResourceNotFoundException::new);

        return ok(success(response).getJson());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete recruitment type by id", response = String.class)
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        RecruitmentType recruitmentType = service.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        service.delete(recruitmentType);
        return ok(success(RECRUITMENT_TYPE_DELETE + id).getJson());
    }
}
