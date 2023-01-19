package com.eteacher.service.validation;

import com.eteacher.service.dto.PaymentPeriodDto;
import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.exception.ResourceNotFoundException;
import com.eteacher.service.service.PaymentPeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static com.eteacher.service.constant.ValidatorConstants.ALREADY_EXIST;
import static com.eteacher.service.constant.ValidatorConstants.DATE_DIFF_PAYMENT_PERIOD;
import static com.eteacher.service.utils.StringUtils.*;


@Component
@RequiredArgsConstructor
public class PaymentPeriodValidator implements Validator {

    private final PaymentPeriodService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return PaymentPeriodDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors error) {

        PaymentPeriodDto request = (PaymentPeriodDto) target;

        if (isNull(request.getId())) {
            Optional<PaymentPeriod> paymentPeriodName = service.findByPaymentPeriodName(request.getPaymentPeriodName());
            if (paymentPeriodName.isPresent()) {
                error.rejectValue("paymentPeriodName", null, ALREADY_EXIST);
            }

            if (isNotEmpty(request.getStartDate()) && isNotEmpty(request.getEndDate())) {
                if (!request.getStartDate().before(request.getEndDate())) {
                    error.rejectValue("startDate", null, DATE_DIFF_PAYMENT_PERIOD);
                }
            }
        }

        if (nonNull(request.getId())) {
            PaymentPeriod paymentPeriod = service.findById(request.getId()).orElseThrow(ResourceNotFoundException::new);
            if (!paymentPeriod.getPaymentPeriodName().equals(request.getPaymentPeriodName())) {
                Optional<PaymentPeriod> paymentPeriodName = service.findByPaymentPeriodName(request.getPaymentPeriodName());
                if (paymentPeriodName.isPresent()) {
                    error.rejectValue("paymentPeriodName", null, ALREADY_EXIST);
                }
            }

            if (isNotEmpty(request.getStartDate()) && isNotEmpty(request.getEndDate())) {
                if (!request.getStartDate().before(request.getEndDate())) {
                    error.rejectValue("startDate", null, DATE_DIFF_PAYMENT_PERIOD);
                }
            }
        }
    }
}
