package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeeJobExperienceDetail;
import com.eteacher.service.enums.JobNature;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeJobExperienceDetailAudit {

    private Long id;

    private Long employeeJobExperienceSl;

    private String organizationName;

    private String organizationAddress;

    private String designationName;

    private Date dateOfJoining;

    private Date dateOfRelease;

    private Boolean isPresent;

    private JobNature jobNature;


    public static EmployeeJobExperienceDetailAudit from(EmployeeJobExperienceDetail detail) {
        EmployeeJobExperienceDetailAudit audit = new EmployeeJobExperienceDetailAudit();
        audit.setId(detail.getId());
        audit.setEmployeeJobExperienceSl(detail.getEmployeeJobExperienceSl());
        audit.setOrganizationName(detail.getOrganizationName());
        audit.setOrganizationAddress(detail.getOrganizationAddress());
        audit.setDesignationName(detail.getDesignationName());
        audit.setDateOfJoining(detail.getDateOfJoining());
        audit.setDateOfRelease(detail.getDateOfRelease());
        audit.setIsPresent(detail.getIsPresent());
        audit.setJobNature(detail.getJobNature());
        return audit;
    }
}
