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
@Table(name = "EMPLOYEE_EDUCATIONAL_QUALIFICATION")
@EqualsAndHashCode(callSuper = false)
public class EmployeeEducationalQualification extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_EDUCATIONAL_QUALIFICATION_ID")
    private Long id;

    @Column(name = "INSTITUTE_UNIVERSITY_NAME")
    private String instituteUniversityName;

    @Column(name = "EDUCATION_QUALIFICATION_EXAM_GRADE")
    private String educationQualificationExamGrade;

    @Column(name = "EDUCATION_QUALIFICATION_EXAM_GRADE_BN")
    private String educationQualificationExamGradeBn;

    @Column(name = "EMPLOYEE_EDUCATIONAL_QUALIFICATION_SL")
    private Long employeeEducationalQualificationSl;

    @Column(name = "EDUCATION_QUALIFICATION_EXAM_PASSING_YEAR")
    private Integer educationQualificationExamPassingYear;

    @Column(name = "SCORE_ACHIEVED")
    private Integer scoreAchieved;

    @Column(name = "IS_FOREIGN")
    private Boolean isForeign;

    @Column(name = "EDUCATION_BOARD_ID")
    private Long educationBoard;

    @Column(name = "EDUCATIONAL_QUALIFICATION_ID")
    private Long educationalQualification;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ENCLOSER_ID")
    private EmployeeEncloser employeeEncloser;

    @Column(name = "GRADE_POINT_ID")
    private Long gradePoint;

    @Column(name = "INSTITUTE_ID", nullable = false)
    private Long institute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
