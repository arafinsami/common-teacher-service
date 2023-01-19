package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeeEducationalQualification;
import lombok.Data;

@Data
public class EmployeeEducationalQualificationAudit {

  private Long id;

  private String instituteUniversityName;

  private String educationQualificationExamGrade;

  private String educationQualificationExamGradeBn;

  private Long employeeEducationalQualificationSl;

  private Integer educationQualificationExamPassingYear;

  private Integer scoreAchieved;

  private Boolean isForeign;

  private Long educationBoard;

  private Long educationalQualification;

  private Long gradePoint;

  private Long institute;

  public static EmployeeEducationalQualificationAudit from(EmployeeEducationalQualification qualification) {
    EmployeeEducationalQualificationAudit audit = new EmployeeEducationalQualificationAudit();
    audit.setId(qualification.getId());
    audit.setInstituteUniversityName(qualification.getInstituteUniversityName());
    audit.setEducationQualificationExamGrade(qualification.getEducationQualificationExamGrade());
    audit.setEducationQualificationExamGradeBn(qualification.getEducationQualificationExamGradeBn());
    audit.setEmployeeEducationalQualificationSl(qualification.getEmployeeEducationalQualificationSl());
    audit.setEducationQualificationExamPassingYear(qualification.getEducationQualificationExamPassingYear());
    audit.setScoreAchieved(qualification.getScoreAchieved());
    audit.setIsForeign(qualification.getIsForeign());
    audit.setEducationBoard(qualification.getEducationBoard());
    audit.setEducationalQualification(qualification.getEducationalQualification());
    audit.setGradePoint(qualification.getGradePoint());
    audit.setInstitute(qualification.getInstitute());
    return audit;
  }
}
