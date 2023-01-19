package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.PaymentMethod;
import lombok.Data;

@Data
public class PaymentMethodAudit {

  private Long id;

  private String paymentMethodName;

  private String paymentMethodNameBn;

  public static PaymentMethodAudit from(PaymentMethod paymentMethod) {
    PaymentMethodAudit audit = new PaymentMethodAudit();
    audit.setId(paymentMethod.getId());
    audit.setPaymentMethodName(paymentMethod.getPaymentMethodName());
    audit.setPaymentMethodNameBn(paymentMethod.getPaymentMethodNameBn());
    return audit;
  }
}
