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
@Table(name = "PERFORMANCE_EVALUATION_ASSESSMENT_TOPIC")
public class PerformanceEvaluationAssessmentTopic extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERFORMANCE_EVALUATION_ASSESSMENT_TOPIC_ID")
    private Long id;

    @Column(name = "PERFORMANCE_EVALUATION_ASSESSMENT_TOPIC_DESCRIPTION")
    private String topicDescription;

    @Column(name = "PERFORMANCE_EVALUATION_ASSESSMENT_TOPIC_DESCRIPTION_BN")
    private String topicDescriptionBn;

    @Column(name = "MAX_SCORE")
    private Integer maxScore;

    @Column(name = "QUALIFYING_SCORE")
    private Integer qualifyingScore;
}
