package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.PaymentMethod;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentMethodResponse {

    private Long id;

    private String paymentMethodName;

    private String paymentMethodNameBn;

    public static PaymentMethodResponse response(PaymentMethod paymentMethod) {
        PaymentMethodResponse response = new PaymentMethodResponse();
        response.setId(paymentMethod.getId());
        response.setPaymentMethodName(paymentMethod.getPaymentMethodName());
        response.setPaymentMethodNameBn(paymentMethod.getPaymentMethodNameBn());
        return response;
    }
}
