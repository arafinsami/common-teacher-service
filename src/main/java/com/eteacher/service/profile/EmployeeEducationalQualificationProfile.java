package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.EmployeeEducationalQualification;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
@NoArgsConstructor
public class EmployeeEducationalQualificationProfile {

    private Long id;

    @NotBlank
    private String universityName;

    @NotBlank
    private String examGrade;

    private String examGradeBn;

    @NotNull
    private Long qualificationSl;

    @NotNull
    private Integer examPassingYear;

    private Integer scoreAchieved;

    private Boolean isForeign;

    @NotNull
    private Long educationBoard;

    @NotNull
    private Long educationalQualification;

    @NotNull
    private Long gradePoint;

    @NotNull
    private Long institute;

    public EmployeeEducationalQualification to() {
        EmployeeEducationalQualification qualification = new EmployeeEducationalQualification();
        qualification.setId(id);
        qualification.setInstituteUniversityName(universityName);
        qualification.setEducationQualificationExamGrade(examGrade);
        qualification.setEducationQualificationExamGradeBn(examGradeBn);
        qualification.setEmployeeEducationalQualificationSl(qualificationSl);
        qualification.setEducationQualificationExamPassingYear(examPassingYear);
        qualification.setScoreAchieved(scoreAchieved);
        qualification.setIsForeign(isForeign);
        qualification.setEducationBoard(educationBoard);
        qualification.setEducationalQualification(educationalQualification);
        qualification.setGradePoint(gradePoint);
        qualification.setInstitute(institute);
        qualification.setRecordStatus(DRAFT);
        return qualification;
    }

    public EmployeeEducationalQualification update() {
        EmployeeEducationalQualification qualification = new EmployeeEducationalQualification();
        qualification.setId(id);
        qualification.setInstituteUniversityName(universityName);
        qualification.setEducationQualificationExamGrade(examGrade);
        qualification.setEducationQualificationExamGradeBn(examGradeBn);
        qualification.setEmployeeEducationalQualificationSl(qualificationSl);
        qualification.setEducationQualificationExamPassingYear(getExamPassingYear());
        qualification.setScoreAchieved(scoreAchieved);
        qualification.setIsForeign(isForeign);
        qualification.setEducationBoard(educationBoard);
        qualification.setEducationalQualification(educationalQualification);
        qualification.setGradePoint(gradePoint);
        qualification.setInstitute(institute);
        qualification.setRecordStatus(ACTIVE);
        return qualification;
    }
}
