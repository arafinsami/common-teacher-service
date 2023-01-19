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
@Table(name = "EMPLOYEE_TYPE")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeType extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_TYPE_ID")
    private Long id;

    @Column(name = "EMPLOYEE_TYPE_NAME")
    private String employeeTypeName;

    @Column(name = "EMPLOYEE_TYPE_NAME_BN")
    private String employeeTypeNameBn;
}
