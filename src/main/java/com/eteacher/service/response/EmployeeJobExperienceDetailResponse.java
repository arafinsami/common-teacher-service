package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeeJobExperienceDetail;
import com.eteacher.service.enums.JobNature;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeJobExperienceDetailResponse {

    private Long id;

    private Long employeeJobExperienceSl;

    private String organizationName;

    private String organizationAddress;

    private String designationName;

    private Date dateOfJoining;

    private Date dateOfRelease;

    private Boolean isPresent;

    private JobNature jobNature;


    public static EmployeeJobExperienceDetailResponse response(EmployeeJobExperienceDetail detail) {
        EmployeeJobExperienceDetailResponse response = new EmployeeJobExperienceDetailResponse();
        response.setId(detail.getId());
        response.setEmployeeJobExperienceSl(detail.getEmployeeJobExperienceSl());
        response.setOrganizationName(detail.getOrganizationName());
        response.setOrganizationAddress(detail.getOrganizationAddress());
        response.setDesignationName(detail.getDesignationName());
        response.setDateOfJoining(detail.getDateOfJoining());
        response.setDateOfRelease(detail.getDateOfRelease());
        response.setIsPresent(detail.getIsPresent());
        response.setJobNature(detail.getJobNature());
        return response;
    }
}
