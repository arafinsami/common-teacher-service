package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.composite.SalaryScaleBreakdownPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Setter
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "SALARY_SCALE_BREAKDOWN")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SalaryScaleBreakdown extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @EmbeddedId
    private SalaryScaleBreakdownPK salaryScaleBreakdownPK;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "PENSION_AMOUNT")
    private Double pensionAmount;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("SALARY_BREAKDOWN_ID")
    @JoinColumn(name = "SALARY_BREAKDOWN_ID")
    private SalaryBreakdown salaryBreakdown;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("SALARY_SCALE_ID")
    @JoinColumn(name = "SALARY_SCALE_ID")
    private SalaryScale salaryScale;
}
