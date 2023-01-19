package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "PAYMENT_PERIOD")
public class PaymentPeriod extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_PERIOD_ID")
    private Long id;

    @Column(name = "PAYMENT_PERIOD_NAME")
    private String paymentPeriodName;

    @Column(name = "PAYMENT_PERIOD_NAME_BN")
    private String paymentPeriodNameBn;

    @Temporal(TemporalType.DATE)
    @JsonFormat(
            pattern = "yyyy-MM-dd'T'HH:mm:ss",
            shape = JsonFormat.Shape.STRING
    )
    @Column(name = "START_DATE")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @JsonFormat(
            pattern = "yyyy-MM-dd'T'HH:mm:ss",
            shape = JsonFormat.Shape.STRING
    )
    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "MONTH")
    private Integer month;

    @Column(name = "YEAR")
    private Integer year;
}
