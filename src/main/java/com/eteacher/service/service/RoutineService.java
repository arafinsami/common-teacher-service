package com.eteacher.service.service;


import com.eteacher.service.audit.RoutineAudit;
import com.eteacher.service.entity.commonteacher.Routine;
import com.eteacher.service.enums.RecordStatus;
import com.eteacher.service.helper.RoutineHelper;
import com.eteacher.service.repository.RoutineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.eteacher.service.constant.MessageConstants.*;
import static com.eteacher.service.enums.Action.*;
import static com.eteacher.service.enums.ModuleName.E_TEACHER;
import static com.eteacher.service.utils.StringUtils.objectToJson;


@Service
@RequiredArgsConstructor
public class RoutineService extends BaseService {

    private final RoutineRepository repository;

    private final ActionLogService actionLogService;

    private final RoutineHelper routineHelper;

    public Optional<Routine> findById(Long id) {
        Optional<Routine> routine = repository.findById(id);
        return routine;
    }

    public Optional<Routine> findByRoutineName(String routineName, RecordStatus status) {
        Optional<Routine> routine = repository.findByRoutineNameAndRecordStatusNot(routineName, status);
        return routine;
    }

    public Optional<Routine> findByRoutineNameBn(String routineName, RecordStatus status) {
        Optional<Routine> routine = repository.findByRoutineNameBnAndRecordStatusNot(routineName, status);
        return routine;
    }

    @Transactional
    public Routine save(Routine routine) {
        routineHelper.getSaveData(routine);
        Routine savedRoutine = repository.save(routine);
        RoutineAudit audit = RoutineAudit.from(savedRoutine);
        actionLogService.publishActivity(
                SAVE,
                E_TEACHER,
                audit.getId(),
                ROUTINE_SAVE,
                objectToJson(audit)
        );
        return savedRoutine;
    }

    @Transactional
    public Routine update(Routine routine) {
        routineHelper.getUpdateData(routine, RecordStatus.ACTIVE);
        Routine updatedRoutine = repository.save(routine);
        RoutineAudit audit = RoutineAudit.from(updatedRoutine);
        actionLogService.publishActivity(
                UPDATE,
                E_TEACHER,
                audit.getId(),
                ROUTINE_UPDATE,
                objectToJson(audit)
        );
        return updatedRoutine;
    }

    @Transactional
    public Routine delete(Routine routine) {
        routineHelper.getUpdateData(routine, RecordStatus.DELETED);
        Routine deletedRoutine = repository.save(routine);
        RoutineAudit audit = RoutineAudit.from(deletedRoutine);
        actionLogService.publishActivity(
                DELETE,
                E_TEACHER,
                audit.getId(),
                ROUTINE_DELETE,
                objectToJson(audit)
        );
        return deletedRoutine;
    }
}
