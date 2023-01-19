package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeeTrainingDetail;
import com.eteacher.service.enums.TrainingType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeTrainingDetailResponse {

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

    public static EmployeeTrainingDetailResponse response(EmployeeTrainingDetail details) {
        EmployeeTrainingDetailResponse response = new EmployeeTrainingDetailResponse();
        response.setId(details.getId());
        response.setEmployeeTrainingSl(details.getEmployeeTrainingSl());
        response.setTraningType(details.getTrainingType());
        response.setTraningInstituteName(details.getTrainingInstituteName());
        response.setTraningInstituteAddress(details.getTrainingInstituteAddress());
        response.setDateOfEnrollment(details.getDateOfEnrollment());
        response.setDateOfCompletion(details.getDateOfCompletion());
        response.setScoreAchieved(details.getScoreAchieved());
        response.setIsForeign(details.getIsForeign());
        response.setGradePoint(details.getGradePoint());
        return response;
    }
}
