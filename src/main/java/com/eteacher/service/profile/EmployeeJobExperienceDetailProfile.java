package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.EmployeeJobExperienceDetail;
import com.eteacher.service.enums.JobNature;
import com.eteacher.service.enums.RecordStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeJobExperienceDetailProfile {

    private Long id;

    private Long employeeJobExperienceSl;

    private String organizationName;

    private String organizationAddress;

    private String designationName;

    private Date dateOfJoining;

    private Date dateOfRelease;

    private Boolean isPresent;

    private JobNature jobNature;


    public EmployeeJobExperienceDetail to(Boolean doDelete) {
        EmployeeJobExperienceDetail detail = new EmployeeJobExperienceDetail();
        detail.setId(id);
        detail.setEmployeeJobExperienceSl(employeeJobExperienceSl);
        detail.setOrganizationName(organizationName);
        detail.setOrganizationAddress(organizationAddress);
        detail.setDesignationName(designationName);
        detail.setDateOfJoining(dateOfJoining);
        detail.setDateOfRelease(dateOfRelease);
        detail.setIsPresent(isPresent);
        detail.setJobNature(jobNature);
        if (doDelete) {
            detail.setRecordStatus(RecordStatus.DELETED);
        } else {
            detail.setRecordStatus(RecordStatus.ACTIVE);
        }
        return detail;
    }
}
