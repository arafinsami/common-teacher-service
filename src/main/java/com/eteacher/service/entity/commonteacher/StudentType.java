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
@Table(name = "STUDENT_TYPE")
@EqualsAndHashCode(callSuper = false)
public class StudentType extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_TYPE_ID")
    private Long id;

    @Column(name = "STUDENT_TYPE_ID_LEGACY")
    private String studentTypeIdLegacy;

    @Column(name = "STUDENT_TYPE_NAME")
    private String studentTypeName;

    @Column(name = "STUDENT_TYPE_NAME_BN")
    private String studentTypeNameBn;
}
