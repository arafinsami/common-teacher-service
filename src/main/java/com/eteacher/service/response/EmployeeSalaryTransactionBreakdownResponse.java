package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeSalaryTransactionBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeSalaryTransactionBreakdownResponse {

    private Long id;

    private Integer salaryBreakdownId;

    private Double amount;

    public static EmployeeSalaryTransactionBreakdownResponse response(EmployeeSalaryTransactionBreakdown breakdown) {
        EmployeeSalaryTransactionBreakdownResponse response = new EmployeeSalaryTransactionBreakdownResponse();
        response.setId(breakdown.getId());
        response.setAmount(breakdown.getAmount());
        return response;
    }
}
