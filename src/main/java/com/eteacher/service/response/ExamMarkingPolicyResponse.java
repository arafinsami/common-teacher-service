package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicy;
import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicyDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static com.eteacher.service.utils.StringUtils.nonNull;


@Data
@NoArgsConstructor
public class ExamMarkingPolicyResponse {

    private Long id;

    private String remarks;

    private Long instituteId;

    private List<InstituteExamMarkingPolicyDetail> instituteExamMarkingPolicyDetail;

    public static ExamMarkingPolicyResponse from(InstituteExamMarkingPolicy examMarkingPolicy) {
        ExamMarkingPolicyResponse response = new ExamMarkingPolicyResponse();
        response.setId(examMarkingPolicy.getId());
        response.setRemarks(examMarkingPolicy.getRemarks());
        response.setInstituteId(examMarkingPolicy.getInstituteId());
        response.setInstituteExamMarkingPolicyDetail(
                nonNull(examMarkingPolicy.getPolicyDetails()) ?
                        examMarkingPolicy.getPolicyDetails()
                        : new ArrayList<>()
        );
        return response;
    }
}
