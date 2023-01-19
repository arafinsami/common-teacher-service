package com.eteacher.service.response;

import com.eteacher.service.entity.govtteacher.EmployeePensionTransactionBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeePensionTransactionBreakdownResponse {

    private Long id;

    private Integer salaryBreakdownId;

    private Double pensionAmount;

    public static EmployeePensionTransactionBreakdownResponse response(EmployeePensionTransactionBreakdown breakdown) {
        EmployeePensionTransactionBreakdownResponse response = new EmployeePensionTransactionBreakdownResponse();
        response.setId(breakdown.getId());
        response.setSalaryBreakdownId(breakdown.getSalaryBreakdownId());
        response.setPensionAmount(breakdown.getPensionAmount());
        return response;
    }
}
