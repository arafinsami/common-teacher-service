package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.enums.JobNature;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "EMPLOYEE_JOB_EXPERIENCE_DETAIL")
@EqualsAndHashCode(callSuper = false)
public class EmployeeJobExperienceDetail extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_JOB_EXPERIENCE_DETAIL_ID")
    private Long id;

    @Column(name = "EMPLOYEE_JOB_EXPERIENCE_SL")
    private Long employeeJobExperienceSl;

    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;

    @Column(name = "ORGANIZATION_ADDRESS")
    private String organizationAddress;

    @Column(name = "DESIGNATION_NAME")
    private String designationName;

    @Column(name = "DATE_OF_JOINING")
    private Date dateOfJoining;

    @Column(name = "DATE_OF_RELEASE")
    private Date dateOfRelease;

    @Column(name = "IS_PRESENT")
    private Boolean isPresent;

    @Enumerated(EnumType.STRING)
    @Column(name = "JOB_NATURE", length = 12)
    private JobNature jobNature;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ENCLOSER_ID")
    private EmployeeEncloser employeeEncloser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
