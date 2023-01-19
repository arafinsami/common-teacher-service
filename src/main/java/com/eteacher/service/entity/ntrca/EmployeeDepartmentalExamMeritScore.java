package com.eteacher.service.entity.ntrca;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.entity.commonteacher.Employee;
import com.eteacher.service.entity.commonteacher.ExamType;
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
@Table(name = "EMPLOYEE_DEPARTMENTAL_EXAM_MERIT_SCORE")
public class EmployeeDepartmentalExamMeritScore extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_DEPARTMENTAL_EXAM_MERIT_SCORE_ID")
    private Long id;

    @Column(name = "SCORE_ACHIEVED")
    private Double scoreAchieved;

    @Column(name = "EXAM_TYPE_ID")
    private Long examType;

    @Column(name = "SUBJECT_ID")
    private Long subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
