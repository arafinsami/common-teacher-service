package com.eteacher.service.audit;

import com.eteacher.service.entity.job.JobApplicantEducationalQualification;
import lombok.Data;

@Data
public class JobApplicantEducationalQualificationAudit {

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

    public static JobApplicantEducationalQualificationAudit audit(JobApplicantEducationalQualification qualification) {
        JobApplicantEducationalQualificationAudit audit = new JobApplicantEducationalQualificationAudit();
        audit.setId(qualification.getId());
        audit.setUniversityName(qualification.getUniversityName());
        audit.setExamGrade(qualification.getExamGrade());
        audit.setExamGradeBn(qualification.getExamGradeBn());
        audit.setQualificationSl(qualification.getQualificationSl());
        audit.setExamPassingYear(qualification.getExamPassingYear());
        audit.setScoreAchieved(qualification.getScoreAchieved());
        audit.setIsForeign(qualification.getIsForeign());
        audit.setEducationBoardId(qualification.getEducationBoardId());
        audit.setEducationalQualificationId(qualification.getEducationalQualificationId());
        audit.setGradePointId(qualification.getGradePointId());
        audit.setInstituteId(qualification.getInstituteId());
        return audit;
    }
}
