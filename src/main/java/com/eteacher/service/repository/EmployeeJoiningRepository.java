package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.EmployeeJoining;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeJoiningRepository extends JpaRepository<EmployeeJoining, Long> {

    Optional<EmployeeJoining> findTopByOrderByIdDesc();

    Optional<EmployeeJoining> findByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
