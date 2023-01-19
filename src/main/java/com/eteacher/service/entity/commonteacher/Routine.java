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
@Table(name = "ROUTINE")
public class Routine extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROUTINE_ID")
    private Long id;

    @Column(name = "ROUTINE_NAME")
    private String routineName;

    @Column(name = "ROUTINE_NAME_BN")
    private String routineNameBn;

    @Column(name = "ROUTINE_DESCRIPTION")
    private String routineDescription;

    @Column(name = "ROUTINE_DESCRIPTION_BN")
    private String routineDescriptionBn;
}
