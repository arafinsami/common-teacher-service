package com.eteacher.service.entity.job;

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
@EqualsAndHashCode(callSuper = false)
@Table(name = "JOB_APPLICANT_EDUCATIONAL_QUALIFICATION")
public class JobApplicantEducationalQualification extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_APPLICANT_EDUCATIONAL_QUALIFICATION_ID")
    private Long id;

    @Column(name = "INSTITUTE_UNIVERSITY_NAME")
    private String universityName;

    @Column(name = "EDUCATION_QUALIFICATION_EXAM_GRADE")
    private String examGrade;

    @Column(name = "EDUCATION_QUALIFICATION_EXAM_GRADE_BN")
    private String examGradeBn;

    @Column(name = "JOB_APPLICANT_EDUCATIONAL_QUALIFICATION_SL")
    private Long qualificationSl;

    @Column(name = "EDUCATION_QUALIFICATION_EXAM_PASSING_YEAR")
    private Integer examPassingYear;

    @Column(name = "SCORE_ACHIEVED")
    private Integer scoreAchieved;

    @Column(name = "IS_FOREIGN")
    private Boolean isForeign;

    @Column(name = "EDUCATION_BOARD_ID")
    private Long educationBoardId;

    @Column(name = "EDUCATIONAL_QUALIFICATION_ID")
    private Long educationalQualificationId;

    @Column(name = "GRADE_POINT_ID")
    private Long gradePointId;

    @Column(name = "INSTITUTE_ID")
    private Long instituteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_APPLICANT_ID")
    private JobApplicant applicant;
}
