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
@Table(name = "INSTITUTE_EXAM_MARKING_POLICY_DETAIL")
@EqualsAndHashCode(callSuper = false)
public class InstituteExamMarkingPolicyDetail extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INSTITUTE_EXAM_MARKING_POLICY_DETAIL_ID")
    private Long id;

    @Column(name = "PERCENTAGE_MARKS")
    private Double percentageMarks;

    @Column(name = "EXAM_TERM_ID")
    private Long examTerm;

    @Column(name = "EXAM_TYPE_ID")
    private Long examType;

    @Column(name = "SUBJECT_ID")
    private Long subjectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSTITUTE_EXAM_MARKING_POLICY_ID")
    private InstituteExamMarkingPolicy policy;
}
