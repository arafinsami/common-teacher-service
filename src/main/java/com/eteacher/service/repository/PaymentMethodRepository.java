package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.PaymentMethod;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    Optional<PaymentMethod> findByPaymentMethodName(String paymentMethodName);

    Optional<PaymentMethod> findTopByOrderByIdDesc();

    Optional<PaymentMethod> findByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
