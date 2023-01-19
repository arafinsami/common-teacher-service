package com.eteacher.service.response;

import com.eteacher.service.entity.mpo.EmployeeMpoPaymentRequestBreakdown;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeMpoPaymentRequestBreakdownResponse {

    private Long id;

    private Integer salaryBreakdownId;

    private Double mpoAmount;

    public static EmployeeMpoPaymentRequestBreakdownResponse response(EmployeeMpoPaymentRequestBreakdown breakdown) {
        EmployeeMpoPaymentRequestBreakdownResponse response = new EmployeeMpoPaymentRequestBreakdownResponse();
        response.setId(breakdown.getId());
        response.setSalaryBreakdownId(breakdown.getSalaryBreakdownId());
        response.setMpoAmount(breakdown.getMpoAmount());
        return response;
    }
}
