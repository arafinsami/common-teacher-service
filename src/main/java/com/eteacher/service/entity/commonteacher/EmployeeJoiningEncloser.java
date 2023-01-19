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
@Table(name = "EMPLOYEE_JOINING_ENCLOSER")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeJoiningEncloser extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENCLOSER_ID")
    private Long id;

    @Column(name = "ENCLOSER_NAME")
    private String encloserName;

    @Column(name = "ENCLOSER_NAME_BN")
    private String encloserNameBn;

    @Column(name = "ENCLOSER_TYPE")
    private Integer encloserType;

    @Column(name = "ENCLOSER_URL")
    private String encloserUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_JOINING_ID")
    private EmployeeJoining employeeJoining;
}
