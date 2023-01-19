package com.eteacher.service.audit;

import com.eteacher.service.entity.commonteacher.Routine;
import lombok.Data;

@Data
public class RoutineAudit {

    private Long id;

    private String routineName;

    private String routineNameBn;

    private String routineDescription;

    private String routineDescriptionBn;

    public static RoutineAudit from(Routine routine) {
        RoutineAudit audit = new RoutineAudit();
        audit.setId(routine.getId());
        audit.setRoutineName(routine.getRoutineName());
        audit.setRoutineNameBn(routine.getRoutineNameBn());
        audit.setRoutineDescription(routine.getRoutineDescription());
        audit.setRoutineDescriptionBn(routine.getRoutineDescriptionBn());
        return audit;
    }
}
