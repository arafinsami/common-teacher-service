package com.eteacher.service.dto;

import com.eteacher.service.entity.commonteacher.Routine;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class RoutineDto {

    private Long id;

    @NotBlank
    private String routineName;

    @NotBlank
    private String routineNameBn;

    private String routineDescription;

    private String routineDescriptionBn;

    public Routine to() {
        Routine routine = new Routine();
        routine.setRoutineName(routineName);
        routine.setRoutineNameBn(routineNameBn);
        routine.setRoutineDescription(routineDescription);
        routine.setRoutineDescriptionBn(routineDescriptionBn);
        return routine;
    }

    public void update(Routine routine) {
        routine.setRoutineName(routineName);
        routine.setRoutineNameBn(routineNameBn);
        routine.setRoutineDescription(routineDescription);
        routine.setRoutineDescriptionBn(routineDescriptionBn);
    }
}
