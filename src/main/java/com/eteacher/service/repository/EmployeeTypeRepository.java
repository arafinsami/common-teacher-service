package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.EmployeeType;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Long> {

  Optional<EmployeeType> findByEmployeeTypeName(String employeeTypeName);

  Optional<EmployeeType> findByEmployeeTypeNameBn(String employeeTypeNameBn);

  Optional<EmployeeType> findTopByOrderByIdDesc();

  Optional<EmployeeType> findByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);

  Optional<EmployeeType> findByEmployeeTypeNameAndRecordStatusNot(String employeeTypeName, RecordStatus recordStatus);

  Optional<EmployeeType> findByEmployeeTypeNameBnAndRecordStatusNot(String employeeTypeNameBn, RecordStatus recordStatus);
}
