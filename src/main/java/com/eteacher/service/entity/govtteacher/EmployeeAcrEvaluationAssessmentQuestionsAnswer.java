package com.eteacher.service.entity.govtteacher;

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
@Table(name = "EMPLOYEE_ACR_EVALUATION_ASSESSMENT_QUESTIONS_ANSWER")
public class EmployeeAcrEvaluationAssessmentQuestionsAnswer extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ACR_EVALUATION_ASSESSMENT_QUESTIONS_ANSWER_ID")
    private Long id;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "ANSWER_BN")
    private String answerBn;

/*
  @OneToOne
  @JoinColumn(name = "ACR_EVALUATION_ASSESSMENT_QUESTION_ID", nullable = false)
  private AcrEvaluationAssessmentQuestion acrEvaluationAssessmentQuestion;

  @OneToOne
  @JoinColumn(name = "ACR_EVALUATION_ASSESSMENT_QUESTION_ANSWER_OPTION_ID", nullable = false)
  private AcrEvaluationAssessmentQuestionAnswerOption acrEvaluationAssessmentQuestionAnswerOption;

  @OneToOne
  @JoinColumn(name = "ACR_EVALUATION_ASSESSMENT_TOPIC_ID", nullable = false)
  private AcrEvaluationAssessmentTopic acrEvaluationAssessmentTopic;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ACR_EVALUATION_ID")
    private EmployeeAcrEvaluation acrEvaluation;
}
