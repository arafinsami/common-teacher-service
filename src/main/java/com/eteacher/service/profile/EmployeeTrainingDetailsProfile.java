package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.EmployeeTrainingDetail;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.enums.TrainingType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeTrainingDetailsProfile {

    private Long id;

    private Long employeeTrainingSl;

    private TrainingType trainingType;

    private String trainingInstituteName;

    private String trainingInstituteAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dateOfEnrollment;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date dateOfCompletion;

    private Integer scoreAchieved;

    private Boolean isForeign;

    private Long gradePoint;

    public EmployeeTrainingDetail to() {
        EmployeeTrainingDetail trainingDetail = new EmployeeTrainingDetail();
        trainingDetail.setId(id);
        trainingDetail.setEmployeeTrainingSl(employeeTrainingSl);
        trainingDetail.setTrainingType(trainingType);
        trainingDetail.setTrainingInstituteName(trainingInstituteName);
        trainingDetail.setTrainingInstituteAddress(trainingInstituteAddress);
        trainingDetail.setDateOfEnrollment(dateOfEnrollment);
        trainingDetail.setDateOfCompletion(dateOfCompletion);
        trainingDetail.setScoreAchieved(scoreAchieved);
        trainingDetail.setIsForeign(isForeign);
        trainingDetail.setGradePoint(gradePoint);
        trainingDetail.setRecordStatus(RecordStatus.DRAFT);
        return trainingDetail;
    }

    public EmployeeTrainingDetail update() {
        EmployeeTrainingDetail trainingDetail = new EmployeeTrainingDetail();
        trainingDetail.setId(id);
        trainingDetail.setEmployeeTrainingSl(employeeTrainingSl);
        trainingDetail.setTrainingType(trainingType);
        trainingDetail.setTrainingInstituteName(trainingInstituteName);
        trainingDetail.setTrainingInstituteAddress(trainingInstituteAddress);
        trainingDetail.setDateOfEnrollment(dateOfEnrollment);
        trainingDetail.setDateOfCompletion(dateOfCompletion);
        trainingDetail.setScoreAchieved(scoreAchieved);
        trainingDetail.setIsForeign(isForeign);
        trainingDetail.setGradePoint(gradePoint);
        trainingDetail.setRecordStatus(RecordStatus.ACTIVE);
        return trainingDetail;
    }
}
