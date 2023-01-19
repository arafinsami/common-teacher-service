package com.eteacher.service.resource;

import com.eteacher.service.dto.PerformanceEvaluationAssessmentQuestionDto;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestion;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.response.PerformanceEvaluationAssessmentQuestionResponse;
import com.eteacher.service.service.PerformanceEvaluationAssessmentQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.eteacher.service.constant.MessageConstants.BANK_UPDATE;
import static com.eteacher.service.constant.MessageConstants.PERFORM_EVAL_ASSESSMENT_QUESTION_SAVE;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequiredArgsConstructor

@Api(tags = "COMMON TEACHER---->Performance Evaluation Assessment Question Data")
@RequestMapping(
        "api/v1/performance-evaluation-assessment-question"
)
public class PerformanceEvaluationAssessmentQuestionResource {

    private final PerformanceEvaluationAssessmentQuestionService service;

    @PostMapping("/save")
    @ApiOperation(
            value = "Save Performance Evaluation Assessment Question",
            response = PerformanceEvaluationAssessmentQuestionResponse.class
    )
    public ResponseEntity<JSONObject> save(@Valid @RequestBody PerformanceEvaluationAssessmentQuestionDto request,
                                           BindingResult bindingResult) {

//        ValidationUtils.invokeValidator(validator, request, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        PerformanceEvaluationAssessmentQuestion question = request.to();

        service.save(question, request);
        return ok(success(PerformanceEvaluationAssessmentQuestionResponse.from(question), PERFORM_EVAL_ASSESSMENT_QUESTION_SAVE).getJson());

    }

    @PutMapping("/update")
    @ApiOperation(
            value = "Update Performance Evaluation Assessment Question",
            response = PerformanceEvaluationAssessmentQuestionResponse.class
    )
    public ResponseEntity<JSONObject> update(@Valid @RequestBody PerformanceEvaluationAssessmentQuestionDto request,
                                             BindingResult bindingResult) {

        //ValidationUtils.invokeValidator(validator, request, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        PerformanceEvaluationAssessmentQuestion question = service.findById(request.getId()).orElseThrow(ResourceNotFoundException::new);

        request.update(question);
        service.update(question, request);
        return ok(success(PerformanceEvaluationAssessmentQuestionResponse.from(question), BANK_UPDATE).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "Get Evaluation Assessment Question By Id", response = PerformanceEvaluationAssessmentQuestionResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        PerformanceEvaluationAssessmentQuestionResponse response = service.findById(id)
                .map(PerformanceEvaluationAssessmentQuestionResponse::from)
                .orElseThrow(ResourceNotFoundException::new);
        return ok(success(response).getJson());
    }

}
