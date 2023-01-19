package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.enums.TrainingType;
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
@Table(name = "EMPLOYEE_TRAINING_DETAIL")
@EqualsAndHashCode(callSuper = false)
public class EmployeeTrainingDetail extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_TRAINING_DETAIL_ID")
    private Long id;

    @Column(name = "EMPLOYEE_TRAINING_SL")
    private Long employeeTrainingSl;

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANING_TYPE", length = 30)
    private TrainingType trainingType;

    @Column(name = "TRANING_INSTITUTE_NAME")
    private String trainingInstituteName;

    @Column(name = "TRANING_INSTITUTE_ADDRESS")
    private String trainingInstituteAddress;

    @Column(name = "DATE_OF_ENROLLMENT")
    private Date dateOfEnrollment;

    @Column(name = "DATE_OF_COMPLETION")
    private Date dateOfCompletion;

    @Column(name = "SCORE_ACHIEVED")
    private Integer scoreAchieved;

    @Column(name = "IS_FOREIGN")
    private Boolean isForeign;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ENCLOSER_ID")
    private EmployeeEncloser employeeEncloser;

    @Column(name = "GRADE_POINT_ID")
    private Long gradePoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
