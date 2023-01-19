package com.eteacher.service.resource;

import com.eteacher.service.dto.JobCircularDto;
import com.eteacher.service.entity.job.JobCircular;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.response.JobCircularResponse;
import com.eteacher.service.service.JobCircularService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.constant.NotFoundConstant.JOB_CIRCULAR;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "JOB--->Job circular's Data")
@RequestMapping(
        "api/v1/job-circular"
)
public class JobCircularResource {

    private final JobCircularService service;

    @PostMapping("/save")
    @ApiOperation(value = "save job circular", response = JobCircularResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody JobCircularDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        JobCircular circular = dto.to();

        service.save(circular);
        return ok(success(JobCircularResponse.response(circular), JOB_CIRCULAR_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update job circular", response = JobCircularResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody JobCircularDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        JobCircular circular = service.findById(dto.getId()).orElseThrow(
                () -> new ResourceNotFoundException(JOB_CIRCULAR + dto.getId())
        );

        dto.update(circular);

        service.update(circular);
        return ok(success(JobCircularResponse.response(circular), JOB_CIRCULAR_UPDATE).getJson());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete job circular by id", response = String.class)
    public ResponseEntity<JSONObject> update(@PathVariable Long id) {

        JobCircular circular = service.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(JOB_CIRCULAR + id)
        );

        service.delete(circular);
        return ok(success(JOB_CIRCULAR_DELETE).getJson());
    }
}
