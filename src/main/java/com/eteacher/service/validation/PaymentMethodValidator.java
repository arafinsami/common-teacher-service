package com.eteacher.service.validation;

import com.eteacher.service.dto.PaymentMethodDto;
import com.eteacher.service.entity.commonteacher.PaymentMethod;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.utils.StringUtils.isNull;
import static com.eteacher.service.utils.StringUtils.nonNull;

@Component
@RequiredArgsConstructor
public class PaymentMethodValidator implements Validator {

    private final PaymentMethodService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return PaymentMethodDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        PaymentMethodDto dto = (PaymentMethodDto) target;

        if (isNull(dto.getId())) {
            Optional<PaymentMethod> paymentMethodName = service.findByPaymentMethodName(dto.getPaymentMethodName());
            if (paymentMethodName.isPresent()) {
                error.rejectValue("paymentMethodName", null, ALREADY_EXIST);
            }
        }

        if (nonNull(dto.getId())) {
            PaymentMethod paymentMethod = service.findById(dto.getId()).orElseThrow(ResourceNotFoundException::new);
            if (!paymentMethod.getPaymentMethodName().equals(dto.getPaymentMethodName())) {
                Optional<PaymentMethod> paymentMethodName = service.findByPaymentMethodName(dto.getPaymentMethodName());
                if (paymentMethodName.isPresent()) {
                    error.rejectValue("paymentMethodName", null, ALREADY_EXIST);
                }
            }
        }
    }
}
