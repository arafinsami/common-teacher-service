package com.eteacher.service.resource;


import com.eteacher.service.dto.EmployeeAcrEvaluationAssessmentQuestionsAnswerDto;
import com.eteacher.service.dto.EmployeeAcrEvaluationDto;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.govtteacher.EmployeeAcrEvaluation;
import com.eteacher.service.entity.govtteacher.EmployeeAcrEvaluationAssessmentQuestionsAnswer;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.EmployeeAcrEvaluationHelper;
import com.eteacher.service.response.EmployeeAcrEvaluationAssessmentQuestionsAnswerResponse;
import com.eteacher.service.response.EmployeeAcrEvaluationResponse;
import com.eteacher.service.service.EmployeeArcEvaluationService;
import com.eteacher.service.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.constant.NotFoundConstant.EMPLOYEE;
import static com.eteacher.service.enums.RecordStatus.DELETED;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.CollectionUtils.distinct;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "GOV TEACHER--->Employee ACR Evaluation's Data")
@RequestMapping(
        "api/v1/employee-acr-evaluation"
)
public class EmployeeAcrEvaluationResource {

    private final EmployeeArcEvaluationService service;

    private final EmployeeAcrEvaluationHelper helper;

    private final EmployeeService employeeService;

    @PostMapping("/save")
    @ApiOperation(value = "save acr evaluation by employee Id", response = String.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody EmployeeAcrEvaluationDto dto,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addAcrEvaluations(distinct(helper.saveEvaluation(dto.getProfiles())));

        service.save(employee);
        return ok(success(EMPLOYEE_ACR_EVALUATION_SAVE).getJson());
    }

