package com.eteacher.service.response;


import com.eteacher.service.entity.commonteacher.Routine;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class RoutineResponse {

    private Long id;

    private String routineName;

    private String routineNameBn;

    private String routineDescription;

    private String routineDescriptionBn;

    public static RoutineResponse response(Routine routine) {
        RoutineResponse response = new RoutineResponse();
        response.setId(routine.getId());
        response.setRoutineName(routine.getRoutineName());
        response.setRoutineNameBn(routine.getRoutineNameBn());
        response.setRoutineDescription(routine.getRoutineDescription());
        response.setRoutineDescriptionBn(routine.getRoutineDescriptionBn());
        return response;
    }

    public static Map<String, Object> searchRoutine(
            String routineName,
            String routineNameBn) {
        Map<String, Object> map = new HashMap<>();
        map.put("routineName", routineName);
        map.put("routineNameBn", routineNameBn);
        return map;
    }
}
