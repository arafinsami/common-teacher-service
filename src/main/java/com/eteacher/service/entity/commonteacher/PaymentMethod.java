package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "PAYMENT_METHOD")
@EqualsAndHashCode(callSuper = false)
public class PaymentMethod extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_METHOD_ID")
    private Long id;

    @Column(name = "PAYMENT_METHOD_NAME")
    private String paymentMethodName;

    @Column(name = "PAYMENT_METHOD_NAME_BN")
    private String paymentMethodNameBn;
}
