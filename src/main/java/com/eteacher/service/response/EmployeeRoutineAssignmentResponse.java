package com.eteacher.service.response;

import com.eteacher.service.entity.commonteacher.EmployeeRoutineAssignment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeRoutineAssignmentResponse {

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

    public static EmployeeRoutineAssignmentResponse response(EmployeeRoutineAssignment assignment) {
        EmployeeRoutineAssignmentResponse response = new EmployeeRoutineAssignmentResponse();
        response.setId(assignment.getId());
        response.setDayName(assignment.getDayName());
        response.setDayValue(assignment.getDayValue());
        response.setStartAt(assignment.getStartAt());
        response.setEndAt(assignment.getEndAt());
        response.setDuration(assignment.getDuration());
        response.setIsRecurring(assignment.getIsRecurring());
        response.setClassGroup(assignment.getClassGroup());
        return response;
    }
}
