package com.eteacher.service.entity.mpo;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "EMPLOYEE_JOB_POSTING_REQUEST_ENCLOSER")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeJobPostingRequestEncloser extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_JOB_POSTING_REQUEST_ENCLOSER_ID")
    private Long id;

    @Column(name = "ENCLOSER_ID")
    private Long encloserId;

    @Column(name = "ENCLOSER_NAME")
    private String encloserName;

    @Column(name = "ENCLOSER_NAME_BN")
    private String encloserNameBn;

    @Enumerated(EnumType.STRING)
    @Column(name = "ENCLOSER_TYPE", length = 12)
    private EncloserType encloserType;

    @Column(name = "ENCLOSER_URL")
    private String encloserUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_JOB_POSTING_REQUEST_ID")
    private EmployeeJobPostingRequest jobPostingRequest;
}
