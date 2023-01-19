package com.eteacher.service.resource;


import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestionAnswerOption;
import com.eteacher.service.helper.PerformanceEvaluationAssessmentQuestionOptionHelper;
import com.eteacher.service.dto.PerformanceEvaluationAssessmentQuestionAnswerOptionDto;
import com.eteacher.service.response.PerformanceEvaluationAssessmentQuestionAnswerOptionResponse;
import com.eteacher.service.service.PerformanceEvaluationAssessmentQuestionOptionService;
import com.eteacher.service.utils.CommonDataHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.eteacher.service.constant.MessageConstants.PERFORM_EVAL_ASSESSMENT_QUESTION_OPTION_SAVE;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/performance-evaluation-assessment-question-option")
@Api(tags = "Performance Evaluation Assessment Question Answer Option Data")
public class PerformanceEvaluationAssessmentQuestionAnswerOptionResource {

    private final PerformanceEvaluationAssessmentQuestionOptionService service;

    private final PerformanceEvaluationAssessmentQuestionOptionHelper helper;

    private final CommonDataHelper commonDataHelper;

    @PostMapping("/save")
    @ApiOperation(value = "save Performance Evaluation Assessment Question Answer Option ", response = PerformanceEvaluationAssessmentQuestionAnswerOptionResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody PerformanceEvaluationAssessmentQuestionAnswerOptionDto request, BindingResult bindingResult) {

//        ValidationUtils.invokeValidator(validator, request, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }
        PerformanceEvaluationAssessmentQuestionAnswerOption answerOption = request.to();
        service.save(answerOption, request);
        return ok(success(PerformanceEvaluationAssessmentQuestionAnswerOptionResponse.from(answerOption), PERFORM_EVAL_ASSESSMENT_QUESTION_OPTION_SAVE).getJson());

    }

}
