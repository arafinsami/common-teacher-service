package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicyDetail;
import lombok.Data;

@Data
public class InstExamMarkPolicyDetailProfile {

  private Long id;

  private Double percentageMarks;

  private Long subjectId;

  private Long examTerm;

  private Long examType;

  public InstituteExamMarkingPolicyDetail to() {
    InstituteExamMarkingPolicyDetail policyDetail = new InstituteExamMarkingPolicyDetail();
    policyDetail.setId(id);
    policyDetail.setPercentageMarks(percentageMarks);
    policyDetail.setSubjectId(subjectId);
    policyDetail.setExamTerm(examTerm);
    policyDetail.setExamType(examType);
    return policyDetail;
  }
}
