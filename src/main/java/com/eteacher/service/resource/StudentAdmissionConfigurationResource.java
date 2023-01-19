package com.eteacher.service.resource;

import com.eteacher.service.dto.StudentAdmissionConfigurationDto;
import com.eteacher.service.entity.commonteacher.StudentAdmissionConfiguration;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.response.StudentAdmissionConfigurationResponse;
import com.eteacher.service.service.StudentAdmissionConfigurationService;
import com.eteacher.service.validation.StudentAdmissionConfigurationValidator;
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
@Api(tags = "COMMON TEACHER---->Student Admission Configuration Data")
@RequestMapping(
        "api/v1/student-admission-configuration"
)
public class StudentAdmissionConfigurationResource {

    private final StudentAdmissionConfigurationValidator validator;

    private final StudentAdmissionConfigurationService service;

    @PostMapping("/save")
    @ApiOperation(
            value = "save student admission configuration",
            response = StudentAdmissionConfigurationResponse.class
    )
    public ResponseEntity<JSONObject> save(@Valid @RequestBody StudentAdmissionConfigurationDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        StudentAdmissionConfiguration admissionConfiguration = dto.to();

        service.save(admissionConfiguration);
        return ok(success(StudentAdmissionConfigurationResponse.response(admissionConfiguration),
                STUDENT_ADMISSION_CONFIGURAION_SAVE).getJson()
        );
    }

    @PutMapping("/update")
    @ApiOperation(
            value = "update student admission configuration",
            response = StudentAdmissionConfigurationResponse.class
    )
    public ResponseEntity<JSONObject> update(@Valid @RequestBody StudentAdmissionConfigurationDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        StudentAdmissionConfiguration admissionConfiguration = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);

        dto.update(admissionConfiguration);

        service.update(admissionConfiguration);
        return ok(success(StudentAdmissionConfigurationResponse.response(admissionConfiguration),
                STUDENT_ADMISSION_CONFIGURAION_UPDATE).getJson()
        );
    }

    @GetMapping("/find/{id}")
    @ApiOperation(
            value = "get student admission configuration by id",
            response = StudentAdmissionConfigurationResponse.class
    )
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        StudentAdmissionConfigurationResponse response = service.findById(id)
                .map(StudentAdmissionConfigurationResponse::response)
                .orElseThrow(ResourceNotFoundException::new);

        return ok(success(response).getJson());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(
            value = "delete student admission configuration by id",
            response = String.class
    )
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        StudentAdmissionConfiguration studentAdmissionConfiguration = service
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        service.delete(studentAdmissionConfiguration);
        return ok(success(STUDENT_ADMISSION_CONFIGURAION_DELETE + id).getJson());
    }
}
