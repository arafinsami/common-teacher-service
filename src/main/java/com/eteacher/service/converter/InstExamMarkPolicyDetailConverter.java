package com.eteacher.service.converter;

import lombok.Data;

@Data
public class InstExamMarkPolicyDetailConverter {

  private Long id;

  private Double percentageMarks;

  private Long subjectId;

  private Long examTerm;

  private Long examType;
}
