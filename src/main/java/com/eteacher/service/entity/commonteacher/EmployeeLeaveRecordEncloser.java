package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import com.eteacher.service.enums.EncloserType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "EMPLOYEE_LEAVE_RECORD_ENCLOSER")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmployeeLeaveRecordEncloser extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_LEAVE_RECORD_ENCLOSER_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ENCLOSER_TYPE", length = 12)
    private EncloserType encloserType;

    @Column(name = "MEMORANDUM_NO")
    private String memorandumNo;

    @Column(name = "ISSUE_DATE")
    private Date issueDate;

    @Column(name = "ENCLOSER_NAME")
    private String encloserName;

    @Column(name = "ENCLOSER_NAME_BN")
    private String encloserNameBn;

    @Column(name = "ENCLOSER_URL")
    private String encloserUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;
}
