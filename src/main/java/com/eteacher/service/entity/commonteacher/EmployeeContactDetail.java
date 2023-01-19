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
@Table(name = "EMPLOYEE_CONTACT_DETAIL")
@EqualsAndHashCode(callSuper = false)
public class EmployeeContactDetail extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_CONTACT_DETAIL_ID")
    private Long id;

    @Column(name = "CONTACT_SL")
    private Long contactSl;

    @Column(name = "CONTACT_TYPE")
    private Integer contactType;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @Column(name = "ADDRESS_LINE_1_BN")
    private String addressLine1Bn;

    @Column(name = "ADDRESS_LINE_2_BN")
    private String addressLine2Bn;

    @Column(name = "PHONE_WORK")
    private String phoneWork;

    @Column(name = "PHONE_HOME")
    private String phoneHome;

    @Column(name = "PHONE_MOBILE")
    private String phoneMobile;

    @Column(name = "EMAIL_WORK")
    private String emailWork;

    @Column(name = "EMAIL_PERSONAL")
    private String emailPersonal;

    @Column(name = "COUNTRY_ID")
    private Long countryId;

    @Column(name = "DISTRICT_ID")
    private Long districtId;

    @Column(name = "DIVISION_ID")
    private Long divisionId;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ENCLOSER_ID")
    private EmployeeEncloser employeeEncloser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
