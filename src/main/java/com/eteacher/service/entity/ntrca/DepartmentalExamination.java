package com.eteacher.service.entity.ntrca;

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
@Table(name = "DEPARTMENTAL_EXAMINATION")
public class DepartmentalExamination extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENTAL_EXAMINATION_ID")
    private Long id;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DESCRIPTION_BN")
    private String descriptionBn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;
}
