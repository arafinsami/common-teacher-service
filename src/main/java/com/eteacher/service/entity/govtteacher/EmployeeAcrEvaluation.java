package com.eteacher.service.entity.govtteacher;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "EMPLOYEE_ACR_EVALUATION")
public class EmployeeAcrEvaluation extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ACR_EVALUATION_ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ASSESSMENT_DATE")
    private Date assessmentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 12)
    private Status status;

    @Column(name = "APPROVER_USER_ID")
    private Long approverUserId;

    @Column(name = "APPROVER_NOTE")
    private String approverNote;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APPROVE_DATE")
    private Date approveDate;

   /* @OneToOne
    @JoinColumn(name = "ACR_EVALUATION_ASSESSMENT_TOPIC_ID", nullable = false)
    private AcrEvaluationAssessmentTopic acrEvaluationAssessmentTopic;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "acrEvaluation"
    )
    private List<EmployeeAcrEvaluationAssessmentQuestionsAnswer> answers;

    public void addAnswer(EmployeeAcrEvaluationAssessmentQuestionsAnswer answer) {
        if (answers == null) {
            answers = new ArrayList<>();
        }
        answer.setAcrEvaluation(this);
        answers.add(answer);
    }
}
