package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicy;
import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicyDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExamMarkingPolicyDto {


    private Long id;

    private String remarks;

    private Long instituteId;

    private List<InstituteExamMarkingPolicyDetail> instituteExamMarkingPolicyDetail;

    public InstituteExamMarkingPolicy to(ExamMarkingPolicyDto request) {
        InstituteExamMarkingPolicy examMarkingPolicy = new InstituteExamMarkingPolicy();
        return examMarkingPolicy;
    }

    public void update(ExamMarkingPolicyDto request, InstituteExamMarkingPolicy examMarkingPolicy) {

    }
}
