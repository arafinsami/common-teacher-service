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
@Table(name = "RECRUITMENT_TYPE")
@EqualsAndHashCode(callSuper = false)
public class RecruitmentType extends BaseEntity {


    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECRUITMENT_TYPE_ID")
    private Long id;

    @Column(name = "RECRUITMENT_TYPE_NAME")
    private String recruitmentTypeName;

    @Column(name = "RECRUITMENT_TYPE_NAME_BN")
    private String recruitmentTypeNameBn;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DESCRIPTION_BN")
    private String descriptionBn;

}
