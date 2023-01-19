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
@Table(name = "EXAM_TERM")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExamTerm extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXAM_TERM_ID")
    private Long id;

    @Column(name = "EXAM_TERM_NAME")
    private String examTermName;

    @Column(name = "EXAM_TERM_NAME_BN")
    private String examTermNameBn;

    @Column(name = "IS_CT")
    private Boolean isCt;

    @Column(name = "IS_PRACTICAL")
    private Boolean isPractical;
}
