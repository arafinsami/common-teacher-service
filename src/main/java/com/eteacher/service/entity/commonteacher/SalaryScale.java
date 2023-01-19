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
@Table(name = "SALARY_SCALE")
@EqualsAndHashCode(callSuper = false)
public class SalaryScale extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SALARY_SCALE_ID")
    private Long id;

    @Column(name = "SALARY_SCALE_NAME")
    private String salaryScaleName;

    @Column(name = "SALARY_SCALE_NAME_BN")
    private String salaryScaleNameBn;

    @Column(name = "IS_NATIONAL_SCALE")
    private Boolean isNationalScale;

    @Column(name = "INSTITUTE_TYPE_ID")
    private Long instituteType;
}
