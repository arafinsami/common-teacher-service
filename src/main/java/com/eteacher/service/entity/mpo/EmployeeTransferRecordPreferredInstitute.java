package com.eteacher.service.entity.mpo;

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
@Table(name = "EMPLOYEE_TRANSFER_RECORD_PREFERRED_INSTITUTE")
public class EmployeeTransferRecordPreferredInstitute extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_TRANSFER_RECORD_PREFERRED_INSTITUTE_ID")
    private Long id;

    @Column(name = "PREFERENCE_ORDER_INDEX")
    private Integer preferenceOrderIndex;

/*  @OneToOne
  @JoinColumn(name = "PREFERRED_INSTITUTE_ID", nullable = false)
  private Institute institute;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_TRANSFER_RECORD_ID")
    private EmployeeTransferRecord transferRecord;

  /*@OneToMany(
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  @JoinColumn(name = "EMPLOYEE_TRANSFER_RECORD_ID")
  List<EmployeeTransferRecordEncloser> employeeTransferRecordEncloser;

  @OneToMany(
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  @JoinColumn(name = "EMPLOYEE_TRANSFER_RECORD_ID")
  List<EmployeeTransferRecordEncloser> employeeTransferRecordEncloser1;*/
}
