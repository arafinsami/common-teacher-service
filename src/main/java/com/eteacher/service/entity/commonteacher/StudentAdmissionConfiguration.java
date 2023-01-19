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
@Table(name = "STUDENT_ADMISSION_CONFIGURATION")
@EqualsAndHashCode(callSuper = false)
public class StudentAdmissionConfiguration extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ADMISSION_CONFIGURATION_ID")
    private Long id;

    @Column(name = "STUDENT_ADMISSION_CONFIGURATION_NAME")
    private String configurationName;

    @Column(name = "STUDENT_ADMISSION_CONFIGURATION_NAME_BN")
    private String configurationNameBn;

    @Column(name = "SVG_ID")
    private Long svgId;

    @Column(name = "MIN_GRADE_POINT_GENERAL")
    private Double minGradePointGeneral;

    @Column(name = "MIN_GRADE_POINT_INTERNAL")
    private Double minGradePointInternal;

    @Column(name = "MIN_GRADE_POINT_SPECIAL")
    private Double minGradePointSpecial;

    @Column(name = "ACADEMIC_CLASS_ID")
    private Long classId;

    @Column(name = "INSTITUTE_ID")
    private Long instituteId;
}
