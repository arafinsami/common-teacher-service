package com.eteacher.service.resource;

import com.eteacher.service.dto.PaymentMethodDto;
import com.eteacher.service.entity.commonteacher.PaymentMethod;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.procedure.PaymentMethodProcedure;
import com.eteacher.service.response.PaymentMethodResponse;
import com.eteacher.service.response.StudentTypeResponse;
import com.eteacher.service.service.PaymentMethodService;
import com.eteacher.service.validation.PaymentMethodValidator;
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
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Payment Method Data")
@RequestMapping(
        "api/v1/payment-method"
)
public class PaymentMethodResource {

    private final PaymentMethodValidator validator;

    private final PaymentMethodService service;

    private final String[] sortable = {"id", "studentTypeName"};

    @PostMapping("/save")
    @ApiOperation(value = "save payment method Type", response = PaymentMethodResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody PaymentMethodDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        PaymentMethod paymentMethod = dto.to();

        service.save(paymentMethod);
        return ok(success(PaymentMethodResponse.response(paymentMethod), PAYMENT_METHOD_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update payment method", response = PaymentMethodResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody PaymentMethodDto dto,
                                             BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        PaymentMethod paymentMethod = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);

        dto.update(paymentMethod);

        service.update(paymentMethod);
        return ok(success(PaymentMethodResponse.response(paymentMethod), PAYMENT_METHOD_UPDATE).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "get payment method by id", response = PaymentMethodResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        Optional<PaymentMethodResponse> response = Optional.ofNullable(service.findById(id)
                .map(PaymentMethodResponse::response)
                .orElseThrow(ResourceNotFoundException::new));

        return ok(success(response).getJson());
    }

    @GetMapping("/search")
    @ApiOperation(value = "Get all applications list", response = PaymentMethodResponse.class)
    public ResponseEntity<JSONObject> getApplicationList(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "paymentMethodName", defaultValue = "") String paymentMethodName,
            @RequestParam(value = "sort", defaultValue = "ASC") String sort) {

        Map<String, Object> map = service.search(
                paymentMethodName,
                sort,
                page,
                size
        );

        List<PaymentMethodProcedure> views = PaymentMethodProcedure.toList(map);
        return ok(success(PaymentMethodProcedure.getResponse(page, size, map, views)).getJson());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete payment method by id")
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        PaymentMethod paymentMethod = service.findById(id).orElseThrow(ResourceNotFoundException::new);

        service.delete(paymentMethod);
        return ok(success(null, PAYMENT_METHOD_DELETE).getJson());
    }

    @GetMapping("/all")
    @ApiOperation(value = "Get all employee type", response = PaymentMethodResponse.class)
    public ResponseEntity<JSONObject> findAll(
            @RequestParam(value = "sortBy", defaultValue = "") String sortBy
    ) {

        List<PaymentMethodResponse> responses = service.findAll(sortable, sortBy)
                .stream()
                .map(PaymentMethodResponse::response)
                .collect(Collectors.toList());

        return ok(success(responses).getJson());
    }
}
