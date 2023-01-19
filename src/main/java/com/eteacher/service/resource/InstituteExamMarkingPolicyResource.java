package com.eteacher.service.resource;

import com.eteacher.service.dto.InstExamMarkPolicyDetailDto;
import com.eteacher.service.dto.InstExamMarkPolicyDto;
import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicy;
import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicyDetail;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.helper.InstituteExamMarkingPolicyDetailHelper;
import com.eteacher.service.response.InstExamMarkPolicyResponse;
import com.eteacher.service.response.InstituteExamMarkingPolicyDetailResponse;
import com.eteacher.service.service.InstExamMarkPolicyService;
import com.eteacher.service.validation.InstExamMarkPolicyValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.RecordStatus.DELETED;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.CollectionUtils.distinct;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Institute Exam Mark Policy Data")
@RequestMapping(
        "/api/v1/inst-exam-mark-policy"
)
public class InstituteExamMarkingPolicyResource {

    private final InstExamMarkPolicyService service;

    private final InstExamMarkPolicyValidator validator;

    private final InstituteExamMarkingPolicyDetailHelper helper;

    @PostMapping("/save")
    @ApiOperation(value = "save institute exam marking policy", response = InstExamMarkPolicyResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody InstExamMarkPolicyDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        InstituteExamMarkingPolicy policy = dto.to();

        service.save(policy);
        return ok(success(InstExamMarkPolicyResponse.response(policy), INST_EXAM_MARK_POL_SAVE).getJson());
    }

    @PostMapping("/policy-detail/save")
    @ApiOperation(value = "save institute exam marking policy detail", response = String.class)
    public ResponseEntity<JSONObject> savePolicy(@Valid @RequestBody InstExamMarkPolicyDetailDto dto,
                                                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        InstituteExamMarkingPolicy policy = service.findById(dto.getPolicyId()).orElseThrow(ResourceNotFoundException::new);

        policy.addPolicyDetail(distinct(helper.savePolicy(dto.getPolicyDetails())));

        service.savePolicy(policy);
        return ok(success(INST_EXAM_MARK_POL_DETAIL_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update institute exam marking policy", response = InstExamMarkPolicyResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody InstExamMarkPolicyDto dto,
                                             BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        InstituteExamMarkingPolicy policy = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);

        dto.update(policy);

        service.update(policy);
        return ok(success(InstExamMarkPolicyResponse.response(policy), INST_EXAM_MARK_POL_UPDATE).getJson());
    }

    @PutMapping("/policy-detail/update")
    @ApiOperation(
            value = "update institute exam marking policy detail",
            response = String.class
    )
    public ResponseEntity<JSONObject> updatePolicy(@Valid @RequestBody InstExamMarkPolicyDetailDto dto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        InstituteExamMarkingPolicy policy = service.findById(dto.getPolicyId()).orElseThrow(ResourceNotFoundException::new);

        policy.addPolicyDetail(distinct(helper.updatePolicy(dto.getPolicyDetails())));

        service.updatePolicy(policy);
        return ok(success(INST_EXAM_MARK_POL_DETAIL_UPDATE).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "get institute exam marking policy by id", response = InstExamMarkPolicyResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) throws Exception {

        InstExamMarkPolicyResponse response = service.findById(id)
                .map(InstExamMarkPolicyResponse::response)
                .orElseThrow(() -> new ResourceNotFoundException("policy " + id));

        return ok(success(response, null).getJson());
    }

    @GetMapping("/find/details/{policyId}")
    @ApiOperation(
            value = "get all institute exam marking policy by policy id",
            response = InstituteExamMarkingPolicyDetailResponse.class
    )
    public ResponseEntity<JSONObject> findAllByPolicyId(@Valid @PathVariable @NotNull @Min(1) Long policyId) {

        InstituteExamMarkingPolicy policy = service.findById(policyId).orElseThrow(ResourceNotFoundException::new);

        List<InstituteExamMarkingPolicyDetailResponse> response = policy.getPolicyDetails()
                .stream()
                .filter(f -> f.getRecordStatus() != (DELETED))
                .map(InstituteExamMarkingPolicyDetailResponse::response)
                .collect(Collectors.toList());

        return ok(success(response).getJson());
    }

    @GetMapping("/find/details/{policyId}/{policyDetailId}")
    @ApiOperation(
            value = "get single institute exam marking policy by policy id and detail id",
            response = InstituteExamMarkingPolicyDetailResponse.class
    )
    public ResponseEntity<JSONObject> findOneByIdForAttendance(@Valid @PathVariable("policyId") @NotNull @Min(1) Long policyId,
                                                               @Valid @PathVariable("policyDetailId") @NotNull @Min(1) Long policyDetailId) {

        InstituteExamMarkingPolicy policy = service.findById(policyId).orElseThrow(ResourceNotFoundException::new);

        InstituteExamMarkingPolicyDetail policyDetail = helper.findByPolicyId(policyDetailId, policy.getPolicyDetails());

        return ok(success(InstituteExamMarkingPolicyDetailResponse.response(policyDetail)).getJson());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "institute exam marking policy delete", response = String.class)
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        InstituteExamMarkingPolicy policy = service.findById(id).orElseThrow(ResourceNotFoundException::new);

        service.delete(policy);
        return ok(success(INST_EXAM_MARK_POL_DELETE + id).getJson());
    }

    @DeleteMapping("/delete/details/{policyId}/{policyDetailId}")
    @ApiOperation(
            value = "delete institute exam marking policy by policy id and detail id",
            response = String.class
    )
    public ResponseEntity<JSONObject> deletePolicy(@Valid @PathVariable @NotNull @Min(1) Long policyId,
                                                   @Valid @PathVariable @NotNull @Min(1) Long policyDetailId) {

        InstituteExamMarkingPolicy policy = service.findById(policyId).orElseThrow(ResourceNotFoundException::new);

        InstituteExamMarkingPolicyDetail policyDetail = helper.findByPolicyId(policyDetailId, policy.getPolicyDetails());

        policy.addPolicyDetail(helper.deletePolicy(policyDetail));

        service.deletePolicy(policy);
        return ok(success(INST_EXAM_MARK_POL_DETAIL_DELETE + " " + policyDetailId).getJson());
    }
}
