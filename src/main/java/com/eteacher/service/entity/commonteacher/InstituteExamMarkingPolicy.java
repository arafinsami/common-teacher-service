package com.eteacher.service.entity.commonteacher;

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
@Table(name = "INSTITUTE_EXAM_MARKING_POLICY")
@EqualsAndHashCode(callSuper = false)
public class InstituteExamMarkingPolicy extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INSTITUTE_EXAM_MARKING_POLICY_ID")
    private Long id;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "INSTITUTE_ID")
    private Long instituteId;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "policy"
    )
    private List<InstituteExamMarkingPolicyDetail> policyDetails;

    public void addPolicyDetail(List<InstituteExamMarkingPolicyDetail> lists) {
        if (policyDetails == null) {
            policyDetails = new ArrayList<>();
        }
        lists.forEach(l -> {
            l.setPolicy(this);
        });
        policyDetails.addAll(lists);
    }
}
