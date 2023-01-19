package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.PaymentMethod;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class PaymentMethodDto {

    private Long id;

    @NotBlank
    private String paymentMethodName;

    @NotBlank
    private String paymentMethodNameBn;

    public PaymentMethod to() {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodName(paymentMethodName);
        paymentMethod.setPaymentMethodNameBn(paymentMethodNameBn);
        return paymentMethod;
    }

    public void update(PaymentMethod paymentMethod) {
        paymentMethod.setPaymentMethodName(paymentMethodName);
        paymentMethod.setPaymentMethodNameBn(paymentMethodNameBn);
    }
}
