package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeeTrainingDetail;
import com.eteacher.service.enums.TrainingType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeTrainingDetailsAudit {

    private Long id;

    private Long employeeTrainingSl;

    private TrainingType traningType;

    private String traningInstituteName;

    private String traningInstituteAddress;

    private Date dateOfEnrollment;

    private Date dateOfCompletion;

    private Integer scoreAchieved;

    private Boolean isForeign;

    private Long gradePoint;

    public static EmployeeTrainingDetailsAudit audit(EmployeeTrainingDetail details) {
        EmployeeTrainingDetailsAudit audit = new EmployeeTrainingDetailsAudit();
        audit.setId(details.getId());
        audit.setEmployeeTrainingSl(details.getEmployeeTrainingSl());
        audit.setTraningType(details.getTrainingType());
        audit.setTraningInstituteName(details.getTrainingInstituteName());
        audit.setTraningInstituteAddress(details.getTrainingInstituteAddress());
        audit.setDateOfEnrollment(details.getDateOfEnrollment());
        audit.setDateOfCompletion(details.getDateOfCompletion());
        audit.setScoreAchieved(details.getScoreAchieved());
        audit.setIsForeign(details.getIsForeign());
        audit.setGradePoint(details.getGradePoint());
        return audit;
    }
}
