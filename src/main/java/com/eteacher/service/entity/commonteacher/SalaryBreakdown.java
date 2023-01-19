package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "SALARY_BREAKDOWN")
@EqualsAndHashCode(callSuper = false)
public class SalaryBreakdown extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SALARY_BREAKDOWN_ID")
    private Long id;

    @Column(name = "SALARY_BREAKDOWN_NAME")
    private String breakdownName;

    @Column(name = "SALARY_BREAKDOWN_NAME_BN")
    private String breakdownNameBn;

    @Column(name = "IS_ARREAR")
    private Boolean isArrear;

    @Column(name = "IS_DEDUCTION")
    private Boolean isDeduction;

    @Column(name = "SALARY_BREAKDOWN_DESCRIPTION")
    private String breakdownDescription;

    @Column(name = "SALARY_BREAKDOWN_DESCRIPTION_BN")
    private String breakdownDescriptionBn;
}
