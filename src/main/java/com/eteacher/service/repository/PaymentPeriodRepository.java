package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentPeriodRepository extends JpaRepository<PaymentPeriod, Long> {

    Optional<PaymentPeriod> findByPaymentPeriodName(String paymentPeriodName);

    Optional<PaymentPeriod> findByPaymentPeriodNameBn(String paymentPeriodNameBn);

    Optional<PaymentPeriod> findTopByOrderByIdDesc();

    Optional<PaymentPeriod> findByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
