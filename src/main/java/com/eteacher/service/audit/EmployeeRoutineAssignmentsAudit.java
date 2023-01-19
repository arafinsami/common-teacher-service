package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.EmployeeRoutineAssignment;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeRoutineAssignmentsAudit {

    private Long id;

    private String dayName;

    private Integer dayValue;

    private Date startAt;

    private Date endAt;

    private Integer duration;

    private Boolean isRecurring;

    private Long classGroup;

//    private Employee employee;

//    private Routine routine;

//    private Long subject;

    public static EmployeeRoutineAssignmentsAudit audit(EmployeeRoutineAssignment assignment) {
        EmployeeRoutineAssignmentsAudit audit = new EmployeeRoutineAssignmentsAudit();
        audit.setId(assignment.getId());
        audit.setDayName(assignment.getDayName());
        audit.setDayValue(assignment.getDayValue());
        audit.setStartAt(assignment.getStartAt());
        audit.setEndAt(assignment.getEndAt());
        audit.setDuration(assignment.getDuration());
        audit.setIsRecurring(assignment.getIsRecurring());
        audit.setClassGroup(assignment.getClassGroup());
        return audit;
    }
}
