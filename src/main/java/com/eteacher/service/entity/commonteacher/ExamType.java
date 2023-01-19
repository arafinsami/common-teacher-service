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
@Table(name = "EXAM_TYPE")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExamType extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXAM_TYPE_ID")
    private Long id;

    @Column(name = "EXAM_TYPE_NAME")
    private String examTypeName;

    @Column(name = "EXAM_TYPE_NAME_BN")
    private String examTypeNameBn;

    @Column(name = "EXAM_TYPE_DESCRIPTION")
    private String examTypeDescription;

    @Column(name = "EXAM_TYPE_DESCRIPTION_BN")
    private String examTypeDescriptionBn;
}
