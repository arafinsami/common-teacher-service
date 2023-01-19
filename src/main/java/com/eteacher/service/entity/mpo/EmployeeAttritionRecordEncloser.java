package com.eteacher.service.entity.mpo;

import com.eteacher.service.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EMPLOYEE_ATTRITION_RECORD_ENCLOSER")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeAttritionRecordEncloser extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ATTRITION_RECORD_ENCLOSER_ID")
    private Long id;

    @Column(name = "ENCLOSER_ID")
    private Long encloserId;

    @Column(name = "ENCLOSER_NAME")
    private String encloserName;

    @Column(name = "ENCLOSER_NAME_BN")
    private String encloserNameBn;

    @Column(name = "ENCLOSER_TYPE")
    private Integer encloserType;

    @Column(name = "ENCLOSER_URL")
    private String encloserUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ATTRITION_RECORD_ID")
    private EmployeeAttritionRecord employeeAttritionRecord;
}
