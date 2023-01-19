package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeeJoining;
import com.eteacher.service.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.eteacher.service.utils.StringUtils.isNotEmpty;
import static com.eteacher.service.utils.StringUtils.nonNull;

@Data
@NoArgsConstructor
public class EmployeeJoiningResponse {

    private Long id;

    private Date effectiveDate;

    private Integer postingStatus;

    private Long approverUserId;

    private String approverNote;

    private Date approveDate;

    private Long fromDesignation;

    private Long toDesignation;

    private Long employee;

    private Long fromInstitute;

    private Long toInstitute;

    private Long reasonForRejection;

    public static EmployeeJoiningResponse from(EmployeeJoining employeeJoining) {
        EmployeeJoiningResponse response = new EmployeeJoiningResponse();
        response.setId(employeeJoining.getId());
        response.setEffectiveDate(employeeJoining.getEffectiveDate());
        response.setPostingStatus(employeeJoining.getPostingStatus());
        response.setApproverUserId(employeeJoining.getApproverUserId());
        response.setApproverNote(employeeJoining.getApproverNote());
        response.setApproveDate(employeeJoining.getApproveDate());
        response.setFromDesignation(employeeJoining.getFromDesignation());
        response.setToDesignation(employeeJoining.getToDesignation());
        response.setEmployee(nonNull(
                employeeJoining.getEmployee()) ?
                employeeJoining.getEmployee().getId()
                : 0l
        );
        response.setFromInstitute(employeeJoining.getFromInstitute());
        response.setToInstitute(employeeJoining.getToInstitute());
        response.setReasonForRejection(employeeJoining.getReasonForRejection());
        return response;
    }

    public static Map<String, Object> searchEmployeeJoining(
            String effectiveDate,
            Integer postingStatus,
            Long approverUserId,
            String approverNote,
            String approveDate,
            Long fromDesignation,
            Long toDesignation,
            Long employee,
            Long fromInstitute,
            Long toInstitute,
            Long reasonForRejection) {
        Map<String, Object> map = new HashMap<>();
        map.put("effectiveDate", isNotEmpty(effectiveDate) ? DateUtils.asDate(effectiveDate) : effectiveDate);
        map.put("postingStatus", postingStatus);
        map.put("approverUserId", approverUserId);
        map.put("approverNote", approverNote);
        map.put("approveDate", isNotEmpty(approveDate) ? DateUtils.asDate(approveDate) : approveDate);
        map.put("fromDesignation", fromDesignation);
        map.put("toDesignation", toDesignation);
        map.put("employee", employee);
        map.put("fromInstitute", fromInstitute);
        map.put("toInstitute", toInstitute);
        map.put("reasonForRejection", reasonForRejection);
        return map;
    }
}
