package com.eteacher.service.entity.commonteacher;

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
@Table(name = "EMPLOYEE_ROUTINE_ASSIGNMENT")
public class EmployeeRoutineAssignment extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ROUTINE_ASSIGNMENT_ID")
    private Long id;

    @Column(name = "DAY_NAME")
    private String dayName;

    @Column(name = "DAY_VALUE")
    private Integer dayValue;

    @Column(name = "START_AT")
    private Date startAt;

    @Column(name = "END_AT")
    private Date endAt;

    @Column(name = "DURATION")
    private Integer duration;

    @Column(name = "IS_RECURRING")
    private Boolean isRecurring;

/*
  @OneToOne
  @JoinColumn(name = "ACADEMIC_CLASS_ID", nullable = false)
  private Class class;*/

    @Column(name = "CLASS_GROUP_ID")
    private Long classGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

//  @OneToOne
//  @JoinColumn(name = "INSTITUTE_BUILDING_ROOM_ID", nullable = false)
//  private InstituteBuildingRoom instituteBuildingRoom;

    @OneToOne
    @JoinColumn(name = "ROUTINE_ID")
    private Routine routine;

    @Column(name = "SUBJECT_ID")
    private Long subject;
}
