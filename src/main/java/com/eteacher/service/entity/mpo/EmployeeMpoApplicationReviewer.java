package com.eteacher.service.entity.mpo;

import com.eteacher.service.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "EMPLOYEE_MPO_APPLICATION_REVIEWER")
public class EmployeeMpoApplicationReviewer extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_MPO_APPLICATION_REVIEWER_ID")
    private Long id;

    @Column(name = "REVIEWER_SL")
    private Integer reviewerSl;

    @Column(name = "REVIEWER_USER_ID")
    private Long reviewerUserId;

    @Column(name = "REVIEWER_NOTE")
    private String reviewerNote;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REVIEWER_DATE")
    private Date reviewerDate;

    @Column(name = "REASON_FOR_REJECTION_ID")
    private Long reasonForRejection;

 /* @OneToMany(
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  @JoinColumn(name = "EMPLOYEE_MPO_APPLICATION_ID")
  List<EmployeeMpoApplicationEncloser> employeeMpoApplicationEncloser;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_MPO_APPLICATION_ID")
    private EmployeeMpoApplication mpoApplication;
}
