package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "PERFORMANCE_EVALUATION")
public class PerformanceEvaluation extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERFORMANCE_EVALUATION_ID")
    private Long id;

    @Column(name = "PERFORMANCE_EVALUATION_DESCRIPTION")
    private String evaluationDescription;

    @Column(name = "PERFORMANCE_EVALUATION_DESCRIPTION_BN")
    private String evaluationDescriptionBn;

    @Column(name = "QUALIFYING_SCORE")
    private Integer qualifyingScore;

    @Column(name = "PERFORMANCE_EVALUATION_YEAR")
    private Integer performanceEvaluationYear;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "performanceEvaluation"
    )
    private List<PerformanceEvaluationAssessmentQuestion> assessmentQuestions;
}
