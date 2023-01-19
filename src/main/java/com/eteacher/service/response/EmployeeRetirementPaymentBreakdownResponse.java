package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeRetirementPaymentBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeRetirementPaymentBreakdownResponse {

    private Long id;

    private Integer salaryBreakdownId;

    private Double amount;

    public static EmployeeRetirementPaymentBreakdownResponse response(EmployeeRetirementPaymentBreakdown breakdown) {
        EmployeeRetirementPaymentBreakdownResponse response = new EmployeeRetirementPaymentBreakdownResponse();
        response.setId(breakdown.getId());
        response.setSalaryBreakdownId(breakdown.getSalaryBreakdownId());
        response.setAmount(breakdown.getAmount());
        return response;
    }
}
