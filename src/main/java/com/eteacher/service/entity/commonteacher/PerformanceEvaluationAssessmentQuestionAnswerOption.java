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
@EqualsAndHashCode(callSuper = false)
@Table(name = "PERFORMANCE_EVALUATION_ASSESSMENT_QUESTION_ANSWER_OPTION")
public class PerformanceEvaluationAssessmentQuestionAnswerOption extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERFORMANCE_EVALUATION_ASSESSMENT_QUESTION_ANSWER_OPTION_ID")
    private Long id;

    @Column(name = "PERFORMANCE_EVALUATION_ASSESSMENT_QUESTION_ANSWER_OPTION")
    private String assessmentQuestionAnswerOption;

    @Column(name = "PERFORMANCE_EVALUATION_ASSESSMENT_QUESTION_ANSWER_OPTION_BN")
    private String assessmentQuestionAnswerOptionBn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERFORMANCE_EVALUATION_ASSESSMENT_QUESTION_ID")
    private PerformanceEvaluationAssessmentQuestion performanceEvaluationAssessmentQuestion;
}
