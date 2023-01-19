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
@Table(name = "PERFORMANCE_EVALUATION_ASSESSMENT_QUESTION")
public class PerformanceEvaluationAssessmentQuestion extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "PERFORMANCE_EVALUATION_ASSESSMENT_QUESTION_ID")
    private Long id;

    @Column(name = "PERFORMANCE_EVALUATION_ASSESSMENT_QUESTION", length = 250)
    private String assessmentQuestion;

    @Column(name = "PERFORMANCE_EVALUATION_ASSESSMENT_QUESTION_BN", length = 300)
    private String assessmentQuestionBn;

    @Column(name = "APP_CONTROL_TYPE")
    private Integer appControlType;

    @Column(name = "UNIT_MARK")
    private Integer unitMark;

    @OneToOne
    @JoinColumn(name = "PERFORMANCE_EVALUATION_ASSESSMENT_TOPIC_ID")
    private PerformanceEvaluationAssessmentTopic assesmentTopic;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "performanceEvaluationAssessmentQuestion"
    )
    private List<PerformanceEvaluationAssessmentQuestionAnswerOption> answerOptions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERFORMANCE_EVALUATION_ID")
    private PerformanceEvaluation performanceEvaluation;
}
