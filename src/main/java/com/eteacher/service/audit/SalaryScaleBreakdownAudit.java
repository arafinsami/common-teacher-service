package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.SalaryScaleBreakdown;
import lombok.Data;

@Data
public class SalaryScaleBreakdownAudit {

  private Long id;

  private Double amount;

  private Double pensionAmount;

  private Long salaryScaleId;

  private Long salaryBreakdownId;

    public static SalaryScaleBreakdownAudit from(SalaryScaleBreakdown salaryScaleBreakdown){
        SalaryScaleBreakdownAudit audit = new SalaryScaleBreakdownAudit();
        //BeanUtils.copyProperties(salaryScaleBreakdown, audit);
        /*if(salaryScaleBreakdown.getSalaryBreakdown()!= null
            && salaryScaleBreakdown.getSalaryScale() != null
        ){
            audit.setSalaryBreakdownId(salaryScaleBreakdown.getSalaryBreakdown().getId());
            audit.setSalaryScaleId(salaryScaleBreakdown.getSalaryScale().getId());
        }else {
            audit.setSalaryBreakdownId(salaryScaleBreakdown.getSalaryScaleBreakdownPK().getSalaryBreakdownId());
            audit.setSalaryScaleId(salaryScaleBreakdown.getSalaryScaleBreakdownPK().getSalaryScaleId());
        }*/
        return audit;
    }
    
}
