package com.eteacher.service.entity.composite;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class SalaryScaleBreakdownPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "SALARY_BREAKDOWN_ID")
    private Long salaryBreakdownId;

    @Column(name = "SALARY_SCALE_ID")
    private Long salaryScaleId;
}
