package com.eteacher.service.resource;

import com.eteacher.service.dto.RoutineDto;
import com.eteacher.service.entity.commonteacher.Routine;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.response.RoutineResponse;
import com.eteacher.service.service.RoutineService;
import com.eteacher.service.utils.CommonDataHelper;
import com.eteacher.service.validation.RoutineValidator;
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
@Api(tags = "COMMON TEACHER---->routine Information Data")
@RequestMapping(
        "api/v1/routine"
)
public class RoutineResource {

    public final RoutineService service;

    public final RoutineValidator validator;

    public final CommonDataHelper helper;

    @PostMapping("/save")
    @ApiOperation(value = "save routine", response = RoutineResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody RoutineDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Routine routine = dto.to();

        service.save(routine);
        return ok(success(RoutineResponse.response(routine), ROUTINE_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update routine", response = RoutineResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody RoutineDto dto,
                                             BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);
        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Routine routine = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);

        dto.update(routine);

        service.update(routine);
        return ok(success(RoutineResponse.response(routine), ROUTINE_UPDATE).getJson());
    }


    @GetMapping("/find/{id}")
    @ApiOperation(value = "get routine by id", response = RoutineResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        RoutineResponse response = service.findById(id)
                .map(RoutineResponse::response)
                .orElseThrow(ResourceNotFoundException::new);

        return ok(success(response).getJson());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete routine by id")
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        Routine routine = service.findById(id).orElseThrow(ResourceNotFoundException::new);

        service.delete(routine);
        return ok(success(ROUTINE_DELETE + id).getJson());
    }
}
