package com.eteacher.service.service;

import com.eteacher.service.audit.PaymentMethodAudit;
import com.eteacher.service.entity.commonteacher.PaymentMethod;
import com.eteacher.service.entity.commonteacher.SalaryScale;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.GetListHelper;
import com.eteacher.service.helper.PaymentMethodHelper;
import com.eteacher.service.procedure.PaymentMethodProcedure;
import com.eteacher.service.query.GenericQuery;
import com.eteacher.service.repository.PaymentMethodRepository;
import com.eteacher.service.utils.GenericStoreProcedure;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;

@Service
@RequiredArgsConstructor
public class PaymentMethodService extends GenericQuery {

    private final PaymentMethodRepository repository;

    private final ActionLogService actionLogService;

    private final PaymentMethodHelper helper;

    private final GenericStoreProcedure procedure;

    private final EntityManager em;

    public Optional<PaymentMethod> findByPaymentMethodName(String paymentMethodName) {
        Optional<PaymentMethod> paymentMethod = repository.findByPaymentMethodName(paymentMethodName);
        return paymentMethod;
    }

    public Optional<PaymentMethod> findById(Long id) {
        Optional<PaymentMethod> method = repository.findById(id);
        return method;
    }

    public List<PaymentMethod> findAll(String[] sortable, String sortBy) {
        return new GetListHelper<PaymentMethod>(em, PaymentMethod.class).findAll(sortable, sortBy);
    }

    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) {
        helper.getSaveData(paymentMethod);
        PaymentMethod savePaymentMethod = repository.save(paymentMethod);
        PaymentMethodAudit audit = PaymentMethodAudit.from(savePaymentMethod);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                PAYMENT_METHOD_SAVE,
                objectToJson(audit)
        );
        return savePaymentMethod;
    }

    @Transactional
    public PaymentMethod update(PaymentMethod paymentMethod) {
        helper.getUpdateData(paymentMethod, RecordStatus.ACTIVE);
        PaymentMethod updatedPaymentMethod = repository.save(paymentMethod);
        PaymentMethodAudit audit = PaymentMethodAudit.from(updatedPaymentMethod);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                PAYMENT_METHOD_UPDATE,
                objectToJson(audit)
        );
        return updatedPaymentMethod;
    }

    @Transactional
    public PaymentMethod delete(PaymentMethod paymentMethod) {
        helper.getUpdateData(paymentMethod, RecordStatus.DELETED);
        PaymentMethod deletedPaymentMethod = repository.save(paymentMethod);
        PaymentMethodAudit audit = PaymentMethodAudit.from(deletedPaymentMethod);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                PAYMENT_METHOD_DELETE,
                objectToJson(audit)
        );
        return deletedPaymentMethod;
    }

    public Map<String, Object> search(String paymentMethodName,
                                      String sort,
                                      Integer page,
                                      Integer size) {
        SqlParameterSource source = new MapSqlParameterSource(
                PaymentMethodProcedure.search(
                        paymentMethodName,
                        sort,
                        page,
                        size
                )
        );
        Map<String, Object> map = procedure
                .callProcedure("COMMON_TEACHER_GLOBAL_SEARCH")
                .execute(source);
        return map;
    }
}
