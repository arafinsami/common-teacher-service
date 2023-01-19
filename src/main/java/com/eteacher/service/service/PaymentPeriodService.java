package com.eteacher.service.service;

import com.eteacher.service.audit.PaymentPeriodAudit;
import com.eteacher.service.entity.commonteacher.PaymentPeriod;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.PaymentPeriodHelper;
import com.eteacher.service.query.GenericQuery;
import com.eteacher.service.repository.PaymentPeriodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class PaymentPeriodService extends GenericQuery {

    private final PaymentPeriodRepository repository;

    private final ActionLogService actionLogService;

    private final PaymentPeriodHelper helper;

    public Optional<PaymentPeriod> findById(Long id) {
        Optional<PaymentPeriod> paymentPeriod = repository.findById(id);
        return paymentPeriod;
    }

    public Optional<PaymentPeriod> findByPaymentPeriodName(String paymentPeriodName) {
        Optional<PaymentPeriod> paymentPeriod = repository.findByPaymentPeriodName(paymentPeriodName);
        return paymentPeriod;
    }

    public Optional<PaymentPeriod> findByPaymentPeriodNameBn(String paymentPeriodNameBn) {
        Optional<PaymentPeriod> paymentPeriod = repository.findByPaymentPeriodNameBn(paymentPeriodNameBn);
        return paymentPeriod;
    }

    @Transactional
    public PaymentPeriod save(PaymentPeriod paymentPeriod) {
        helper.getSaveData(paymentPeriod);
        PaymentPeriod p = repository.save(paymentPeriod);
        PaymentPeriodAudit audit = PaymentPeriodAudit.audit(p);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                PAYMENT_PERIOD_SAVE,
                objectToJson(audit)
        );
        return p;
    }

    @Transactional
    public PaymentPeriod update(PaymentPeriod paymentPeriod) {
        helper.getUpdateData(paymentPeriod, RecordStatus.ACTIVE);
        PaymentPeriod p = repository.save(paymentPeriod);
        PaymentPeriodAudit audit = PaymentPeriodAudit.audit(p);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                PAYMENT_PERIOD_UPDATE,
                objectToJson(audit)
        );
        return p;
    }

    @Transactional
    public void delete(PaymentPeriod paymentPeriod) {
        helper.getUpdateData(paymentPeriod, RecordStatus.DELETED);
        PaymentPeriod p = repository.save(paymentPeriod);
        PaymentPeriodAudit audit = PaymentPeriodAudit.audit(p);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                PAYMENT_PERIOD_DELETE,
                objectToJson(audit)
        );
    }
}
