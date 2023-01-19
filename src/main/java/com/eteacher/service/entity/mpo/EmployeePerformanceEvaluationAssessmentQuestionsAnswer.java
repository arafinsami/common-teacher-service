package com.eteacher.service.entity.mpo;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestion;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentQuestionAnswerOption;
import com.eteacher.service.entity.commonteacher.PerformanceEvaluationAssessmentTopic;
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
@Table(name = "EMPLOYEE_PERFORMANCE_EVALUATION_ASSESSMENT_QUESTIONS_ANSWER")
public class EmployeePerformanceEvaluationAssessmentQuestionsAnswer extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_PERFORMANCE_EVALUATION_ASSESSMENT_QUESTIONS_ANSWER_ID")
    private Long id;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "ANSWER_BN")
    private String answerBn;

    @Column(name = "SCORE_ACHIEVED")
    private Integer scoreAchieved;

    @OneToOne
    @JoinColumn(name = "PERFORMANCE_EVALUATION_ASSESSMENT_QUESTION_ID")
    private PerformanceEvaluationAssessmentQuestion performanceEvaluationAssessmentQuestion;

    @OneToOne
    @JoinColumn(name = "PERFORMANCE_EVALUATION_ASSESSMENT_QUESTION_ANSWER_OPTION_ID")
    private PerformanceEvaluationAssessmentQuestionAnswerOption performanceEvaluationAssessmentQuestionAnswerOption;

    @OneToOne
    @JoinColumn(name = "PERFORMANCE_EVALUATION_ASSESSMENT_TOPIC_ID")
    private PerformanceEvaluationAssessmentTopic performanceEvaluationAssessmentTopic;
}
