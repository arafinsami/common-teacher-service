package com.eteacher.service.entity.mpo;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.enums.EncloserType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "EMPLOYEE_MPO_APPLICATION_ENCLOSER")
public class EmployeeMpoApplicationEncloser extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_MPO_APPLICATION_ENCLOSER_ID")
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
    @JoinColumn(name = "EMPLOYEE_MPO_APPLICATION_ID")
    private EmployeeMpoApplication mpoApplication;

  /*@OneToMany(
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  @JoinColumn(name = "EMPLOYEE_MPO_APPLICATION_ID")
  private List<EmployeeMpoApplicationReviewer> employeeMpoApplicationReviewer;*/
}
