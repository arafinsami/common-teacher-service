package com.eteacher.service.resource;

import com.eteacher.service.entity.commonteacher.PerformanceEvaluation;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.dto.PerformanceEvaluationDto;
import com.eteacher.service.response.PerformanceEvaluationResponse;
import com.eteacher.service.service.PerformanceEvaluationService;
import com.eteacher.service.utils.CommonDataHelper;
import com.eteacher.service.utils.PaginatedResponse;
import com.eteacher.service.validation.PerformanceEvaluationValidator;
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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.ResponseBuilder.*;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Performance Evaluation Data")
@RequestMapping(
        "api/v1/performance-evaluation"
)
public class PerformanceEvaluationResource {

    private final PerformanceEvaluationValidator validator;

    private final PerformanceEvaluationService service;

    private final CommonDataHelper helper;

    private final String[] sortable = {"id", "performanceEvaluationDescription", "performanceEvaluationDescriptionBn", "performanceEvaluationYear"};

    private final String[] searchable = {"performanceEvaluationDescription", "performanceEvaluationDescriptionBn", "performanceEvaluationYear"};


    @GetMapping("/list")
    @ApiOperation(value = "Get Performance Evaluation", response = PerformanceEvaluationResponse.class)
    public ResponseEntity<JSONObject> getList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "performanceEvaluationDescription", defaultValue = "") String performanceEvaluationDescription,
            @RequestParam(value = "performanceEvaluationDescriptionBn", defaultValue = "") String performanceEvaluationDescriptionBn,
            @RequestParam(value = "performanceEvaluationYear", defaultValue = "") Integer performanceEvaluationYear) {


        helper.setPageSize(page, size);

        PaginatedResponse response = new PaginatedResponse();

        Map<String, Object> map = service.getList(
                sortable, searchable, sortBy, search, page, size, PerformanceEvaluationResponse.searchPerformanceEvaluation(
                        performanceEvaluationDescription,
                        performanceEvaluationDescriptionBn
                )
        );

        List<PerformanceEvaluation> performance = (List<PerformanceEvaluation>) map.get("lists");

        List<PerformanceEvaluationResponse> responses = performance.stream()
                .map(PerformanceEvaluationResponse::from)
                .collect(Collectors.toList());

        helper.getCommonData(page, size, map, response, responses);
        return ok(paginatedSuccess(response).getJson());
    }


    @PostMapping("/save")
    @ApiOperation(value = "Save Performance Evaluation", response = PerformanceEvaluationResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody PerformanceEvaluationDto request,
                                           BindingResult bindingResult) {


        ValidationUtils.invokeValidator(validator, request, bindingResult);
        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        PerformanceEvaluation evaluation = request.to(request);

        service.save(evaluation);
        return ok(success(PerformanceEvaluationResponse.from(evaluation), PERFORM_EVAL_SAVE).getJson());
    }


    @GetMapping("/all")
    @ApiOperation(value = "Get all Performance Evaluation", response = PerformanceEvaluationResponse.class)
    public ResponseEntity<JSONObject> findAll(
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy) {

        List<PerformanceEvaluationResponse> responses = service.findAll(sortable, sortBy)
                .stream().map(PerformanceEvaluationResponse::from)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "Get Performance Evaluation by id", response = PerformanceEvaluationResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        Optional<PerformanceEvaluationResponse> response = Optional.ofNullable(service.findById(id)
                .map(PerformanceEvaluationResponse::from)
                .orElseThrow(ResourceNotFoundException::new));

        return ok(success(response).getJson());
    }


    @PutMapping("/update")
    @ApiOperation(value = "Update Performance Evaluation", response = PerformanceEvaluationResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody PerformanceEvaluationDto request,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        PerformanceEvaluation performance = service.findById(request.getId())
                .orElseThrow(ResourceNotFoundException::new);

        request.update(request, performance);

        service.update(performance);
        return ok(success(PerformanceEvaluationResponse.from(performance), EMPLOYEE_TYPE_UPDATE).getJson());
    }

//    @DeleteMapping("/delete/{id}")
//    @ApiOperation(value = "Delete Performance Evaluation by id")
//    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {
//
//        PerformanceEvaluation perform = service.findById(id)
//                .orElseThrow(ResourceNotFoundException::new);
//
//        service.delete(perform);
//
//        return ok(success(perform, PERFORM_EVAL_DELETE).getJson());
//    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Delete Performance Evaluation by id")
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        PerformanceEvaluation performance = service.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        service.delete(performance);
        return ok(success(null, PERFORM_EVAL_DELETE).getJson());
    }


}
