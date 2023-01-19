package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.enums.ProficiencyLevel;
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
@Table(name = "EMPLOYEE_LANGUAGE_SKILL")
@EqualsAndHashCode(callSuper = false)
public class EmployeeLanguageSkill extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_LANGUAGE_SKILL_ID")
    private Long id;

    @Column(name = "EMPLOYEE_LANGUAGE_SKILL_SL")
    private Long employeeLanguageSkillSl;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROFICIENCY_LEVEL", length = 30)
    private ProficiencyLevel proficiencyLevel;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ENCLOSER_ID")
    private EmployeeEncloser employeeEncloser;

    @Column(name = "LANGUAGE_ID")
    private Long language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
