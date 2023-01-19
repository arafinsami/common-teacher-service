package com.eteacher.service.entity.job;

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
@Table(name = "JOB_APPLICANT_ENCLOSER")
public class JobApplicantEncloser extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_APPLICANT_ENCLOSER_ID")
    private Long id;

    @Column(name = "ENCLOSER_ID")
    private Long encloserId;

    @Column(name = "ENCLOSER_NAME")
    private String encloserName;

    @Column(name = "ENCLOSER_NAME_BN")
    private String encloserNameBn;

    @Column(name = "ENCLOSER_TYPE")
    private Integer encloserType;

    @Column(name = "ENCLOSER_URL")
    private String encloserUrl;
}
