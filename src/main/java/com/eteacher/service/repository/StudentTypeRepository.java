package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.StudentType;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentTypeRepository extends JpaRepository<StudentType, Long> {

    Optional<StudentType> findByStudentTypeNameBn(String studentTypeNameBn);

    Optional<StudentType> findByStudentTypeName(String studentTypeName);

    Optional<StudentType> findByStudentTypeIdLegacy(String studentTypeIdLegacy);

    Optional<StudentType> findByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);
}
