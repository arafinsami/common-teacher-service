package com.eteacher.service.audit;

import com.eteacher.service.entity.mpo.EmployeeMpoPaymentRequestBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeMpoPaymentRequestBreakdownAudit {

    private Long id;

    private Integer salaryBreakdownId;

    private Double mpoAmount;

    public static EmployeeMpoPaymentRequestBreakdownAudit audit(EmployeeMpoPaymentRequestBreakdown breakdown) {
        EmployeeMpoPaymentRequestBreakdownAudit audit = new EmployeeMpoPaymentRequestBreakdownAudit();
        audit.setId(breakdown.getId());
        audit.setSalaryBreakdownId(breakdown.getSalaryBreakdownId());
        audit.setMpoAmount(breakdown.getMpoAmount());
        return audit;
    }
}
