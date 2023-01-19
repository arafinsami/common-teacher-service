package com.eteacher.service.resource;

import com.eteacher.service.dto.PerformanceEvaluationAssessmentTopicDto;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.response.PerformanceEvaluationAssessmentTopicResponse;
import com.eteacher.service.search.PerformanceEvaluationAssessmentTopicSearch;
import com.eteacher.service.service.PerformanceEvaluationAssessmentTopicService;
import com.eteacher.service.utils.CommonDataHelper;
import com.eteacher.service.utils.PaginatedResponse;
import com.eteacher.service.validation.PerformanceEvaluationAssessmentTopicValidator;
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
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.ResponseBuilder.*;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Performance Evaluation Assessment Topic Data")
@RequestMapping(
        "api/v1/performance-evaluation-assessment-topic"
)
public class PerformanceEvaluationAssessmentTopicResource {

    private final PerformanceEvaluationAssessmentTopicValidator validator;

    private final PerformanceEvaluationAssessmentTopicService service;

    private final CommonDataHelper helper;

    @PostMapping("/save")
    @ApiOperation(
            value = "save performance evaluation assessment topic",
            response = PerformanceEvaluationAssessmentTopicResponse.class
    )
    public ResponseEntity<JSONObject> save(@Valid @RequestBody PerformanceEvaluationAssessmentTopicDto request,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, request, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        PerformanceEvaluationAssessmentTopic performanceEvaluationAssessmentTopic = request.to();

        service.save(performanceEvaluationAssessmentTopic);
        return ok(success(PerformanceEvaluationAssessmentTopicResponse.from(performanceEvaluationAssessmentTopic),
                PERFORM_EVAL_ASSESSMENT_TOPIC_SAVE).getJson()
        );
    }

    @PutMapping("/update")
    @ApiOperation(
            value = "update performance evaluation assessment topic",
            response = PerformanceEvaluationAssessmentTopicResponse.class
    )
    public ResponseEntity<JSONObject> update(@Valid @RequestBody PerformanceEvaluationAssessmentTopicDto request,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        PerformanceEvaluationAssessmentTopic topic = service.findById(request.getId()).orElseThrow(ResourceNotFoundException::new);

        request.update(topic);

        service.update(topic);
        return ok(success(PerformanceEvaluationAssessmentTopicResponse.from(topic),
                PERFORM_EVAL_ASSESSMENT_TOPIC_UPDATE).getJson()
        );
    }

    @GetMapping("/search")
    @ApiOperation(value = "search Performance Evaluation Assessment Topic",
            response = PerformanceEvaluationAssessmentTopicResponse.class)
    public ResponseEntity<JSONObject> getList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy,
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "performanceEvaluationAssessmentTopicDescription", defaultValue = "") String performanceEvaluationAssessmentTopicDescription,
            @RequestParam(value = "performanceEvaluationAssessmentTopicDescriptionBn", defaultValue = "") String performanceEvaluationAssessmentTopicDescriptionBn,
            @RequestParam(value = "maxScore", defaultValue = "") Integer maxScore,
            @RequestParam(value = "qualifyingScore", defaultValue = "") Integer qualifyingScore) {

        helper.setPageSize(page, size);

        PaginatedResponse response = new PaginatedResponse();

        Map<String, Object> topicMap = service.getList(
                PerformanceEvaluationAssessmentTopicSearch.sortable,
                PerformanceEvaluationAssessmentTopicSearch.searchable,
                sortBy,
                search,
                page,
                size,
                PerformanceEvaluationAssessmentTopicResponse.searchPerformanceEvaluationAssessmentTopic(
                        performanceEvaluationAssessmentTopicDescription,
                        performanceEvaluationAssessmentTopicDescriptionBn,
                        maxScore,
                        qualifyingScore
                ));

        List<PerformanceEvaluationAssessmentTopic> topics = (List<PerformanceEvaluationAssessmentTopic>) topicMap.get("lists");

        List<PerformanceEvaluationAssessmentTopicResponse> responses = topics.stream()
                .map(PerformanceEvaluationAssessmentTopicResponse::from)
                .collect(Collectors.toList());

        helper.getCommonData(page, size, topicMap, response, responses);

        return ok(paginatedSuccess(response).getJson());
    }

    @GetMapping("/lists")
    @ApiOperation(value = "get all performance evaluation assessment Topic list",
            response = PerformanceEvaluationAssessmentTopicResponse.class)
    public ResponseEntity<JSONObject> findAll(
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy) {

        List<PerformanceEvaluationAssessmentTopicResponse> responses = service
                .findAll(PerformanceEvaluationAssessmentTopicSearch.sortable, sortBy)
                .stream()
                .map(PerformanceEvaluationAssessmentTopicResponse::from)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "get performance evaluation assessment topic by id",
            response = PerformanceEvaluationAssessmentTopicResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        PerformanceEvaluationAssessmentTopicResponse response = service
                .findByIdAndRecordStatusNot(id)
                .map(PerformanceEvaluationAssessmentTopicResponse::from)
                .orElseThrow(ResourceNotFoundException::new);

        return ok(success(response).getJson());
    }

    @PutMapping("/delete/{id}")
    @ApiOperation(value = "delete performance evaluation assessment topic by id",
            response = PerformanceEvaluationAssessmentTopicResponse.class)
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        PerformanceEvaluationAssessmentTopic topic = service.findById(id).orElseThrow(ResourceNotFoundException::new);

        service.delete(topic);
        return ok(success(PERFORM_EVAL_ASSESSMENT_TOPIC_DELETE + id).getJson());
    }
}
