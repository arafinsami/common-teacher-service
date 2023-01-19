package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeeEducationalQualification;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeEducationalQualificationResponse {

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

    public static EmployeeEducationalQualificationResponse response(EmployeeEducationalQualification qualification) {
        EmployeeEducationalQualificationResponse response = new EmployeeEducationalQualificationResponse();
        response.setId(qualification.getId());
        response.setInstituteUniversityName(qualification.getInstituteUniversityName());
        response.setEducationQualificationExamGrade(qualification.getEducationQualificationExamGrade());
        response.setEducationQualificationExamGradeBn(qualification.getEducationQualificationExamGradeBn());
        response.setEmployeeEducationalQualificationSl(qualification.getEmployeeEducationalQualificationSl());
        response.setEducationQualificationExamPassingYear(qualification.getEducationQualificationExamPassingYear());
        response.setScoreAchieved(qualification.getScoreAchieved());
        response.setIsForeign(qualification.getIsForeign());
        response.setEducationBoard(qualification.getEducationBoard());
        response.setEducationalQualification(qualification.getEducationalQualification());
        response.setGradePoint(qualification.getGradePoint());
        response.setInstitute(qualification.getInstitute());
        return response;
    }
}
