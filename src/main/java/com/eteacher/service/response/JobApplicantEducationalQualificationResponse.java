package com.eteacher.service.response;

import com.eteacher.service.entity.job.JobApplicantEducationalQualification;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JobApplicantEducationalQualificationResponse {

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

    public static JobApplicantEducationalQualificationResponse response(JobApplicantEducationalQualification qualification) {
        JobApplicantEducationalQualificationResponse response = new JobApplicantEducationalQualificationResponse();
        response.setId(qualification.getId());
        response.setUniversityName(qualification.getUniversityName());
        response.setExamGrade(qualification.getExamGrade());
        response.setExamGradeBn(qualification.getExamGradeBn());
        response.setQualificationSl(qualification.getQualificationSl());
        response.setExamPassingYear(qualification.getExamPassingYear());
        response.setScoreAchieved(qualification.getScoreAchieved());
        response.setIsForeign(qualification.getIsForeign());
        response.setEducationBoardId(qualification.getEducationBoardId());
        response.setEducationalQualificationId(qualification.getEducationalQualificationId());
        response.setGradePointId(qualification.getGradePointId());
        response.setInstituteId(qualification.getInstituteId());
        return response;
    }
}
