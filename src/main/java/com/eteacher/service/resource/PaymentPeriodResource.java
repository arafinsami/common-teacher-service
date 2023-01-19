package com.eteacher.service.resource;

import com.eteacher.service.dto.PaymentPeriodDto;
import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.response.PaymentPeriodResponse;
import com.eteacher.service.service.PaymentPeriodService;
import com.eteacher.service.validation.PaymentPeriodValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.exception.ApiError.fieldError;
import static com.eteacher.service.utils.ResponseBuilder.error;
import static com.eteacher.service.utils.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@Api(tags = "COMMON TEACHER---->Payment Period Data")
@RequestMapping(
        "api/v1/payment-period"
)
public class PaymentPeriodResource {

    private final PaymentPeriodValidator validator;

    private final PaymentPeriodService service;

    @PostMapping("/save")
    @ApiOperation(value = "save payment period type", response = PaymentPeriodResponse.class)
    public ResponseEntity<JSONObject> save(@Valid @RequestBody PaymentPeriodDto dto,
                                           BindingResult bindingResult) {

        ValidationUtils.invokeValidator(validator, dto, bindingResult);

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        PaymentPeriod paymentPeriod = dto.to();

        service.save(paymentPeriod);
        return ok(success(PaymentPeriodResponse.response(paymentPeriod), PAYMENT_PERIOD_SAVE).getJson());
    }

    @PutMapping("/update")
    @ApiOperation(value = "update payment period", response = PaymentPeriodResponse.class)
    public ResponseEntity<JSONObject> update(@Valid @RequestBody PaymentPeriodDto dto,
                                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return badRequest().body(error(fieldError(bindingResult)).getJson());
        }

        PaymentPeriod paymentPeriod = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);

        dto.update(paymentPeriod);

        service.update(paymentPeriod);
        return ok(success(PaymentPeriodResponse.response(paymentPeriod), PAYMENT_PERIOD_UPDATE).getJson());
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "get payment period by id", response = PaymentPeriodResponse.class)
    public ResponseEntity<JSONObject> findById(@PathVariable Long id) {

        Optional<PaymentPeriodResponse> response = Optional.ofNullable(service.findById(id)
                .map(PaymentPeriodResponse::response)
                .orElseThrow(ResourceNotFoundException::new));

        return ok(success(response).getJson());
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "delete payment period by id")
    public ResponseEntity<JSONObject> delete(@PathVariable Long id) {

        PaymentPeriod PaymentPeriod = service.findById(id).orElseThrow(ResourceNotFoundException::new);

        service.delete(PaymentPeriod);
        return ok(success(PAYMENT_PERIOD_DELETE + id).getJson());
    }
}
