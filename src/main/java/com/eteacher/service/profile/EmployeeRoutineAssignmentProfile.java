package com.eteacher.service.profile;

import com.eteacher.service.entity.commonteacher.EmployeeRoutineAssignment;
import com.eteacher.service.enums.RecordStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class EmployeeRoutineAssignmentProfile {

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

    private Long subject;

    public EmployeeRoutineAssignment to(Boolean doDelete) {
        EmployeeRoutineAssignment routineAssignment = new EmployeeRoutineAssignment();
        routineAssignment.setId(id);
        routineAssignment.setDayName(dayName);
        routineAssignment.setDayValue(dayValue);
        routineAssignment.setStartAt(startAt);
        routineAssignment.setEndAt(endAt);
        routineAssignment.setDuration(duration);
        routineAssignment.setIsRecurring(isRecurring);
        routineAssignment.setClassGroup(classGroup);
        routineAssignment.setSubject(subject);
        if (doDelete) {
            routineAssignment.setRecordStatus(RecordStatus.DELETED);
        } else {
            routineAssignment.setRecordStatus(RecordStatus.ACTIVE);
        }
        return routineAssignment;
    }

    public EmployeeRoutineAssignment to() {
        EmployeeRoutineAssignment routineAssignment = new EmployeeRoutineAssignment();
        routineAssignment.setId(id);
        routineAssignment.setDayName(dayName);
        routineAssignment.setDayValue(dayValue);
        routineAssignment.setStartAt(startAt);
        routineAssignment.setEndAt(endAt);
        routineAssignment.setDuration(duration);
        routineAssignment.setIsRecurring(isRecurring);
        routineAssignment.setClassGroup(classGroup);
        routineAssignment.setSubject(subject);
        routineAssignment.setRecordStatus(RecordStatus.DRAFT);
        return routineAssignment;
    }

    public EmployeeRoutineAssignment update() {
        EmployeeRoutineAssignment routineAssignment = new EmployeeRoutineAssignment();
        routineAssignment.setId(id);
        routineAssignment.setDayName(dayName);
        routineAssignment.setDayValue(dayValue);
        routineAssignment.setStartAt(startAt);
        routineAssignment.setEndAt(endAt);
        routineAssignment.setDuration(duration);
        routineAssignment.setIsRecurring(isRecurring);
        routineAssignment.setClassGroup(classGroup);
        routineAssignment.setSubject(subject);
        routineAssignment.setRecordStatus(RecordStatus.ACTIVE);
        return routineAssignment;
    }

}
