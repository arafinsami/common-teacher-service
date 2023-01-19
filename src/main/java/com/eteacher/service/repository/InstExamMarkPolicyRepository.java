package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.InstituteExamMarkingPolicy;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstExamMarkPolicyRepository extends JpaRepository<InstituteExamMarkingPolicy, Long> {

    Optional<InstituteExamMarkingPolicy> findByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);

    Optional<InstituteExamMarkingPolicy> findByInstituteId(Long instExamMarkPolId);
}
