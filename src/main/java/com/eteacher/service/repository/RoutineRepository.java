package com.eteacher.service.repository;


import com.eteacher.service.entity.commonteacher.Routine;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

    Optional<Routine> findByIdAndRecordStatusNot(Long id, RecordStatus status);

    Optional<Routine> findByRoutineNameAndRecordStatusNot(String routineName, RecordStatus status);

    Optional<Routine> findByRoutineNameBnAndRecordStatusNot(String routineName, RecordStatus status);

    Optional<Routine> findTopByOrderByIdDesc();
}