    @PostMapping("/save/answer")
    @ApiOperation(
            value = "save employee acr evaluation answer with acr evaluation Id and employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> saveAnswer(@Valid @RequestBody EmployeeAcrEvaluationAssessmentQuestionsAnswerDto dto,
                                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeAcrEvaluation evaluation = helper.findEvaluation(dto.getEvaluationId(), employee);

        evaluation.addAnswer(helper.saveAnswer(dto.getAnswer()));

        employee.addAcrEvaluation(evaluation);

        service.saveAnswer(employee, evaluation);
        return ok(success(EMPLOYEE_ACR_EVALUATION_ASSESMENT_ANSWER_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update acr evaluation by employee Id", response = String.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody EmployeeAcrEvaluationDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        employee.addAcrEvaluations(distinct(helper.updateEvaluation(dto.getProfiles())));

        service.update(employee);
        return ok(success(EMPLOYEE_ACR_EVALUATION_UPDATE).getJson());
    }

    @PutMapping("/update/answer")
    @ApiOperation(
            value = "update employee acr evaluation answer with acr evaluation Id and employee Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> updateAnswer(@Valid @RequestBody EmployeeAcrEvaluationAssessmentQuestionsAnswerDto dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        Employee employee = employeeService.findById(dto.getEmployeeId()).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + dto.getEmployeeId())
        );

        EmployeeAcrEvaluation evaluation = helper.findEvaluation(dto.getEvaluationId(), employee);

        evaluation.addAnswer(helper.updateAnswer(dto.getAnswer()));

        employee.addAcrEvaluation(evaluation);

        service.updateAnswer(employee, evaluation);
        return ok(success(EMPLOYEE_ACR_EVALUATION_ASSESMENT_ANSWER_UPDATE).getJson());
    }

    @GetMapping("/find/{employeeId}")
    @ApiOperation(
            value = "get employee acr evaluation lists by employee id",
            response = EmployeeAcrEvaluationResponse.class
    )
    public ResponseEntity<JSONObject> findAllEvaluationByEmployeeId(@Valid @PathVariable @NotNull @Min(1) Long employeeId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        List<EmployeeAcrEvaluation> evaluations = employee.getAcrEvaluations()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .collect(Collectors.toList());

        List<EmployeeAcrEvaluationResponse> response = evaluations
                .stream()
                .map(EmployeeAcrEvaluationResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/{employeeId}/{evaluationId}")
    @ApiOperation(
            value = "get single employee acr evaluation by employee Id and evaluation Id",
            response = EmployeeAcrEvaluationResponse.class
    )
    public ResponseEntity<JSONObject> findEvaluationByEmployeeAndEvaluationId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                              @Valid @PathVariable @NotNull @Min(1) Long evaluationId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeAcrEvaluation evaluation = helper.findEvaluation(evaluationId, employee);

        return ok(success(EmployeeAcrEvaluationResponse.response(evaluation)).getJson());
    }

    @GetMapping("/find/answer/{employeeId}/{evaluationId}")
    @ApiOperation(
            value = "get employee acr evaluation answer lists by employee id and evaluation id",
            response = EmployeeAcrEvaluationAssessmentQuestionsAnswerResponse.class
    )
    public ResponseEntity<JSONObject> findAllAnswerByEmployeeIdAndEvaluationId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                               @Valid @PathVariable @NotNull @Min(1) Long evaluationId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeAcrEvaluation evaluation = helper.findEvaluation(evaluationId, employee);

        List<EmployeeAcrEvaluationAssessmentQuestionsAnswerResponse> response = evaluation.getAnswers()
                .stream()
                .filter(f -> f.getRecordStatus() != DELETED)
                .map(EmployeeAcrEvaluationAssessmentQuestionsAnswerResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/answer/{employeeId}/{evaluationId}/{answerId}")
    @ApiOperation(
            value = "get single employee acr evaluation answer with employee Id, evaluation Id and answer Id",
            response = EmployeeAcrEvaluationAssessmentQuestionsAnswerResponse.class
    )
    public ResponseEntity<JSONObject> findAnswerByEmployeeIdAndEvaluationIdAndAnswerId(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                                                       @Valid @PathVariable @NotNull @Min(1) Long evaluationId,
                                                                                       @Valid @PathVariable @NotNull @Min(1) Long answerId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeAcrEvaluation evaluation = helper.findEvaluation(evaluationId, employee);

        EmployeeAcrEvaluationAssessmentQuestionsAnswer answer = helper.findAnswer(answerId, evaluation);

        return ok(success(EmployeeAcrEvaluationAssessmentQuestionsAnswerResponse.response(answer)).getJson());
    }

    @DeleteMapping("/delete/{employeeId}/{evaluationId}")
    @ApiOperation(value = "delete single evaluation by employee Id and employee evaluation Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteEvaluation(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                       @Valid @PathVariable @NotNull @Min(1) Long evaluationId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeAcrEvaluation evaluation = helper.findEvaluation(evaluationId, employee);

        employee.addAcrEvaluations(helper.delete(evaluation));

        service.delete(employee);
        return ok(success(EMPLOYEE_ACR_EVALUATION_DELETE + " with id: " + evaluationId).getJson());
    }

    @DeleteMapping("/delete/answer/{employeeId}/{evaluationId}/{answerId}")
    @ApiOperation(value = "delete answer by employee Id, evaluation Id and answer Id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deleteAnswer(@Valid @PathVariable @NotNull @Min(1) Long employeeId,
                                                   @Valid @PathVariable @NotNull @Min(1) Long evaluationId,
                                                   @Valid @PathVariable @NotNull @Min(1) Long answerId) {

        Employee employee = employeeService.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException(EMPLOYEE + employeeId)
        );

        EmployeeAcrEvaluation evaluation = helper.findEvaluation(evaluationId, employee);

        EmployeeAcrEvaluationAssessmentQuestionsAnswer answer = helper.findAnswer(answerId, evaluation);

        evaluation.addAnswer(helper.deleteAnswer(answer));

        employee.addAcrEvaluation(evaluation);

        service.deleteAnswer(employee, evaluation);
        return ok(success(EMPLOYEE_ACR_EVALUATION_ASSESMENT_ANSWER_DELETE + " with id: " + answerId).getJson());
    }
}
