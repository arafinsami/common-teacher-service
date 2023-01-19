package com.eteacher.service.entity.ntrca;

import com.eteacher.service.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "DEPARTMENT")
public class Department extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID")
    private Long id;

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    @Column(name = "DEPARTMENT_NAME_BN")
    private String departmentNameBn;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DESCRIPTION_BN")
    private String descriptionBn;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "department"
    )
    private List<DepartmentalExamination> examinations;

    public void addDeptExams(List<DepartmentalExamination> lists) {
        if (examinations == null) {
            examinations = new ArrayList<>();
        }
        lists.forEach(list -> {
            list.setDepartment(this);
        });
        examinations.addAll(lists);
    }

}
