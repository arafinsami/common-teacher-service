package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.SalaryBreakdown;
import com.eteacher.service.entity.mpo.EmployeeSalaryIncrementBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EmployeeSalaryIncrementBreakdownResponse {

    private Long id;

    private Double amount;

    private SalaryBreakdown salaryBreakdown;

    public static EmployeeSalaryIncrementBreakdownResponse response(EmployeeSalaryIncrementBreakdown breakdown) {
        EmployeeSalaryIncrementBreakdownResponse response = new EmployeeSalaryIncrementBreakdownResponse();
        response.setId(breakdown.getId());
        response.setAmount(breakdown.getAmount());
        response.setSalaryBreakdown(breakdown.getSalaryBreakdown());
        return response;
    }
}
