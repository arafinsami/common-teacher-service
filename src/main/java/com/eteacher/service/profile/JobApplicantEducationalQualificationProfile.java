package com.eteacher.service.profile;

import com.eteacher.service.entity.job.JobApplicantEducationalQualification;
import lombok.Data;

import static com.eteacher.service.enums.RecordStatus.ACTIVE;
import static com.eteacher.service.enums.RecordStatus.DRAFT;

@Data
public class JobApplicantEducationalQualificationProfile {

    private Long id;

    private String universityName;

    private String examGrade;

    private String examGradeBn;

    private Long qualificationSl;

    private Integer examPassingYear;

    private Integer scoreAchieved;

    private Boolean isForeign;

    private Long educationBoardId;

    private Long educationalQualificationId;

    private Long gradePointId;

    private Long instituteId;

    public JobApplicantEducationalQualification to() {
        JobApplicantEducationalQualification qualification = new JobApplicantEducationalQualification();
        qualification.setUniversityName(universityName);
        qualification.setExamGrade(examGrade);
        qualification.setExamGradeBn(examGradeBn);
        qualification.setQualificationSl(qualificationSl);
        qualification.setExamPassingYear(examPassingYear);
        qualification.setScoreAchieved(scoreAchieved);
        qualification.setIsForeign(isForeign);
        qualification.setEducationBoardId(educationBoardId);
        qualification.setEducationalQualificationId(educationalQualificationId);
        qualification.setGradePointId(gradePointId);
        qualification.setInstituteId(instituteId);
        qualification.setRecordStatus(DRAFT);
        return qualification;
    }

    public JobApplicantEducationalQualification update() {
        JobApplicantEducationalQualification qualification = new JobApplicantEducationalQualification();
        qualification.setId(id);
        qualification.setUniversityName(universityName);
        qualification.setExamGrade(examGrade);
        qualification.setExamGradeBn(examGradeBn);
        qualification.setQualificationSl(qualificationSl);
        qualification.setExamPassingYear(examPassingYear);
        qualification.setScoreAchieved(scoreAchieved);
        qualification.setIsForeign(isForeign);
        qualification.setEducationBoardId(educationBoardId);
        qualification.setEducationalQualificationId(educationalQualificationId);
        qualification.setGradePointId(gradePointId);
        qualification.setInstituteId(instituteId);
        qualification.setRecordStatus(ACTIVE);
        return qualification;
    }
}
